# Amazon Playwright Automation Framework (Java)

## Project Overview

This project demonstrates an **end-to-end UI automation framework** built using **Playwright with Java and TestNG**.
The framework automates a product purchase flow on **Amazon.in**, validating key user interactions such as searching for a product, navigating to the product page, adding the item to the cart, and verifying cart details.

The project follows **best automation practices**, including the **Page Object Model (POM)**, structured test architecture, reporting, screenshots, video recording, and **CI/CD integration using GitHub Actions**.

---

# Tech includes

* **Java 21**
* **Playwright (Java)**
* **TestNG**
* **Maven**
* **GitHub Actions (CI/CD)**
* **Allure Reports**
* **Page Object Model (POM)**

---

# Automation Scenario Covered

The automation test validates the following user journey:

1. Navigate to **Amazon.in**
2. Search for **“HP smart tank”**
3. Verify search results are displayed
4. Select the printer **“Smart Tank 589”**
5. Verify product page opens
6. Select **Quantity = 2**
7. Click **Add to Cart**
8. Verify **Proceed to Buy (2 items)** button appears

---

# Framework Architecture

The framework follows the **Page Object Model design pattern** to improve maintainability and reusability.

---

# Key Framework Features

### Page Object Model (POM)

Each web page is represented by a dedicated class containing locators and actions.

Example:

* `HomePage` → search functionality
* `SearchResultsPage` → select product
* `ProductPage` → quantity selection & add to cart
* `CartPage` → verify cart details

---

### Base Test Setup

`BaseTest` handles:

* Browser launch
* Playwright context setup
* Video recording
* Global timeouts
* Test teardown

---

### Screenshot Capture

Screenshots are automatically captured when tests fail.

Saved in:

```
screenshots/
```

---

### Video Recording

Every test execution records a browser video.

Saved in:

```
videos/
```

Video recording is enabled using:

```java
.setRecordVideoDir(Paths.get("videos/"))
```

---

### Test Reporting

Test execution generates **Allure reports** which include:

* Test steps
* Pass/Fail status
* Screenshots
* Execution duration

Generate report using:

```
mvn allure:report
```

Open report:

```
mvn allure:serve
```

---

# CI/CD Integration (GitHub Actions)

The framework includes a **GitHub Actions pipeline** that automatically runs tests on every push or pull request.

Workflow file:

```
.github/workflows/playwright-tests.yml
```

Pipeline Steps:

1. Checkout repository
2. Setup Java environment
3. Install Playwright browsers
4. Build project
5. Run automation tests
6. Upload artifacts:
   a.Screenshots
   b,Videos
   c.Test reports

---

# Running Tests Locally

## Prerequisites

Install:

* Java 21
* Maven
* Node dependencies for Playwright browsers

---

## Install Playwright Browsers

```
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

---

## Run Automation Tests

```
mvn clean test
```

---

# Sample Test Flow

```
1.Open Amazon 
      
2.Search HP smart tank
      
3.Verify search results
      
4.Click Smart Tank 589
      
5.Switch to product page
      
6.Select quantity = 2
      
7.Add to cart

8.Verify Proceed to Buy (2 items)
```

---

# Practices Implemented

* Page Object Model
* Reusable test setup
* Stable Playwright locators
* Explicit waits
* Headless execution support
* CI/CD pipeline integration
* Screenshot and video capture
* Clean project structure
---
