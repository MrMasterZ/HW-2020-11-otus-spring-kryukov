package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Book;
import reactor.core.publisher.Flux;


public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findAll();

    Flux<Book> findByTitle(@Param("title") String title);

}