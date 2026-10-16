package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a settlement record from ALAT Pay.
 *
 * <p>A settlement is created when funds from a transaction are paid out
 * to the merchant's bank account. It contains the same information as a
 * transaction plus payout metadata.</p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settlement {

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

    @JsonProperty("settlementId")
    private String settlementId;

    @JsonProperty("customer")
    private Customer customer;

    /**
     * Empty constructor required by Jackson.
     */
    public Settlement() {
    }

    /** @return the unique settlement identifier */
    public String getId() { return id; }

    /** @return the amount in the smallest currency unit */
    public long getAmount() { return amount; }

    /** @return the merchant-supplied order ID */
    public String getOrderId() { return orderId; }

    /** @return the settlement description */
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

    /** @return the callback URL for this settlement */
    public String getCallbackUrl() { return callbackUrl; }

    /** @return the fee charged */
    public double getFeeAmount() { return feeAmount; }

    /** @return the currency code */
    public String getCurrency() { return currency; }

    /** @return the settlement status */
    public String getStatus() { return status; }

    /** @return the settlement ID */
    public String getSettlementId() { return settlementId; }

    /** @return the customer associated with this settlement */
    public Customer getCustomer() { return customer; }

    @Override
    public String toString() {
        return "Settlement{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}