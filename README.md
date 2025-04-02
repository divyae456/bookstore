# BookStore API Automation Framework

### This is a TestNG-based API automation framework for testing the BookStore API endpoints.

# Features

- TestNG test framework
- REST Assured for API testing
- Extent Reports for reporting
- Maven for dependency management
- Request chaining implementation
- Positive and negative test scenarios
- CRUD operations testing

# Project Structure

```
src/
├── main/java/     # Framework 
├── test/java/     # Test cases
└── resources/     # Configuration files
```

# Prerequisites

- Java 11 or higher
- Maven
- IDE (IntelliJ IDEA recommended)

# Setup

1. Clone the repository
2. Import as Maven project
3. Update config.properties with required configuration
4. Run tests using: `mvn clean test`

# Test Execution

Run all tests:
```bash
mvn clean test
```

Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

# Reporting

- Extent reports are generated in `test-output/ExtentReport.html`
- TestNG reports are generated in `target/surefire-reports`

# Test Coverage

1. Authentication
   - User signup
   - User login
   - Invalid credentials handling

2. Book Operations
   - Create new book
   - Get book by ID
   - Get all books
   - Update book
   - Delete book

## Contributing

1. Fork the project
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request 