# Automation Framework – OrangeHRM

A Java + Selenium UI automation framework built against the [OrangeHRM open-source demo](https://opensource-demo.orangehrmlive.com) (`Admin` / `admin123`), following the Page Object Model with a fluent API, a driver factory for multi-browser/parallel execution, and Allure reporting with automatic screenshot/video capture on test completion.

## Tech Stack

| Layer | Tools |
|---|---|
| Language / Build | Java 23, Maven |
| UI Automation | Selenium 4.45 |
| API Automation | REST Assured 6.0 (used alongside UI in select flows) |
| Test Runner | TestNG 7.12 |
| Reporting | Allure 2.34 |
| Logging | Log4j2 |
| Media | `video-recorder-testng` + `jave-all-deps` (test recordings), Selenium screenshots |
| Data | JSON test data (Jackson / json-path / json-simple) |

## Project Structure

```
Automation-Frame-work-OrangeHRM/
├── src/main/java/com/orangehrm/
│   ├── drivers/
│   │   ├── AbstractDriver.java        # Factory contract – createDriver()
│   │   ├── Browser.java               # Enum (CHROME/FIREFOX/EDGE) → returns matching factory
│   │   ├── ChromeFactory / FireFoxFactory / EdgeFactory
│   │   ├── GUIDriver.java             # ThreadLocal<WebDriver> wrapper; exposes element()/browser()/frame()/alert()/validation()/verification()
│   │   ├── WebDriverProvider.java     # Interface for listener access to the raw WebDriver
│   │   └── UITest.java                # Marker interface for UI test classes (drives screenshot/video capture)
│   ├── pages/pagescomponents/         # Page Object Model (fluent – each action returns the next page)
│   │   ├── LoginPage, DashboardPage, AdminPage, EmployeeManagementPage,
│   │   │   LeavePage, MyInfoPage, RecruitmentPage, NavigationBarComponents
│   ├── utils/
│   │   ├── actions/                   # ElementActions, BrowserActions, FrameActions, AlertActions – wrap raw Selenium calls
│   │   ├── dataReader/                # JsonReader, PropertyReader
│   │   ├── logs/LogsManager.java
│   │   ├── WaitManager, TimeManager, OsUtils, TerminalUtils, FileUtils
│   ├── validations/
│   │   ├── Validation.java            # Soft assertions (accumulate, assertAll() at end)
│   │   ├── Verification.java          # Hard assertions (fail fast)
│   │   └── BaseAssertion.java
│   ├── media/                         # ScreenShotsManager, ScreenRecordManager
│   └── listeners/TestNGListeners.java # ISuiteListener/IExecutionListener/IInvokedMethodListener/ITestListener
├── src/main/resources/
│   ├── config.properties              # credentials, browser, execution type, base URLs
│   ├── environment.properties         # environment URLs
│   ├── video.properties               # recordTests flag, recordings folder
│   ├── allure.properties              # allure-results directory, auto-open report
│   ├── log4j2.properties
│   └── META-INF/services/org.testng.ITestNGListener   # registers TestNGListeners via Java SPI
├── src/test/java/com/orangehrm/
│   ├── BaseTest.java                  # Instantiates GUIDriver + page objects, navigates to login page
│   ├── LoginTest.java                 # Login validations
│   ├── EmployeeManagementTests.java   # PIM/employee management (UI, with REST Assured available)
│   └── tests/e2e/
│       ├── E2E1_FullHrLifecycleTest.java   # Sequential, dependent steps: login → add employee → create system user → ...
│       └── CrossLayerE2ETest.java          # Placeholder for a combined API + UI flow
└── src/test/resources/test-data/      # employee.json, leave.json, login.json
```

## Design Patterns

- **Driver Factory** – `Browser` enum maps to `ChromeFactory` / `FireFoxFactory` / `EdgeFactory`, each implementing `AbstractDriver.createDriver()`. `GUIDriver` wraps the created `WebDriver` in a `ThreadLocal`, making the framework parallel-execution-safe out of the box, and exposes action/assertion helpers instead of the raw driver.
- **Fluent Page Object Model** – Every page action returns the next logical page object (e.g. `loginPage.login(...)` returns a `NavigationBarComponents`; `clickPimButton()` returns an `EmployeeManagementPage`), so tests read as a chained flow rather than disconnected steps.
- **Action Layer** – `ElementActions` / `BrowserActions` / `FrameActions` / `AlertActions` centralize raw Selenium interaction (typing, clicking, waits, alerts), keeping page objects declarative and locators/waits consistent framework-wide.
- **Dual Assertion Strategy** – `Validation` (soft asserts, collected and thrown together via `assertAll()`) vs. `Verification` (hard asserts, fail fast) both extend a common `BaseAssertion`, letting tests choose the right failure behavior per step.
- **Listener-Driven Reporting** – `TestNGListeners` (registered via `META-INF/services/org.testng.ITestNGListener`, so it applies automatically without a `testng.xml` suite file) cleans/creates output directories at suite start, and auto-captures a screenshot (pass/fail/skip) and screen recording for any test class implementing `UITest`.
- **Data-Driven Tests** – `JsonReader` pulls scenario data (valid/invalid credentials, employee records) from JSON files in `test-data/`, and `PropertyReader` centralizes config/environment values.

## Test Coverage

| Class | Focus |
|---|---|
| `LoginTest` | Valid login, invalid username/password, required-field validation |
| `EmployeeManagementTests` | Add employee, add system user, and related PIM flows |
| `E2E1_FullHrLifecycleTest` | Sequential (`dependsOnMethods`) end-to-end flow: admin login → add employee → create system user → ... — tagged with Allure `@Epic`/`@Feature`/`@Story`/`@Severity` |
| `CrossLayerE2ETest` | Reserved for a combined REST Assured (API) + Selenium (UI) flow — currently a stub |

## Setup

1. **Prerequisites**: JDK 23, Maven, a Chrome/Firefox/Edge browser installed locally.
2. Configure `src/main/resources/config.properties`:
   ```properties
   admin.username=Admin
   admin.password=admin123
   browser=Chrome
   executionType=Local
   base.url=https://opensource-demo.orangehrmlive.com
   ```
   (These are the public credentials for OrangeHRM's open-source demo instance — not sensitive.)
3. Optional: set `recordTests=true` in `video.properties` to capture screen recordings of UI test runs.

## Running Tests

There's no `testng.xml` suite in this project — TestNG/Surefire auto-discovers `*Test.java` classes, and `TestNGListeners` attaches itself automatically via the Java `ServiceLoader` entry in `META-INF/services`. Run:

```bash
mvn clean test
```

or run individual test classes directly from your IDE.

## Reporting & Artifacts

Each run produces, under `test-output/`:
- **Logs/** – timestamped Log4j2 log file per run
- **screenshots/** – auto-captured on pass, failure, and skip for any `UITest`
- **recordings/** – screen recordings, if `recordTests=true`
- **allure-results/** – raw Allure results (`allure.properties` points here and can auto-open the report after execution)

Generate the HTML report with the Allure CLI:
```bash
allure serve test-output/allure-results
```

## Notes

- `EmployeeManagementTests` already imports REST Assured alongside the UI page objects, laying the groundwork for the cross-layer API + UI flow that `CrossLayerE2ETest` is reserved for.
- Two log/screenshot output roots exist in the repo (`test-output/` and `test-outputs/`) from earlier runs — `TestNGListeners` cleans and recreates `test-output/` at the start of each execution.
