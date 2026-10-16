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

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("paymentMethodId")
    private int paymentMethodId;

    @JsonProperty("sessionId")
    private String sessionId;

    @JsonProperty("merchantName")
    private String merchantName;

    @JsonProperty("merchantId")
    private String merchantId;

    @JsonProperty("businessId")
    private String businessId;

    @JsonProperty("businessName")
    private String businessName;

    @JsonProperty("businessBankAccountNumber")
    private String businessBankAccountNumber;

    @JsonProperty("businessBankCode")
    private String businessBankCode;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("callbackUrl")
    private String callbackUrl;

    @JsonProperty("feeAmount")
    private double feeAmount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusReason")
    private String statusReason;

    @JsonProperty("settlementType")
    private String settlementType;

    @JsonProperty("settlementId")
    private String settlementId;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("settledAt")
    private String settledAt;

    @JsonProperty("ngnVirtualBankAccountNumber")
    private String ngnVirtualBankAccountNumber;

    @JsonProperty("ngnVirtualBankCode")
    private String ngnVirtualBankCode;

    @JsonProperty("usdVirtualAccountNumber")
    private String usdVirtualAccountNumber;

    @JsonProperty("usdVirtualBankCode")
    private String usdVirtualBankCode;

    /**
     * Empty constructor required by Jackson.
     */
    public Transaction() {
    }

    /** @return the unique transaction identifier */
    public String getId() { return id; }

    /** @return the amount in the smallest currency unit */
    public long getAmount() { return amount; }

    /** @return the merchant-supplied order ID */
    public String getOrderId() { return orderId; }

    /** @return the transaction description */
    public String getDescription() { return description; }

    /** @return the payment method/channel ID */
    public int getPaymentMethodId() { return paymentMethodId; }

    /** @return the session ID */
    public String getSessionId() { return sessionId; }

    /** @return the merchant's display name */
    public String getMerchantName() { return merchantName; }

    /** @return the merchant ID */
    public String getMerchantId() { return merchantId; }

    /** @return the business ID */
    public String getBusinessId() { return businessId; }

    /** @return the business display name */
    public String getBusinessName() { return businessName; }

    /** @return the business bank account number */
    public String getBusinessBankAccountNumber() { return businessBankAccountNumber; }

    /** @return the business bank code */
    public String getBusinessBankCode() { return businessBankCode; }

    /** @return the payment channel description */
    public String getChannel() { return channel; }

    /** @return the callback URL for this transaction */
    public String getCallbackUrl() { return callbackUrl; }

    /** @return the fee charged for this transaction */
    public double getFeeAmount() { return feeAmount; }

    /** @return the currency code (e.g., "NGN") */
    public String getCurrency() { return currency; }

    /** @return the transaction status (e.g., "pending", "successful") */
    public String getStatus() { return status; }

    /** @return a human-readable reason for the current status */
    public String getStatusReason() { return statusReason; }

    /** @return the settlement type (e.g., "Automatic") */
    public String getSettlementType() { return settlementType; }

    /** @return the settlement ID, if settled */
    public String getSettlementId() { return settlementId; }

    /** @return the customer associated with this transaction */
    public Customer getCustomer() { return customer; }

    /** @return the ISO-8601 creation timestamp */
    public String getCreatedAt() { return createdAt; }

    /** @return the ISO-8601 last-update timestamp */
    public String getUpdatedAt() { return updatedAt; }

    /** @return the ISO-8601 settlement timestamp, or null */
    public String getSettledAt() { return settledAt; }

    /** @return the NGN virtual bank account number */
    public String getNgnVirtualBankAccountNumber() { return ngnVirtualBankAccountNumber; }

    /** @return the NGN virtual bank code */
    public String getNgnVirtualBankCode() { return ngnVirtualBankCode; }

    /** @return the USD virtual account number */
    public String getUsdVirtualAccountNumber() { return usdVirtualAccountNumber; }

    /** @return the USD virtual bank code */
    public String getUsdVirtualBankCode() { return usdVirtualBankCode; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", currency='" + currency + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}