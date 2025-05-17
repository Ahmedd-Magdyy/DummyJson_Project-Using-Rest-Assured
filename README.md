# 🔍 API Testing Framework — DummyJSON Platform

This repository presents a comprehensive **automated API testing framework** integrating:
- **Postman + Newman** for manual & CLI-based functional API testing
- **Java (RestAssured + TestNG)** for advanced, reusable test scenarios
- **Allure** for clean and rich test reports
- **Jenkins** for continuous integration and scheduled execution

---

## 📦 Tech Stack

| Tool/Framework | Purpose                                                  |
|----------------|----------------------------------------------------------|
| Postman        | Design and test API requests manually                    |
| Newman         | Run Postman collections via command-line                 |
| RestAssured    | Automate REST API validation in Java                     |
| TestNG         | Java testing framework used with RestAssured             |
| Allure         | Generate visual reports from Java test results           |
| Jenkins        | Automate testing pipeline with CI/CD                     |

---

## 📁 Project Structure

```
DummyJson_Project/
├── postman/
│   ├── DummyJSON_collection.json     # Postman test collection
│   └── DummyJSON_environment.json    # Postman environment file
│
├── src/
│   ├── main/java/
│   │   ├── EndPoints/                # API route definitions
│   │   ├── Models/                   # POJOs for Users, Products, Carts...
│   │   ├── Utils/                    # Utility classes for operations
│   │   └── org/example/Main.java     # Optional test driver
│   │
│   └── test/java/
│       ├── com/dummyjson/users/
│       │   ├── testCases/            # Unit test classes per API feature
│       │   └── Scenarios/            # TestNG scenario classes (valid/invalid)
│
├── allure-report/                    # Generated HTML report from Allure
├── testOutputs/                      # Allure results & Surefire test outputs
├── pom.xml                           # Maven dependencies & plugins
└── README.md                         # Project documentation
```

---

## 🧪 Test Architecture Overview

### 🔹 RESTAssured + TestNG Tests

Test cases are modularized by API resource:

- `UserTest.java`: Validates CRUD for `/users`
- `ProductTest.java`: Tests for listing, updating `/products`
- `CartTest.java`: Add/Update/Delete items in `/carts`
- `ToDoTest.java`: Coverage of `/todos` APIs
- `PostTest.java`: POST creation & deletion logic

### 🔹 Scenarios Package

Located at:
```
src/test/java/com/dummyjson/users/Scenarios/
```
This package includes:
- `ValidScenario.java` — All happy path flows
- `InvalidScenario.java` — Tests for authentication errors, bad input, etc.

### 🔹 TestNG Factory Pattern (Structure Insight)

Although a dedicated `*Factory.java` file was **not found**, your architecture resembles a factory pattern by separating:

- **Test scenarios**: grouped and reusable
- **Test cases**: targeted for each resource (cart, user, etc.)
- **Data setup**: centralized in `Utils/` for reuse and scalability

We recommend optionally adding a dedicated **Test Factory** (e.g. `ScenarioFactory.java`) in the future to generate test objects dynamically with TestNG’s `@Factory` annotation.

---

## 🛠️ How to Run

### Postman Tests via Newman CLI

```bash
newman run postman/DummyJSON_collection.json \
  -e postman/DummyJSON_environment.json \
  -r cli,json,html
```

### Java Tests (with Maven)

```bash
mvn clean test
```

### View Allure Report

```bash
allure serve target/allure-results
```

Ensure you have Allure CLI installed:
```bash
# MacOS
brew install allure

# Windows (with Chocolatey)
choco install allure
```

---

## 📊 Reporting

- `Surefire Reports`: in `target/surefire-reports/`
- `Allure Results`: in `target/allure-results/`
- `Allure HTML`: in `allure-report/` or view via `allure serve`

---

## 🔄 Jenkins CI/CD Integration

This project can be integrated into Jenkins to:
- Run API tests (Postman + Java)
- Schedule tests (daily, nightly, per-commit)
- Auto-generate and publish Allure reports

Use a simple pipeline with:
```groovy
stage('Test Execution') {
    steps {
        sh 'mvn clean test'
    }
}
stage('Generate Allure Report') {
    steps {
        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
    }
}
```

---

## ⚙️ Pre-Requisites

- Java 8 or newer
- Maven
- Node.js + Newman (`npm install -g newman`)
- Allure CLI
- Jenkins (optional for CI)

---

## ✍️ Author

**Ahmed Magdy**  
Software Testing Engineer  
Feel free to connect or contribute!

---

## 📬 Feedback

Submit issues or PRs for improvements. Happy Testing 🚀
