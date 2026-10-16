# ALAT Pay Java SDK

[![Java CI with Maven](https://github.com/Agenda-X-PR/alatpay-java-sdk/actions/workflows/ci.yml/badge.svg)](https://github.com/Agenda-X-PR/alatpay-java-sdk/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://adoptium.net)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org)

A community-built Java SDK for integrating [ALAT Pay](https://alatpay.ng) into your Java applications.

## Features

- 🔐 Three-header authentication (Secret Key, Business ID, Subscription Key)
- 📊 Transaction monitoring (list & filter transactions, get single transaction)
- 💰 Settlements retrieval
- 💳 Bank Transfer (virtual accounts) — *pending endpoint documentation*
- 📱 USSD payments — *planned*
- 🏦 Static wallets — *planned*
- ⚡ Modern Java 21+ HTTP client
- 🛡️ Typed exceptions for clean error handling
- 📄 Full Javadoc on all public APIs
- ✅ Comprehensive unit tests with MockWebServer

## Requirements

- Java 21 or higher
- Maven 3.9+
- An ALATPay merchant account with API credentials

## Installation

### From Source (Currently)

This SDK is not yet published to Maven Central. To use it, clone and install locally:

```bash
git clone https://github.com/Agenda-X-PR/alatpay-java-sdk.git
cd alatpay-java-sdk
mvn clean install
```

Then add it to your `pom.xml`:

```xml
<dependency>
    <groupId>ng.alatpay</groupId>
    <artifactId>alatpay-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### From Maven Central

*Coming soon.*

## Getting Credentials

You need three credentials from ALAT Pay:

| Credential | Where to find it |
| :--- | :--- |
| **Secret Key** | Merchant Dashboard → Settings → Business → View (requires PIN) |
| **Business ID** | Same location as the Secret Key |
| **Subscription Key** | ALATPay developer portal → subscribe to an API product |

**Never hardcode credentials.** Use environment variables or a secrets manager.

### Environment Variables

Set these before running your application:

**Windows (PowerShell):**

```powershell
$env:ALATPAY_SECRET_KEY="sk_your_secret_key"
$env:ALATPAY_SUBSCRIPTION_KEY="your_subscription_key"
$env:ALATPAY_BUSINESS_ID="your_business_id"
```

**Mac / Linux:**

```bash
export ALATPAY_SECRET_KEY="sk_your_secret_key"
export ALATPAY_SUBSCRIPTION_KEY="your_subscription_key"
export ALATPAY_BUSINESS_ID="your_business_id"
```

Or pass them directly to `AlatPayConfig` from your own configuration system.

## Quick Start

```java
import com.alatpay.sdk.AlatPayClient;
import com.alatpay.sdk.AlatPayConfig;
import com.alatpay.sdk.Transaction;

import java.util.List;

public class Example {
    public static void main(String[] args) throws Exception {

        AlatPayConfig config = new AlatPayConfig(
            System.getenv("ALATPAY_SECRET_KEY"),
            System.getenv("ALATPAY_SUBSCRIPTION_KEY"),
            System.getenv("ALATPAY_BUSINESS_ID")
        );

        AlatPayClient client = new AlatPayClient(config);

        // List all transactions (first page)
        List<Transaction> transactions = client.listTransactions();
        transactions.forEach(System.out::println);

        // Get a specific page
        List<Transaction> page2 = client.listTransactions(2);

        // Get a single transaction
        Transaction txn = client.getTransaction("transaction-id-here");
        System.out.println("Status: " + txn.getStatus());
    }
}
```

## Error Handling

The SDK provides typed exceptions so you can handle different failures precisely:

```java
try {
    client.listTransactions();
} catch (AlatPayAuthException e) {
    // Invalid or missing credentials (401/403)
    System.err.println("Auth problem: " + e.getMessage());
} catch (AlatPayNetworkException e) {
    // Could not reach ALAT Pay servers
    System.err.println("Network problem: " + e.getMessage());
} catch (AlatPayApiException e) {
    // Server returned an error (4xx / 5xx)
    System.err.println("API error [" + e.getStatusCode() + "]: " + e.getMessage());
}
```

All three exceptions extend `AlatPayException`, which you can catch as a fallback.

## Payment Channels

When making payments, ALATPay accepts a `channel` field with these values:

| Value | Payment Channel |
| :--- | :--- |
| `*` | All |
| `1` | Card |
| `2` | Bank Transfer |
| `3` | Bank Details |
| `5` | Phone (USSD) |
| `8` | Static Account |

## Project Structure

```
src/main/java/com/alatpay/sdk/
├── AlatPayClient.java              # Main entry point + shared HTTP logic
├── AlatPayConfig.java              # Credentials holder
├── Transaction.java                # Transaction model
├── TransactionListResponse.java    # Paginated transaction list response
├── SingleTransactionResponse.java  # Single transaction response
├── Settlement.java                 # Settlement model
├── SettlementListResponse.java     # Settlement list response
├── Customer.java                   # Customer model
├── VirtualAccountResponse.java     # Bank transfer response
├── exception/
│   ├── AlatPayException.java       # Base exception
│   ├── AlatPayApiException.java    # API errors (4xx/5xx)
│   ├── AlatPayAuthException.java   # Authentication errors (401/403)
│   └── AlatPayNetworkException.java # Network errors
└── service/
    ├── BankTransferService.java    # Bank transfer (pending spec)
    └── SettlementService.java      # Settlements
```

## Building & Testing

```bash
# Compile
mvn clean compile

# Run all tests
mvn clean test

# Package as JAR
mvn clean package
```

All tests use MockWebServer — no real API credentials required to run them.

## Roadmap

- [x] Transaction monitoring (list + filter)
- [x] Single transaction lookup
- [x] Settlements retrieval
- [x] Typed exception hierarchy
- [x] Unit tests + CI
- [ ] Bank Transfer (virtual account generation) — *pending endpoint docs*
- [ ] USSD payment flow
- [ ] Static wallets
- [ ] Publishing to Maven Central

## Contributing

Pull requests are welcome. Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for a version history.

## License

MIT — see [LICENSE](LICENSE) for details.
