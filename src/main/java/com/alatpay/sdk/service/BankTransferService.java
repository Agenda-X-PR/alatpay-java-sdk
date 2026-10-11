package com.alatpay.sdk.service;

import com.alatpay.sdk.AlatPayClient;
import com.alatpay.sdk.VirtualAccountResponse;
import com.alatpay.sdk.exception.AlatPayApiException;
import com.alatpay.sdk.exception.AlatPayAuthException;
import com.alatpay.sdk.exception.AlatPayNetworkException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Service for ALAT Pay bank transfer operations.
 *
 * <p>Currently supports generating virtual bank accounts for customers.
 * Obtain an instance via {@link com.alatpay.sdk.AlatPayClient#bankTransfer()}.</p>
 */
public class BankTransferService {

    private final AlatPayClient client;

    public BankTransferService(AlatPayClient client) {
        this.client = client;
    }

    /**
 * Generates a virtual bank account for a customer.
 *
 * @return the {@link VirtualAccountResponse} containing the account details
 * @throws AlatPayAuthException    if the credentials are invalid
 * @throws AlatPayApiException     if ALAT Pay returns any other error
 * @throws AlatPayNetworkException if the server cannot be reached
 */
    public VirtualAccountResponse generateVirtualAccount() 
            throws AlatPayApiException, AlatPayNetworkException {

        String fullUrl = client.getConfig().getBaseUrl()
        + "/alatpaytransaction/api/v1/transactions";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .header("Authorization", "Bearer " + client.getConfig().getSecretKey())
                .header("Business-Id", client.getConfig().getBusinessId())
                .header("Ocp-Apim-Subscription-Key", client.getConfig().getSubscriptionKey())
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.getHttpClient().send(
                    httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new AlatPayNetworkException("Could not reach ALAT Pay", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AlatPayNetworkException("Request interrupted", e);
        }

        int status = response.statusCode();
        String body = response.body();

        if (status == 401 || status == 403) {
            throw new AlatPayAuthException(
                "Auth failed", status, body);
        }
        if (status >= 400) {
            throw new AlatPayApiException(
                "API error", status, body);
        }

        try {
            return client.getMapper().readValue(body, VirtualAccountResponse.class);
        } catch (IOException e) {
            throw new AlatPayApiException(
                "Could not parse response", status, body);
        }
    }
}