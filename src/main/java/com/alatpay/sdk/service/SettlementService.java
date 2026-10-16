package com.alatpay.sdk.service;

import com.alatpay.sdk.AlatPayClient;
import com.alatpay.sdk.Settlement;
import com.alatpay.sdk.SettlementListResponse;
import com.alatpay.sdk.exception.AlatPayApiException;
import com.alatpay.sdk.exception.AlatPayAuthException;
import com.alatpay.sdk.exception.AlatPayNetworkException;

import java.util.List;

/**
 * Service for ALAT Pay settlement operations.
 *
 * <p>Obtain an instance via {@link com.alatpay.sdk.AlatPayClient#settlements()}.</p>
 */
public class SettlementService {

    private final AlatPayClient client;

    public SettlementService(AlatPayClient client) {
        this.client = client;
    }

    /**
     * Fetches the first page of settlements for your business.
     *
     * @return a list of {@link Settlement} objects
     * @throws AlatPayAuthException    if the credentials are invalid
     * @throws AlatPayApiException     if ALAT Pay returns any other error
     * @throws AlatPayNetworkException if the server cannot be reached
     */
    public List<Settlement> listSettlements()
            throws AlatPayAuthException, AlatPayApiException, AlatPayNetworkException {
        return listSettlements(1);
    }

    /**
     * Fetches a specific page of settlements for your business.
     *
     * @param page the page number (1-based)
     * @return a list of {@link Settlement} objects
     * @throws AlatPayAuthException    if the credentials are invalid
     * @throws AlatPayApiException     if ALAT Pay returns any other error
     * @throws AlatPayNetworkException if the server cannot be reached
     */
    public List<Settlement> listSettlements(int page)
            throws AlatPayAuthException, AlatPayApiException, AlatPayNetworkException {

        String path = "/payment-settlement/api/v1/settlements"
                + "?page=" + page
                + "&businessId=" + client.getConfig().getBusinessId();

        SettlementListResponse response = client.get(path, SettlementListResponse.class);
        return response.getData();
    }
}