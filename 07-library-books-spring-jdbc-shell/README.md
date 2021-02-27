## Homework №5 (theme: "DAO on Spring JDBC") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Task
* Create an application that stores information about books in a library in a relational database (Spring JDBC)

### Job Requirements
* Use Spring JDBC and spring-boot-starter-jdbc to connect to relational databases 
* Provide tables of authors, books and genres.
* Many-to-One Mappings (book has one author and a genre). 
* UI do on Spring Shell (CRUD books are mandatory, operations with authors and genres - as will be convenient).
* Script for creating of tables and script for inserting data into tables must be started automatically using spring-boot-starter-jdbc.
* Cover with tests as much as possible.

### Used technologies
* Spring boot
* Annotation-based configuration
* Lombok
* YML application configuration
* Spring Shell
* H2 Embedded database for tests
* Spring JDBC
* JUnit 5
* @JdbcTest, @Transactional, @DirtiesContext
* spring-boot-starter-jdbc
* asciitable
* maven (maven-shade-plugin, maven-enforcer-plugin, etc)
* SQL