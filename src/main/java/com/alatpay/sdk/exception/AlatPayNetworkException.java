package com.alatpay.sdk.exception;

/**
 * Thrown when the SDK cannot reach ALAT Pay's servers.
 *
 * <p>This usually means there's no internet connection, the request timed out,
 * or ALAT Pay's servers are unreachable. It's generally safe to retry after
 * a short delay.</p>
 */
public class AlatPayNetworkException extends AlatPayException {

    /**
     * Creates a new network exception.
     *
     * @param message the error description
     * @param cause   the original I/O exception that triggered this one
     */
    public AlatPayNetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}