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
 */
public class BankTransferService {

    private final AlatPayClient client;

    public BankTransferService(AlatPayClient client) {
        this.client = client;
    }

    /**
     * Generates a virtual bank account for a customer.
     *
     * @return the {@link VirtualAccountResponse} containing the account details
     * @throws AlatPayAuthException    if the credentials are invalid
     * @throws AlatPayApiException     if ALAT Pay returns any other error
     * @throws AlatPayNetworkException if the server cannot be reached
     */
    public VirtualAccountResponse generateVirtualAccount()
            throws AlatPayAuthException, AlatPayApiException, AlatPayNetworkException {

        return client.get(
                "/alatpaytransaction/api/v1/transactions",
                VirtualAccountResponse.class
        );
    }
}