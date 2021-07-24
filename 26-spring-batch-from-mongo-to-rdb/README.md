## Homework №14 (theme: "Spring Batch") Kryukov Sergey Alexandrovich on the course otus "Developer on Spring Framework"

### Task
* Based on Spring Batch, develop a procedure for migrating data from Mongo to RDB

### Job Requirements
* Database must have several entities and all of them must be migrated to the RDB
* Entities in Database must be relations (many-to-one and etc.) to each other and after migration these relations must be saved
* In entity, there can be objects that are not relationed with other tables (for example, the author for which there is no book) and such objects must also be migrated to the RDB

### Used technologies
* Spring boot
* spring-boot-starter-batch
* Spring Data MongoDB
* mongock
* spring-boot-starter-data-jpa
* H2 Embedded database
* Spring Shell
* Annotation-based configuration
* Lombok
* YML application configuration
* spring-batch-test
* spring-boot-starter-test
* JUnit 5
* AssertJ
* maven (maven-shade-plugin, maven-enforcer-plugin, etc)