## Homework №13 (theme: "Spring Security: authorization (based on URL, methods and ACL)") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Task
* Create CRUD application with Web UI and database and authentication using Spring Security and with authorization (URL-based, method restriction, and ACL)
It is a continuation of 23-spring-security-authentication (Spring Data Mongo replace with Spring Data JPA )

### Job Requirements
* 1) Using acl, restrict the ability to view books when a user enters the main page:
    1.1) all users see all books
     1.2) Sergey sees all the books except "Martian path"
* 2) Restrict controller methods using prePost and secured annotations: 
     only admin can delete and create books
* 3) Ограничить методы контроллера с помощью ограничений URL-адресов:
     Deny all users except admin from editing the workbook


### Used technologies
* spring-boot-starter-security 
* spring-security-test
* Spring boot
* Spring MVC
* spring-boot-starter-web
* spring-boot-starter-test
* Annotation-based configuration
* Thymeleaf
* CSS
* Lombok
* YML application configuration
* JUnit 5
* AssertJ
* @Transactional
* maven (maven-shade-plugin, maven-enforcer-plugin, etc)
* spring-boot-starter-data-jpa
* H2 Embedded database for tests
* Liquibase
* @DataJpaTest