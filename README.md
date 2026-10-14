# StockFlow - Inventory Management System

**StockFlow** is a robust desktop application designed for efficient inventory management and point-of-sale operations. Engineered with a strong focus on clean architecture, the system provides a reliable, scalable, and secure solution for tracking products, managing stock, and handling user roles.

## 🚀 Key Features

*   **Role-Based Access Control (RBAC):** Secure authentication system distinguishing between Administrators (full access to configurations and user management) and Cashiers (restricted to operational tasks).
*   **Complete CRUD Operations:** Seamlessly add, search, update, and delete products and users from the database.
*   **Dynamic Search & Filtering:** Real-time product querying using multiple criteria (ID, Barcode, Name).
*   **Intuitive GUI:** User-friendly interface built with Java Swing, ensuring a smooth user experience.

## 🛠️ Architecture & Tech Stack

This project strictly adheres to the **MVC (Model-View-Controller)** architectural pattern, ensuring a clear separation of concerns:
*   **Language:** Java (JDK 8+)
*   **GUI Framework:** Java Swing
*   **Database:** MySQL
*   **Data Access:** JDBC & DAO (Data Access Object) Pattern
*   **Documentation:** Fully documented with Javadoc standard.

The implementation of the **DAO pattern** guarantees that all direct database interactions are isolated from the business logic and user interface, promoting high maintainability and security against SQL injection through `PreparedStatement`.

## ⚙️ How to Run
1. Set up a local MySQL server.
2. Execute the SQL script provided in the `Base de Datos` folder to create the tables.
3. Download the latest `.jar` from the **Releases** section.
4. Run the application (ensure your MySQL server is active).

## 👨‍💻 Author

**Marco Almendarez**  
*Software Development Student | Java Enthusiast*  
Connect with me on [LinkedIn](https://www.linkedin.com/in/marcovini13)
