## Homework №17 (theme: "Docker") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Task
* It is a continuation of 30-spring-boot-actuator
* Wrapped application in docker-container, using Dockerfile and docker-compose

### Used technologies
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
* @Transactional
* maven (maven-shade-plugin, maven-enforcer-plugin, etc)
* spring-boot-starter-data-jpa
* Postgresql database for production
* H2 Embedded database for tests
* Liquibase
* @DataJpaTest

### Command to build library-app docker-image
* docker build -t 32-docker-library-app:v1 ./library-app

### Command to create (based on docker-image) and start docker-containers (library-app and postgres)
docker-compose up

### Combining two previous commands (build library-app docker-image, create and start docker-containers (library-app and postgres))
docker-compose --file docker-compose-build.yml up