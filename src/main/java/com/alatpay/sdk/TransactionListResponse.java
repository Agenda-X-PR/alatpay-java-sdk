package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response envelope for the ALAT Pay transactions listing endpoint.
 *
 * <p>Wraps the list of transactions returned by ALAT Pay alongside
 * status, message, and pagination metadata.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListResponse implements AlatPayResponse {

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<Transaction> data;

    @JsonProperty("pagination")
    private Pagination pagination;

    public TransactionListResponse() {
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
     * Returns the list of transactions.
     *
     * @return the list; may be null if none were returned
     */
    public List<Transaction> getData() { return data; }

    /**
     * Returns pagination metadata for this response.
     *
     * @return the pagination object
     */
    public Pagination getPagination() { return pagination; }

    @Override
    public String toString() {
        return "TransactionListResponse{status=" + status
                + ", count=" + (data != null ? data.size() : 0)
                + ", page=" + (pagination != null ? pagination.getCurrentPage() : "?")
                + "}";
    }

    /**
     * Pagination metadata returned alongside list responses.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {

        @JsonProperty("currentPage")
        private int currentPage;

        @JsonProperty("totalItems")
        private int totalItems;

        @JsonProperty("totalPages")
        private int totalPages;

        @JsonProperty("pathUrl")
        private String pathUrl;

        @JsonProperty("previousPageUrl")
        private String previousPageUrl;

        @JsonProperty("nextPageUrl")
        private String nextPageUrl;

        /** @return the current page number */
        public int getCurrentPage() { return currentPage; }

        /** @return the total number of items across all pages */
        public int getTotalItems() { return totalItems; }

        /** @return the total number of pages */
        public int getTotalPages() { return totalPages; }

        /** @return the base path URL for the listing */
        public String getPathUrl() { return pathUrl; }

        /** @return URL to the previous page, or null */
        public String getPreviousPageUrl() { return previousPageUrl; }

        /** @return URL to the next page, or null */
        public String getNextPageUrl() { return nextPageUrl; }
    }
}