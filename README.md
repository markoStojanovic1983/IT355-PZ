code
Markdown
# Personal Finance Tracker

A web application for managing personal incomes and expenses, developed for the IT355 - Advanced Web Technologies course. The application uses an in-memory data store, requiring no database setup to run.

## Features

*   **Dashboard:** A summary of total income, expenses, and current balance.
*   **Expense Management:** Full CRUD (Create, Read, Update, Delete) functionality for expenses.
*   **Income Management:** Full CRUD for income sources.
*   **Category Management:** Organize expenses by creating and managing custom categories. Full CRUD (Create, Read, Update, Delete) supported
*   **Transaction History:** A combined, chronological view of all income and expense transactions.
*   **Responsive Design:** A clean UI suitable for both desktop and mobile devices.

## Tech Stack

*   **Backend:** Java 17, Spring Boot 3, Spring MVC
*   **Frontend:** Thymeleaf, Bootstrap 5
*   **Build Tool:** Apache Maven
*   **Data Storage:** In-Memory (data is reset on application restart)

## Getting Started

Follow these instructions to get the project running on your local machine.

### Prerequisites

*   Java Development Kit (JDK) 17 or newer
*   Git

### Installation and Running

1.  **Clone the repository:**
    ```sh
    git clone https://your-repository-url/it355-pz.git
    cd it355-pz
    ```

2.  **Run the application using the Maven Wrapper:**
    *   On Windows:
        ```cmd
        .\mvnw spring-boot:run
        ```
    *   On Linux/macOS:
        ```sh
        ./mvnw spring-boot:run
        ```

3.  **Access the application:**
    Open your web browser and navigate to `http://localhost:8080`.

## Project Architecture

The application follows a Three-Tier (Layered) Architecture, which cleanly separates concerns.

*   **Presentation Layer:** Spring MVC Controllers and Thymeleaf views that handle user interaction.
*   **Business Logic Layer:** Services that contain the core application logic and business rules.
*   **Data Access Layer:** A `DataStorageService` that simulates a database using in-memory Java collections.

## Future Improvements

*   Implement data persistence with Spring Data JPA and a relational database (e.g., H2, PostgreSQL).
*   Add user authentication and authorization using Spring Security.
*   Introduce a reporting section with data visualization charts.
*   Develop a REST API to enable integration with other clients.
*   Add comprehensive unit and integration tests.

## Author

*   Marko Stojanović 1983
