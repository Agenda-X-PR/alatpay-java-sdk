# Contributing to the ALAT Pay Java SDK

Thanks for your interest in contributing! This document explains how to get involved.

## Code of Conduct

Be kind. Be respectful. Be patient. We're all here to learn.

## How to Contribute

### Reporting Bugs

1. Check if the issue already exists in [Issues](https://github.com/Agenda-X-PR/alatpay-java-sdk/issues)
2. If not, open a new issue with:
   - A clear title
   - Steps to reproduce
   - Expected vs actual behavior
   - Your Java version and OS

### Suggesting Features

Open an issue describing:
- What you want to add
- Why it's useful
- Any implementation ideas

### Submitting Changes

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Make your changes
4. Run tests: `mvn clean test`
5. Commit with a clear message
6. Push and open a Pull Request

## Development Setup

Requirements:
- Java 21+
- Maven 3.9+

Build and test:

```bash
mvn clean test
```

## Code Style

- Use Javadoc on all public classes and methods
- Follow the existing package structure
- Add unit tests for new behavior
- Keep methods small and focused

## Running Tests

All tests use MockWebServer — no real credentials needed:

```bash
mvn test
```

## License

By contributing, you agree that your contributions will be licensed under the MIT License.