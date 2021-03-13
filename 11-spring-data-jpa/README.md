## Homework №7 (theme: "White Magic Spring Data: Spring Data JPA") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Task
* Create an application that stores information about books in a library in a relational database (Spring Data JPA)
It is a refactoring of 09-library-books-hibernate-spring-orm

### Job Requirements
* Cover custom methods of repositories with tests, using H2 database
* Disable DDL via Hibernate
* @Transactional put only on service methods

### Used technologies
* Spring boot
* spring-boot-starter-data-jpa
* spring-boot-starter-test
* Annotation-based configuration
* Lombok
* YML application configuration
* Spring Shell
* H2 Embedded database for tests
* Liquibase
* JUnit 5
* @DataJpaTest, @Transactional
* asciitable
* maven (maven-shade-plugin, maven-enforcer-plugin, etc)