package com.alatpay.sdk.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.alatpay.sdk.Customer; // We'll need a Customer class

public class VirtualAccountRequest {
    @JsonProperty("amount")
    private long amount; // Amount in kobo

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("customer")
    private Customer customer;

    // Builder for clean object creation
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private long amount;
        private String orderId;
        private String description;
        private Customer customer;

        public Builder amount(long amount) { this.amount = amount; return this; }
        public Builder orderId(String orderId) { this.orderId = orderId; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder customer(Customer customer) { this.customer = customer; return this; }

        public VirtualAccountRequest build() {
            VirtualAccountRequest req = new VirtualAccountRequest();
            req.amount = this.amount;
            req.orderId = this.orderId;
            req.description = this.description;
            req.customer = this.customer;
            return req;
        }
    }

    // Getters
    public long getAmount() { return amount; }
    public String getOrderId() { return orderId; }
    public String getDescription() { return description; }
    public Customer getCustomer() { return customer; }
}