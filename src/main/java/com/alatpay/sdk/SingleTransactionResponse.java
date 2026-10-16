package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response envelope for the ALAT Pay single-transaction endpoint.
 *
 * <p>Wraps a single {@link Transaction} alongside status and message.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleTransactionResponse implements AlatPayResponse {
    @JsonProperty("status")
    private boolean status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Transaction data;

    /**
     * Empty constructor required by Jackson.
     */
    public SingleTransactionResponse() {
    }

    /**
     * Returns whether the request succeeded.
     *
     * @return true on success, false otherwise
     */
    public boolean isStatus() { return status; }

    /**
     * Returns the response message from ALAT Pay.
     *
     * @return the message
     */
    public String getMessage() { return message; }

    /**
     * Returns the transaction in the response.
     *
     * @return the transaction
     */
    public Transaction getData() { return data; }

    @Override
    public String toString() {
        return "SingleTransactionResponse{status=" + status
                + ", transaction=" + data + "}";
    }
}