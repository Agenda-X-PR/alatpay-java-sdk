package com.alatpay.sdk.service;

import com.alatpay.sdk.AlatPayClient;
import com.alatpay.sdk.VirtualAccountResponse;
import com.alatpay.sdk.exception.AlatPayApiException;
import com.alatpay.sdk.exception.AlatPayAuthException;
import com.alatpay.sdk.exception.AlatPayNetworkException;

/**
 * Service for ALAT Pay bank transfer operations.
 *
 * <p>Obtain an instance via {@link com.alatpay.sdk.AlatPayClient#bankTransfer()}.</p>
 *
 * <p><b>Status:</b> The bank transfer endpoint is not yet published in the
 * official ALATPay API documentation. This service is a placeholder and will
 * be implemented once the endpoint specification is available.</p>
 */
public class BankTransferService {

        // Reserved for future use once the bank transfer endpoint is documented.
    @SuppressWarnings("unused")
    private final AlatPayClient client;
    public BankTransferService(AlatPayClient client) {
        this.client = client;
    }

    /**
     * Generates a virtual bank account for a customer.
     *
     * <p><b>Not yet implemented</b> — awaiting endpoint documentation.</p>
     *
     * @return the {@link VirtualAccountResponse} containing the account details
     * @throws AlatPayAuthException    if the credentials are invalid
     * @throws AlatPayApiException     if ALAT Pay returns any other error
     * @throws AlatPayNetworkException if the server cannot be reached
     * @throws UnsupportedOperationException until the endpoint is confirmed
     */
    public VirtualAccountResponse generateVirtualAccount()
            throws AlatPayAuthException, AlatPayApiException, AlatPayNetworkException {
        throw new UnsupportedOperationException(
            "Bank transfer endpoint not yet documented. See ALATPay docs for details.");
    }
}