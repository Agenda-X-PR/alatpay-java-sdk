package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response envelope for the ALAT Pay settlements listing endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettlementListResponse implements AlatPayResponse {

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<Settlement> data;

    /**
     * Empty constructor required by Jackson.
     */
    public SettlementListResponse() {
    }

    /** @return true on success */
    public boolean isStatus() { return status; }

    /** @return the response message */
    public String getMessage() { return message; }

    /** @return the list of settlements */
    public List<Settlement> getData() { return data; }

    @Override
    public String toString() {
        return "SettlementListResponse{status=" + status
                + ", count=" + (data != null ? data.size() : 0) + "}";
    }
}