# Point of Sales System (Simulation)
This project is a backend simulation for a point-of-sale system using Java Spring Boot, implementing features such as category management, product management, shopping cart functionality, and payment processing.

## Features

- [Category Management] - A feature used by administrators to add, update, and delete category data.
- [Product Management] - A feature used by administrators to add, update, and delete product data.
- [Shopping Cart] - A feature that allows users or customers to select products they wish to purchase.
- [Checkout and Payment Processing] - A feature that enables users or customers to complete their purchases and make payments.

## Tech

This application is built using:

- [Java] - The primary programming language used in the development of this application.
- [Spring Boot] - A Java-based framework used for building web applications and REST APIs quickly and efficiently. Spring Boot provides out-of-the-box features such as auto-configuration, dependency management, and an embedded server.
- [Spring Security] - A security module within the Spring ecosystem that offers features such as authentication, authorization, and protection against attacks like CSRF (Cross-Site Request Forgery). It is used to secure the application, particularly the REST API endpoints.
- [RESTful API] - An HTTP-based API architecture standard for managing application resources. RESTful APIs are used in this application to facilitate communication between the client and server, supporting HTTP methods like GET, POST, PUT, and DELETE.
- [PostgreSQL] - A reliable, feature-rich open-source relational database management system. PostgreSQL is used to store application data, including information about categories, products, carts, and payment transactions.
- [Kafka] - A distributed streaming platform used for real-time data processing. Kafka can be utilized in this application to manage asynchronous communication between the main POS system and the Payment Gateway service.
- [Postman] - A tool for testing and documenting APIs. In this application, Postman is used to ensure that REST API endpoints function as expected.

## REST API Specification

URL: http://localhost:8082
#### Authentication:
| Endpoint | HTTP | Description |
| ------ | ------ | ------ |
| /api/v1/auth/admin-register | POST | Admin Registration |
| /api/v1/auth/admin-authenticate | POST | Admin Authentication |
| /api/v1/auth/user-register | POST | User Registration |
| /api/v1/auth/user-authenticate | POST | User Authentication |

#### Category:
| Endpoint | HTTP | Description |
| ------ | ------ | ------ |
| /api/v1/admin/category | POST | Add new category |
| /api/v1/admin/category?id=... | DELETE | Delete category by id |
| /api/v1/auth/category?id=... | PUT | Update category by id |

#### Product:
| Endpoint | HTTP | Description |
| ------ | ------ | ------ |
| /api/v1/admin/product | POST | Add new product |
| /api/v1/admin/product?id=... | DELETE | Delete product by id |
| /api/v1/auth/product?id=... | PUT | Update product by id |

#### Payment Method:
| Endpoint | HTTP | Description |
| ------ | ------ | ------ |
| /api/v1/admin/payment-method | POST | Add new payment method |
| /api/v1/admin/payment-method?id=... | DELETE | Delete payment method by id |

#### Shopping Cart:
| Endpoint | HTTP | Description |
| ------ | ------ | ------ |
| /api/v1/user/shopping-cart | GET | Get all the items available for purchase by customers |
| /api/v1/user/shopping-cart/{id} | GET | Get item by category id |
| /api/v1/user/shopping-cart | POST | Add new shopping cart |

#### Checkout:
| Endpoint | HTTP | Description |
| ------ | ------ | ------ |
| /api/v1/user/checkout | POST | Checkout process |

URL: http://localhost:8081
#### Payment Gateway:
| Endpoint | HTTP | Description |
| ------ | ------ | ------ |
| /api/payment-process?number=...&totalPrice=... | GET | Payment process |
| /api/payment-status?number=...&totalPrice=... | GET | Get payment stastus and send notification to merchant |

## How to Run

- Clone this repository
- Make sure you are using JDK 23 and Maven 3.x
- Make sure you have installed and configured Kafka. The instructions for installation and configuration are available at the following link https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/.
- Make sure you have connected the project to the PostgreSQL database.
- Run both projects (Point of Sales and Payment Gateway) using IntelliJ, Visual Studio Code, or any other application that supports Java.
- You can then test the available endpoints using Postman or other applications that support API testing.
