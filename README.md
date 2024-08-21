# Welcome to api automation project!

# How to Run an Automation Project Locally

## Things to Install (* = optional)
- **Maven v. 3.9+** - [Maven – Installing Apache Maven](https://maven.apache.org/install.html)
- **Java 11** (All Providers are allowed except Oracle, Correto by default) - Install through IDE (Settings -> Project Structure -> Project -> SDK: Add JDK -> Choose version and set up language level - 11)
- **Git** - [Git - Downloads](https://git-scm.com/downloads)
- **Allure*** - [Allure Report Docs — Install Allure Report for macOS](https://docs.qameta.io/allure/#_installing_a_tool)

## How to Run It?

### Running the Suite
You have 2 ways of running it:

1. **Run from IDE:**
    - Choose the suite, open the context menu inside it - if you run for the first time, choose ‘More Run/Debug' - ‘Modify run Configuration’.
    - You’ll have the modal panel with run configurations. The only thing you need to do is to add the environment you want to run for. Add the command in your VM options and apply & save it:

    ```bash
    -DtestEnv=envName
    ```

   **EnvNames:** prod,

    - You’ll see your run results in the 'Run' section.
    - When you have already set it up - just click ‘Run' afterwards. If you don’t specify the environment, it’ll automatically choose the prod environment.

2. **Run through Maven:**

    ```bash
    mvn clean test -DtestSuite=suiteName -DtestEnv=envName
    ```

    - You’ll see your run results in the console.
    
    ```bash
    allure serve target/allure-results
    ```
    - You’ll see your run results in the allure html.
