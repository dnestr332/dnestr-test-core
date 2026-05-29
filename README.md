# dnestr-test-core

Multi-module test automation core providing base utilities and specialized frameworks for Web (Playwright) and Mobile (Appium) automation.

## Project Structure

This repository is a Maven multi-module project:

- **`dnestr-base-core`**: Shared core utilities including custom assertions (`Softly`), base API clients, property readers, and common enums.
- **`dnestr-web-core`**: Web automation framework built on top of [Microsoft Playwright](https://playwright.dev/java/).
- **`dnestr-mobile-core`**: Mobile automation framework built on top of [Appium](https://appium.io/) and Selenium, supporting both Android and iOS.

## Requirements

- **Java**: JDK 21
- **Maven**: 3.8+
- **Lombok**: Required for compilation (plugin installed in IDE)
- **Playwright Dependencies**: For web automation (run `mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"` if needed)
- **Appium Server**: Required for mobile automation

## Setup & Installation

Clone the repository and install the artifacts to your local Maven repository:

```bash
mvn clean install -DskipTests
```

## Scripts & Commands

- **Build all modules**: `mvn clean compile`
- **Run all tests**: `mvn test`
- **Install to local repository**: `mvn clean install`
- **Run tests for a specific module**:
  ```bash
  mvn test -pl dnestr-base-core
  ```

# 2.0.0

## Breaking Changes

### Base Core
- Reorganized package structure
- Added FieldState
- Improved state handling

### Web Core
- BaseElement -> PageElement
- BaseTableCell -> TableColumn
- AppTable -> PageTable
- Refactored assertion flows

### Mobile Core
- Reorganized package structure
- Added AppAction
- Moved MobilePlatform to enums
- Improved flow architecture
