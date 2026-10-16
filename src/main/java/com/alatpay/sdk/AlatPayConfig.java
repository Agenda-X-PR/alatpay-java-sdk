package com.alatpay.sdk;

/**
 * Configuration holder for the ALAT Pay SDK.
 *
 * <p>Holds the three credentials required to authenticate with ALAT Pay,
 * plus optional base URL and timeout settings.</p>
 */
public class AlatPayConfig {

    private static final String DEFAULT_BASE_URL = "https://apibox.alatpay.ng";
    private static final int DEFAULT_TIMEOUT_SECONDS = 30;

    private final String secretKey;
    private final String subscriptionKey;
    private final String businessId;
    private final String baseUrl;
    private final int timeoutSeconds;

    /**
     * Creates a config with default base URL and 30-second timeout.
     *
     * @param secretKey       the secret key from your merchant dashboard
     * @param subscriptionKey the Azure APIM subscription key
     * @param businessId      your unique ALAT Pay business identifier
     */
    public AlatPayConfig(String secretKey, String subscriptionKey, String businessId) {
        this(secretKey, subscriptionKey, businessId, DEFAULT_BASE_URL, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * Creates a config with custom base URL and timeout. Useful for testing
     * against a sandbox or a local mock server.
     *
     * @param secretKey       the secret key
     * @param subscriptionKey the subscription key
     * @param businessId      the business ID
     * @param baseUrl         the API base URL (no trailing slash)
     * @param timeoutSeconds  request/connect timeout in seconds
     */
    public AlatPayConfig(String secretKey, String subscriptionKey, String businessId,
                         String baseUrl, int timeoutSeconds) {
        if (secretKey == null || secretKey.isBlank()) {
            throw new IllegalArgumentException("secretKey must not be null or blank");
        }
        if (subscriptionKey == null || subscriptionKey.isBlank()) {
            throw new IllegalArgumentException("subscriptionKey must not be null or blank");
        }
        if (businessId == null || businessId.isBlank()) {
            throw new IllegalArgumentException("businessId must not be null or blank");
        }
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("baseUrl must not be null or blank");
        }
        if (timeoutSeconds <= 0) {
            throw new IllegalArgumentException("timeoutSeconds must be positive");
        }

        this.secretKey = secretKey;
        this.subscriptionKey = subscriptionKey;
        this.businessId = businessId;
        // Strip trailing slash to avoid double-slash when concatenating paths
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.timeoutSeconds = timeoutSeconds;
    }

    /** @return the secret key */
    public String getSecretKey() { return secretKey; }

    /** @return the subscription key */
    public String getSubscriptionKey() { return subscriptionKey; }

    /** @return the business ID */
    public String getBusinessId() { return businessId; }

    /** @return the API base URL */
    public String getBaseUrl() { return baseUrl; }

    /** @return the request timeout in seconds */
    public int getTimeoutSeconds() { return timeoutSeconds; }
}