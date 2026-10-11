package com.alatpay.sdk;

import com.alatpay.sdk.exception.AlatPayApiException;
import com.alatpay.sdk.exception.AlatPayAuthException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlatPayClientTest {

    private MockWebServer server;
    private AlatPayClient client;

    @BeforeEach
    void setUp() throws Exception {
        // Start a fake HTTP server on a random port
        server = new MockWebServer();
        server.start();

        // Override the base URL to the fake server
        String fakeBaseUrl = server.url("/").toString();
        // Remove trailing slash for consistency
        if (fakeBaseUrl.endsWith("/")) {
            fakeBaseUrl = fakeBaseUrl.substring(0, fakeBaseUrl.length() - 1);
        }

        // We need a way to inject the URL — for now, we'll use reflection-free approach
        // by creating config with a fake URL. Let's build a config just for tests:
        client = new AlatPayClient(configWithUrl(fakeBaseUrl));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void shouldParseSuccessfulTransactionList() throws Exception {
        // 1. Tell the fake server what to respond with
        String fakeJson = "{"
                + "\"status\":\"success\","
                + "\"message\":\"OK\","
                + "\"data\":["
                + "  {\"id\":\"txn_1\",\"amount\":5000,\"status\":\"successful\","
                + "   \"currency\":\"NGN\",\"reference\":\"ORDER-001\"},"
                + "  {\"id\":\"txn_2\",\"amount\":10000,\"status\":\"pending\","
                + "   \"currency\":\"NGN\",\"reference\":\"ORDER-002\"}"
                + "]}";

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(fakeJson)
                .addHeader("Content-Type", "application/json"));

        // 2. Call our SDK
        List<Transaction> transactions = client.listTransactions();

        // 3. Verify the results
        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertEquals("txn_1", transactions.get(0).getId());
        assertEquals(5000, transactions.get(0).getAmount());
        assertEquals("successful", transactions.get(0).getStatus());
        assertEquals("txn_2", transactions.get(1).getId());
    }

    @Test
    void shouldThrowAuthExceptionOn401() {
        // Server returns 401
        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody("{\"message\":\"Invalid credentials\"}")
                .addHeader("Content-Type", "application/json"));

        // Verify the SDK throws the right exception type
        AlatPayAuthException ex = assertThrows(
                AlatPayAuthException.class,
                () -> client.listTransactions()
        );

        assertEquals(401, ex.getStatusCode());
        assertTrue(ex.getMessage().contains("Invalid credentials"));
    }

    @Test
    void shouldThrowApiExceptionOn500() {
        server.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("{\"message\":\"Server error\"}")
                .addHeader("Content-Type", "application/json"));

        AlatPayApiException ex = assertThrows(
                AlatPayApiException.class,
                () -> client.listTransactions()
        );

        assertEquals(500, ex.getStatusCode());
    }

    @Test
    void shouldSendCorrectHeaders() throws Exception {
        // Respond successfully to any request
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"status\":\"success\",\"data\":[]}")
                .addHeader("Content-Type", "application/json"));

        // Trigger a request
        client.listTransactions();

        // Take the request the fake server received
        var receivedRequest = server.takeRequest();

        // Verify our SDK sent the right headers
        assertEquals("Bearer sk_test_fake",
                receivedRequest.getHeader("Authorization"));
        assertEquals("biz_test_fake",
                receivedRequest.getHeader("Business-Id"));
        assertEquals("sub_test_fake",
                receivedRequest.getHeader("Ocp-Apim-Subscription-Key"));
    }

    // Helper — creates a config with a specific base URL for testing
    private AlatPayConfig configWithUrl(String url) {
        // We can't modify AlatPayConfig's baseUrl externally, so we use reflection here.
        // Alternatively, you could add a setter/builder to AlatPayConfig later.
        AlatPayConfig config = new AlatPayConfig("sk_test_fake", "sub_test_fake", "biz_test_fake");
        try {
            var field = AlatPayConfig.class.getDeclaredField("baseUrl");
            field.setAccessible(true);
            field.set(config, url);
        } catch (Exception e) {
            throw new RuntimeException("Could not set baseUrl for test", e);
        }
        return config;
    }
}