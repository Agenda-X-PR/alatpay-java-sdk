package com.alatpay.sdk.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Utility for safely encoding values that go into URL query strings and path segments.
 *
 * <p>Prevents special characters ({@code /}, {@code ?}, {@code &}, spaces) from
 * breaking the request URL.</p>
 */
public final class UrlEncoder {

    private UrlEncoder() {
        // Utility class — no instances
    }

    /**
     * Encodes a value for use in a query string.
     *
     * @param value the raw value; may be null
     * @return the encoded value, or an empty string if the input is null
     */
    public static String query(String value) {
        if (value == null) return "";
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    /**
     * Encodes a value for use as a URL path segment.
     *
     * <p>Differs from {@link #query(String)} in that spaces are encoded as
     * {@code %20} rather than {@code +}, which is required for path segments.</p>
     *
     * @param value the raw value; may be null
     * @return the encoded value, or an empty string if the input is null
     */
    public static String path(String value) {
        if (value == null) return "";
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20");
    }
}