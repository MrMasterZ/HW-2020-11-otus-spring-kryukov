## Homework №11 (theme: "Spring WebFlux") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Task
* Create CRUD application with Web UI and AJAX and @RestController on reactive stack
It is a refactoring of 17-spring-mvc-ajax

### Job Requirements
* use Spring Web Flux
* use Reactive Spring Data Repositories

### Used technologies
* Spring boot
* Spring Data MongoDB
* Spring Web Flux
* Reactive Spring Data Repositories (spring-boot-starter-data-mongodb-reactive)
* spring-boot-starter-test
* Annotation-based configuration
* CSS
* javascript
* AJAX
* Lombok
* YML application configuration
* JUnit 5
* AssertJ
* @Transactional
* maven (maven-shade-plugin, maven-enforcer-plugin, etc)

### Command to init mongo container image and start it (You need to have docker installed)
* docker run -d -p 127.0.0.1:27016:27017 --name mongo mongo