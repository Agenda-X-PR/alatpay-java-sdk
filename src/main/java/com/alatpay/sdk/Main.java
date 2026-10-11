package com.alatpay.sdk;
import com.alatpay.sdk.exception.AlatPayApiException;

public class Main {
    public static void main(String[] args) {
        try {
            AlatPayConfig config = new AlatPayConfig(
                "sk_test_placeholder",
                "c92de5d944654480ba779295f726f9be",
                "biz_test_placeholder"
            );

            AlatPayClient client = new AlatPayClient(config);

            System.out.println("Testing bank transfer service...");
            client.bankTransfer().generateVirtualAccount();

      } catch (AlatPayApiException e) {
    System.out.println("Error: " + e.getMessage());
    System.out.println("Status: " + e.getStatusCode());
    System.out.println("Raw: " + e.getRawResponse());

} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
    }
}