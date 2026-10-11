package com.alatpay.sdk;

/**
 * Configuration holder for the ALAT Pay SDK.
 *
 * <p>Holds the three credentials required to authenticate with ALAT Pay:
 * the secret key, subscription key, and business ID.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * AlatPayConfig config = new AlatPayConfig(
 *     "sk_test_...",
 *     "subscription_key_...",
 *     "biz_..."
 * );
 * }</pre>
 */
public class AlatPayConfig {

    private String secretKey;
    private String subscriptionKey;
    private String businessId;
    private String baseUrl;

    /**
     * Creates a new configuration with the required ALAT Pay credentials.
     *
     * @param secretKey       the secret key from your ALAT Pay merchant dashboard
     * @param subscriptionKey the Azure APIM subscription key
     * @param businessId      your unique ALAT Pay business identifier
     */
    public AlatPayConfig(String secretKey, String subscriptionKey, String businessId) {
        this.secretKey = secretKey;
        this.subscriptionKey = subscriptionKey;
        this.businessId = businessId;
        this.baseUrl = "https://apibox.alatpay.ng";
    }

    /**
     * Returns the secret key used for request authentication.
     *
     * @return the secret key
     */
    public String getSecretKey() { return secretKey; }

    /**
     * Returns the Azure APIM subscription key.
     *
     * @return the subscription key
     */
    public String getSubscriptionKey() { return subscriptionKey; }

    /**
     * Returns the business identifier.
     *
     * @return the business ID
     */
    public String getBusinessId() { return businessId; }

    /**
     * Returns the base URL for ALAT Pay API requests.
     *
     * @return the base URL
     */
    public String getBaseUrl() { return baseUrl; }
}