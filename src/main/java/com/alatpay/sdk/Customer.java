package com.alatpay.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a customer associated with an ALAT Pay transaction.
 *
 * <p>Use the {@link #builder()} method to construct instances fluently:</p>
 * <pre>{@code
 * Customer customer = Customer.builder()
 *     .email("ada@example.com")
 *     .firstName("Ada")
 *     .lastName("Lovelace")
 *     .build();
 * }</pre>
 */
public class Customer {

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

        @JsonProperty("id")
    private String id;

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("metadata")
    private String metadata;

    /**
     * Empty constructor required by Jackson.
     */
    public Customer() {
    }

    /**
     * Returns a new builder for constructing a Customer.
     *
     * @return a fresh {@link Builder} instance
     */
    public static Builder builder() { return new Builder(); }

    /**
     * Returns the customer's email address.
     *
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Returns the customer's phone number.
     *
     * @return the phone number
     */
    public String getPhone() { return phone; }

    /**
     * Returns the customer's first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Returns the customer's last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }

        /** @return the customer's unique ID */
    public String getId() { return id; }

    /** @return the transaction ID associated with this customer */
    public String getTransactionId() { return transactionId; }

    /** @return the customer creation timestamp */
    public String getCreatedAt() { return createdAt; }

    /** @return any metadata attached to the customer */
    public String getMetadata() { return metadata; }

    /**
     * Fluent builder for {@link Customer} instances.
     */
    public static class Builder {
        private String email;
        private String phone;
        private String firstName;
        private String lastName;

        /** Sets the email. @param email the email @return this builder */
        public Builder email(String email) { this.email = email; return this; }

        /** Sets the phone. @param phone the phone @return this builder */
        public Builder phone(String phone) { this.phone = phone; return this; }

        /** Sets the first name. @param firstName the first name @return this builder */
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }

        /** Sets the last name. @param lastName the last name @return this builder */
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }

        /**
         * Builds a new Customer with the values set on this builder.
         *
         * @return the constructed Customer
         */
        public Customer build() {
            Customer c = new Customer();
            c.email = this.email;
            c.phone = this.phone;
            c.firstName = this.firstName;
            c.lastName = this.lastName;
            return c;
        }
    }
}