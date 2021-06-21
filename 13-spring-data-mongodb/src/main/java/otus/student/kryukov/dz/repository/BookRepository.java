package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByTitle(@Param("title") String title);

    List<Book> findByAuthorObject(@Param("authorObject") Author authorObject);

}