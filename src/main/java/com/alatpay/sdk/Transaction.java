package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single ALAT Pay transaction.
 *
 * <p>Instances are created automatically by the SDK when parsing
 * API responses — you normally don't construct them yourself.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @JsonProperty("id")
    private String id;

    @JsonProperty("amount")
    private long amount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("reference")
    private String reference;

    /**
     * Empty constructor required by Jackson for JSON deserialization.
     */
    public Transaction() {
    }

    /**
     * Returns the unique transaction identifier.
     *
     * @return the transaction ID
     */
    public String getId() { return id; }

    /**
     * Returns the transaction amount in the smallest currency unit (kobo).
     *
     * @return the amount
     */
    public long getAmount() { return amount; }

    /**
     * Returns the transaction status (for example, "successful" or "pending").
     *
     * @return the status string
     */
    public String getStatus() { return status; }

    /**
     * Returns the currency code (for example, "NGN").
     *
     * @return the currency code
     */
    public String getCurrency() { return currency; }

    /**
     * Returns the merchant-provided reference for this transaction.
     *
     * @return the reference string
     */
    public String getReference() { return reference; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", currency='" + currency + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}