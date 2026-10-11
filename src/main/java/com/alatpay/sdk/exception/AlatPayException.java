package com.alatpay.sdk.exception;

/**
 * Base exception for all ALAT Pay SDK errors.
 *
 * <p>All other ALAT Pay exceptions extend this class, so catching
 * {@code AlatPayException} will catch every SDK error.</p>
 */
public class AlatPayException extends Exception {

    /**
     * Creates a new exception with the given message.
     *
     * @param message the error description
     */
    public AlatPayException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the given message and underlying cause.
     *
     * @param message the error description
     * @param cause   the original exception that triggered this one
     */
    public AlatPayException(String message, Throwable cause) {
        super(message, cause);
    }
}