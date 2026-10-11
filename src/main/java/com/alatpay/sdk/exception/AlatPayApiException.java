package com.alatpay.sdk.exception;

/**
 * Thrown when ALAT Pay's API returns an error response (HTTP 4xx or 5xx).
 *
 * <p>Carries the HTTP status code and the raw response body so callers
 * can inspect exactly what the server returned.</p>
 */
public class AlatPayApiException extends AlatPayException {

    private final int statusCode;
    private final String rawResponse;

    /**
     * Creates a new API exception.
     *
     * @param message     the error description
     * @param statusCode  the HTTP status code returned by ALAT Pay
     * @param rawResponse the raw response body from ALAT Pay
     */
    public AlatPayApiException(String message, int statusCode, String rawResponse) {
        super(message);
        this.statusCode = statusCode;
        this.rawResponse = rawResponse;
    }

    /**
     * Returns the HTTP status code from the failed request.
     *
     * @return the status code (for example, 400 or 500)
     */
    public int getStatusCode() { return statusCode; }

    /**
     * Returns the raw response body from ALAT Pay.
     *
     * @return the raw response, useful for logging and debugging
     */
    public String getRawResponse() { return rawResponse; }

    @Override
    public String toString() {
        return "AlatPayApiException{status=" + statusCode
                + ", message='" + getMessage() + "'}";
    }
}