package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response from ALAT Pay when generating a virtual account.
 *
 * <p>Contains the account number and bank name that a customer
 * should pay into.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VirtualAccountResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Data data;

    /**
     * Returns the response status.
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
     * Returns the inner data object containing virtual account details.
     *
     * @return the data object
     */
    public Data getData() { return data; }

    /**
     * Inner payload holding virtual account information.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        @JsonProperty("transactionId")
        private String transactionId;

        @JsonProperty("virtualBankAccountNumber")
        private String virtualBankAccountNumber;

        @JsonProperty("bankName")
        private String bankName;

        /**
         * Returns the transaction ID associated with this virtual account.
         *
         * @return the transaction ID
         */
        public String getTransactionId() { return transactionId; }

        /**
         * Returns the generated virtual bank account number.
         *
         * @return the account number
         */
        public String getVirtualBankAccountNumber() { return virtualBankAccountNumber; }

        /**
         * Returns the name of the bank holding the virtual account.
         *
         * @return the bank name
         */
        public String getBankName() { return bankName; }
    }
}