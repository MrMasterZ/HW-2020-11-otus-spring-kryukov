package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Author;
import reactor.core.publisher.Mono;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Mono<Author> findByAuthor(@Param("author") String author);

}