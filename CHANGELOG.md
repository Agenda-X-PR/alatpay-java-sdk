# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Planned
- Bank transfer POST endpoint (generate virtual account with request body)
- USSD payment service (initiate + validate)
- Static wallet service (create, list, history)
- Publishing to Maven Central

## [1.0.0] - 2026-10-11

### Added
- Initial release of the ALAT Pay Java SDK
- `AlatPayConfig` for holding credentials (secret key, subscription key, business ID)
- `AlatPayClient` as the main SDK entry point
- Three-header authentication support
- Transaction listing endpoint
- Bank transfer service (virtual accounts)
- Typed exception hierarchy: `AlatPayException`, `AlatPayApiException`, `AlatPayAuthException`, `AlatPayNetworkException`
- JSON deserialization using Jackson
- Comprehensive Javadoc on all public APIs
- Unit tests using JUnit 5 and MockWebServer
- GitHub Actions CI workflow
- MIT License

[Unreleased]: https://github.com/Agenda-X-PR/alatpay-java-sdk/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/Agenda-X-PR/alatpay-java-sdk/releases/tag/v1.0.0