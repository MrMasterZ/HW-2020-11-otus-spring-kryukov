## Homework №5 (theme: "DAO on Spring JDBC") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Job Condition (functional description)
* Create an application that stores information about books in a library in a relational database
* use Spring JDBC and spring-boot-starter-jdbc to connect to relational databases 

### Requirements for job
* 1) Provide tables of authors, books and genres.
* 2) Many-to-One Mappings (book has one author and a genre). 
* 3) UI do on Spring Shell (CRUD books are mandatory, operations with authors and genres - as will be convenient).
* 4) Script for creating of tables and script for inserting data into tables must be started automatically using spring-boot-starter-jdbc.
* 5) Cover with tests as much as possible.

### Used technologies
* Spring boot
* Annotation-based configuration
* Lombok
* YML application configuration
* Spring Shell
* H2 Embedded database for tests
* Spring JDBC
* JUnit 5
* @JdbcTest, @Transactional, @DirtiesContext annotation in tests
* asciitable
* maven
* SQL
