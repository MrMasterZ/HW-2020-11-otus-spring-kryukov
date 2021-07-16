package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByAuthor(@Param("author") String author);

}