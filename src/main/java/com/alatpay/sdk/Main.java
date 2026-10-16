package com.alatpay.sdk;

import com.alatpay.sdk.exception.AlatPayApiException;

/**
 * Example usage of the ALATPay Java SDK.
 *
 * <p>Set the following environment variables before running:</p>
 * <pre>
 *   ALATPAY_SECRET_KEY       your secret key (sk_...)
 *   ALATPAY_SUBSCRIPTION_KEY your Azure APIM subscription key
 *   ALATPAY_BUSINESS_ID      your business ID
 * </pre>
 *
 * <p>Never hardcode credentials in source code.</p>
 */
public class Main {

    public static void main(String[] args) {
        String secretKey = System.getenv("ALATPAY_SECRET_KEY");
        String subscriptionKey = System.getenv("ALATPAY_SUBSCRIPTION_KEY");
        String businessId = System.getenv("ALATPAY_BUSINESS_ID");

        if (secretKey == null || subscriptionKey == null || businessId == null) {
            System.err.println("Missing credentials. Set these environment variables:");
            System.err.println("  ALATPAY_SECRET_KEY");
            System.err.println("  ALATPAY_SUBSCRIPTION_KEY");
            System.err.println("  ALATPAY_BUSINESS_ID");
            return;
        }

        AlatPayConfig config = new AlatPayConfig(secretKey, subscriptionKey, businessId);
        AlatPayClient client = new AlatPayClient(config);

        try {
            System.out.println("Fetching transactions...");
            client.listTransactions().forEach(System.out::println);
        } catch (AlatPayApiException e) {
            System.err.println("API error [" + e.getStatusCode() + "]: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}