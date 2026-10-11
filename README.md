# ALAT Pay Java SDK

A community-built Java SDK for integrating [ALAT Pay](https://alatpay.ng) into your Java applications.

> **⚠️ Disclaimer:** This is an independent, community-built SDK. It is not affiliated with, endorsed by, or maintained by Wema Bank PLC or ALAT Pay.

## Features

- Three-header authentication (Secret Key, Business ID, Subscription Key)
- Bank Transfer (virtual accounts)
- USSD payments (coming soon)
- Static wallets (coming soon)
- Transaction listing
- Modern Java 17+ HTTP client
- Typed exceptions for clean error handling

## Requirements

- Java 17 or higher
- Maven 3.6+
- An ALAT Pay merchant account with API credentials

## Installation

Add this to your `pom.xml`:

```xml
<dependency>
    <groupId>ng.alatpay</groupId>
    <artifactId>alatpay-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

```java
import com.alatpay.sdk.AlatPayClient;
import com.alatpay.sdk.AlatPayConfig;

public class Example {
    public static void main(String[] args) throws Exception {
        AlatPayConfig config = new AlatPayConfig(
            "sk_your_secret_key",
            "your_subscription_key",
            "your_business_id"
        );

        AlatPayClient client = new AlatPayClient(config);

        // List transactions
        var transactions = client.listTransactions();
        transactions.forEach(System.out::println);
    }
}
```

## Getting Credentials

You need three credentials from ALAT Pay:

| Credential | Where to find it |
| :--- | :--- |
| **Secret Key** | Merchant Dashboard → Settings → Developer |
| **Business ID** | Merchant Dashboard → Settings → Business |
| **Subscription Key** | Azure APIM Portal → Subscribe to product |

## Error Handling

The SDK provides typed exceptions so you can handle different failures precisely:

```java
try {
    client.listTransactions();
} catch (AlatPayAuthException e) {
    // Wrong credentials
} catch (AlatPayNetworkException e) {
    // Connection problem
} catch (AlatPayApiException e) {
    // Server returned an error
}
```

## Project Structure

```
src/main/java/com/alatpay/sdk/
├── AlatPayClient.java         # Main entry point
├── AlatPayConfig.java         # Credentials holder
├── Transaction.java           # Transaction model
├── Customer.java              # Customer model
├── exception/                 # Typed exceptions
└── service/                   # API service wrappers
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first.

## License

MIT