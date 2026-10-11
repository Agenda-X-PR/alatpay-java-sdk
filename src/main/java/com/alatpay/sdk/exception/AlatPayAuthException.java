package com.alatpay.sdk.exception;

/**
 * Thrown when ALAT Pay rejects the request due to authentication failure
 * (HTTP 401 or 403).
 *
 * <p>This typically means the secret key, subscription key, or business ID
 * is missing, invalid, or does not have access to the requested resource.</p>
 */
public class AlatPayAuthException extends AlatPayApiException {

    /**
     * Creates a new authentication exception.
     *
     * @param message     the error description
     * @param statusCode  the HTTP status code (401 or 403)
     * @param rawResponse the raw response body from ALAT Pay
     */
    public AlatPayAuthException(String message, int statusCode, String rawResponse) {
        super(message, statusCode, rawResponse);
    }
}