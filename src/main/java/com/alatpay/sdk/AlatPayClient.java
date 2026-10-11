package com.alatpay.sdk;

import com.alatpay.sdk.exception.AlatPayApiException;
import com.alatpay.sdk.exception.AlatPayAuthException;
import com.alatpay.sdk.exception.AlatPayNetworkException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alatpay.sdk.service.BankTransferService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Main entry point for the ALAT Pay Java SDK.
 *
 * <p>Use this class to send requests to ALAT Pay. Construct it once with
 * a valid {@link AlatPayConfig}, then call the service accessor methods
 * (for example, {@link #bankTransfer()} or {@link #listTransactions()}).</p>
 *
 * <pre>{@code
 * AlatPayConfig config = new AlatPayConfig("sk_...", "sub_...", "biz_...");
 * AlatPayClient client = new AlatPayClient(config);
 * List<Transaction> txns = client.listTransactions();
 * }</pre>
 */

public class AlatPayClient {

    private AlatPayConfig config;
    private HttpClient httpClient;
    private ObjectMapper mapper;
    private BankTransferService bankTransferService;

    /**
 * Constructs a new SDK client using the given configuration.
 *
 * @param config the configuration holding your ALAT Pay credentials
 */

    public AlatPayClient(AlatPayConfig config) {
        this.config = config;
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.bankTransferService = new BankTransferService(this);
    }

    /**
 * Fetches the list of transactions for your business.
 *
 * @return a list of {@link Transaction} objects; may be empty if none exist
 * @throws AlatPayAuthException    if the credentials are invalid
 * @throws AlatPayApiException     if ALAT Pay returns any other error
 * @throws AlatPayNetworkException if the server cannot be reached
 */

    public List<Transaction> listTransactions() throws AlatPayApiException, AlatPayNetworkException {
        String fullUrl = config.getBaseUrl() + "/alatpaytransaction/api/v1/transactions";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .header("Authorization", "Bearer " + config.getSecretKey())
                .header("Business-Id", config.getBusinessId())
                .header("Ocp-Apim-Subscription-Key", config.getSubscriptionKey())
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new AlatPayNetworkException("Could not reach ALAT Pay servers", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AlatPayNetworkException("Request was interrupted", e);
        }

        int status = response.statusCode();
        String body = response.body();

        // Handle different error categories
        if (status == 401 || status == 403) {
            throw new AlatPayAuthException(
                "Authentication failed: " + extractMessage(body), status, body);
        }

        if (status >= 400) {
            throw new AlatPayApiException(
                "ALAT Pay returned error: " + extractMessage(body), status, body);
        }

        // Success — parse it
        try {
            TransactionListResponse parsed = mapper.readValue(body, TransactionListResponse.class);
            return parsed.getData();
        } catch (IOException e) {
            throw new AlatPayApiException(
                "Could not parse ALAT Pay response: " + e.getMessage(), status, body);
        }
    }

   /**
 * Extracts the "message" field from an ALAT Pay error response body.
 * Falls back to a generic message if the body isn't valid JSON.
 *
 * @param body the raw response body
 * @return the extracted message or a fallback string
 */
    private String extractMessage(String body) {
    try {
        var node = mapper.readTree(body).path("message");
        return node.isMissingNode() ? "unknown error" : node.asText();
    } catch (Exception e) {
        return "unknown error";
    }
}
/**
 * Returns the configuration this client was constructed with.
 *
 * @return the configuration
 */
   public AlatPayConfig getConfig() { return config; }
/**
 * Returns the bank transfer service for generating virtual accounts.
 *
 * @return the bank transfer service
 */
    public BankTransferService bankTransfer() { return bankTransferService; }

    public ObjectMapper getMapper() { return mapper; }
    public HttpClient getHttpClient() { return httpClient; }
        /**
     * Sends a GET request to the given path and parses the response into the given type.
     *
     * @param path         the endpoint path (for example, "/transactions")
     * @param responseType the class to deserialize the response into
     * @param <T>          the response type
     * @return the parsed response
     * @throws AlatPayAuthException    if credentials are invalid
     * @throws AlatPayApiException     if ALAT Pay returns any other error
     * @throws AlatPayNetworkException if the server cannot be reached
     */
    public<T> T get(String path, Class<T> responseType)
            throws AlatPayAuthException, AlatPayApiException, AlatPayNetworkException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBaseUrl() + path))
                .header("Authorization", "Bearer " + config.getSecretKey())
                .header("Business-Id", config.getBusinessId())
                .header("Ocp-Apim-Subscription-Key", config.getSubscriptionKey())
                .GET()
                .build();

        return execute(request, responseType);
    }

    /**
     * Sends a POST request with a JSON body and parses the response into the given type.
     *
     * @param path         the endpoint path
     * @param requestBody  the object to serialize as the request body
     * @param responseType the class to deserialize the response into
     * @param <T>          the response type
     * @return the parsed response
     * @throws AlatPayAuthException    if credentials are invalid
     * @throws AlatPayApiException     if ALAT Pay returns any other error
     * @throws AlatPayNetworkException if the server cannot be reached
     */
    public <T> T post(String path, Object requestBody, Class<T> responseType)
            throws AlatPayAuthException, AlatPayApiException, AlatPayNetworkException {

        String jsonBody;
        try {
            jsonBody = mapper.writeValueAsString(requestBody);
        } catch (IOException e) {
            throw new AlatPayApiException("Could not serialize request body: " + e.getMessage(), 0, null);
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getBaseUrl() + path))
                .header("Authorization", "Bearer " + config.getSecretKey())
                .header("Business-Id", config.getBusinessId())
                .header("Ocp-Apim-Subscription-Key", config.getSubscriptionKey())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return execute(request, responseType);
    }

    /**
     * Shared logic for sending a request and handling the response.
     */
    private <T> T execute(HttpRequest request, Class<T> responseType)
            throws AlatPayAuthException, AlatPayApiException, AlatPayNetworkException {

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new AlatPayNetworkException("Could not reach ALAT Pay servers", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AlatPayNetworkException("Request was interrupted", e);
        }

        int status = response.statusCode();
        String body = response.body();

        if (status == 401 || status == 403) {
            throw new AlatPayAuthException(
                    "Authentication failed: " + extractMessage(body), status, body);
        }

        if (status >= 400) {
            throw new AlatPayApiException(
                    "ALAT Pay returned error: " + extractMessage(body), status, body);
        }

        try {
            return mapper.readValue(body, responseType);
        } catch (IOException e) {
            throw new AlatPayApiException(
                    "Could not parse ALAT Pay response: " + e.getMessage(), status, body);
        }
    }
}