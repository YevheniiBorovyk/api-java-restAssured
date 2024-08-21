# API java rest assured

## Prerequisites

To get started, ensure you have the following installed:

- **Maven v. 3.9+** (Optional)  
  [Install Apache Maven](https://maven.apache.org/install.html)

- **Java 11**  
  Any provider is acceptable (e.g., OpenJDK). Install through your IDE:
  - Go to Settings -> Project Structure -> Project -> SDK.
  - Add JDK, choose version 11, and set up the language level.

- **Git**  
  [Download Git](https://git-scm.com/downloads)

- **Allure**  
  [Allure Report Docs — Install Allure Report for macOS](https://docs.qameta.io/allure/#_installing_a_tool)

## How to Run It?

### Running the Suite
You have 2 ways of running it:

1. **Run from IDE:**

    - You’ll see your run results in the 'Run' section.
    - When you have already set it up - just click ‘Run' afterwards. If you don’t specify the environment, it’ll automatically choose the prod environment.
    - You can change env in this class `SpecificationFactory`

2. **Run through Maven:**

    ```bash
    mvn clean test -DtestSuite=suiteName -DtestEnv=envName
    ```    
    - You’ll see your run results in the console.



    ```bash
    allure serve target/allure-results
    ```
    - You’ll see your run results in the allure html.
