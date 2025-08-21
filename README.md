ESTORE – E-Commerce Web Application

A Spring Boot based e-commerce web application with Thymeleaf, Spring Security (JWT authentication), JPA/Hibernate, and MySQL.
This project demonstrates a full-stack workflow for managing products, users, authentication, and persistent shopping carts.

🚀 Features

Authentication & Authorization

Secure login & registration with Spring Security

JWT-based authentication

Role-based access (Admin & User)

Product Management

Admin can add, update, delete products

Users can view product listings & details

Cart Management

Add products to cart

Persistent cart stored in DB

Update/remove items

User Dashboard

View personal cart

Manage account details

Frontend

Thymeleaf-based UI

Responsive design with basic CSS

🏗️ Tech Stack

Backend: Spring Boot, Spring Security, Spring Data JPA, Hibernate

Frontend: Thymeleaf, HTML, CSS

Database: MySQL

Build Tool: Maven

Language: Java 17+

Project Structure
src/main/java/com/ecommerce/estore
 ├── config/        # Security & config classes
 ├── controller/    # MVC & REST controllers
 ├── model/         # Entities (User, Product, CartItem)
 ├── repository/    # Spring Data JPA repositories
 └── service/       # Business logic

src/main/resources
 ├── static/css     # Stylesheets
 └── templates      # Thymeleaf templates


Installation & Setup
1️⃣ Clone the repository
git clone https://github.com/<your-username>/estore.git
cd estore

2️⃣ Configure the Database

Update src/main/resources/application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/estore
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3️⃣ Build & Run
mvn spring-boot:run


Application will run on:
👉 http://localhost:8080/

🔑 Default Roles

Admin – Manage products (CRUD)

User – Browse products, manage cart

📸 Screenshots

(You can add login page, product listing, cart page screenshots here)

📌 Future Enhancements

Payment gateway integration

Order history & invoice generation

Product categories & search functionality

REST API for mobile integration
