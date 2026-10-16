package com.alatpay.sdk;

/**
 * Common contract for all ALAT Pay response envelopes.
 *
 * <p>Every successful response from ALAT Pay includes a boolean {@code status}
 * and a human-readable {@code message}. This interface lets the SDK check
 * both uniformly, regardless of the specific response type.</p>
 */
public interface AlatPayResponse {

    /**
     * Returns whether ALAT Pay reports the operation as successful.
     *
     * @return true if the operation succeeded
     */
    boolean isStatus();

    /**
     * Returns the human-readable message from ALAT Pay.
     *
     * @return the message; may be null
     */
    String getMessage();
}