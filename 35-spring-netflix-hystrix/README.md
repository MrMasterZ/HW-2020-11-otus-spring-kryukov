## Homework №18 (theme: "Hystrix") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Task
* It is a continuation of 32-docker
* Wrap all external calls in Hystrix, Hystrix Javanica.

### Used technologies
* Hystrix
* Docker
* Dockerfile
* docker-compose
* spring-boot-starter-actuator
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
* maven
* spring-boot-starter-data-jpa
* Postgresql database for production
* H2 Embedded database for tests
* Liquibase
* @DataJpaTest

### Command to build library-app docker-image
* docker build -t 35-spring-netflix-hystrix-library-app:v1 ./library-app

### Command to create (based on docker-image) and start docker-containers (library-app and postgres)
* docker-compose up

### Combining two previous commands (build library-app docker-image, create and start docker-containers (library-app and postgres))
* docker-compose --file docker-compose-build.yml up

### Command to check Hystrix
* docker stop 35-hystrix-postgres
