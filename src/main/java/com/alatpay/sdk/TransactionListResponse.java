package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response envelope for the ALAT Pay transactions listing endpoint.
 *
 * <p>Wraps the list of transactions returned by ALAT Pay alongside
 * status and message metadata.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<Transaction> data;

    /**
     * Empty constructor required by Jackson.
     */
    public TransactionListResponse() {
    }

    /**
     * Returns the response status (for example, "success").
     *
     * @return the status
     */
    public String getStatus() { return status; }

    /**
     * Returns the response message from ALAT Pay.
     *
     * @return the message
     */
    public String getMessage() { return message; }

    /**
     * Returns the list of transactions in the response.
     *
     * @return the list of transactions; may be null if none were returned
     */
    public List<Transaction> getData() { return data; }

    @Override
    public String toString() {
        return "TransactionListResponse{status='" + status + "', count="
                + (data != null ? data.size() : 0) + "}";
    }
}