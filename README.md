# REST API for an E-Commerce Application

Main target of ReadingIsGood is to deliver books from its one centralized warehouse to their customers within the same day. That is why stock consistency is the first priority for their vision operations.

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Spring Security
* Hibernate
* H2 DB

## Modules

* Login Module
* Customer Module
* Product Module
* Order Module



## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](E-Commerce-Backend\src\main\resources\application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8080
    spring.h2.console.enabled=true
    spring.datasource.url=jdbc:h2:mem:root;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto=update

```

## API Root Endpoint

`https://localhost:8080/`

`http://localhost:8080/swagger-ui/index.html`


### All Endpoints

* `POST /api/register`                                        : Register a new customer
* `POST /api/authenticate`                                    : Login
* `GET /api/account`                                          : Get the current user.
* `GET /api/orders/customer/{userId}`                         : Will query all orders of the customer
* `POST /api/orders`                                          : Placing a new order
* `GET /api/books/{id}`                                       : Tracking the stock of books
* `GET /api/orders/customer/{userId}`                         : List all orders of the customer
* `GET /api/order-items/order/{orderId}`                      : Viewing the order details
* `POST /api/books`                                           : Persist new book
* `PUT /api/books/{id}`                                       : update book’s information
* `GET /api/orders/{id}`                                      : query order by Id
* `GET /api/orders/strat-date/{startDate}/end-date/{endDate}` : List orders by date interval




