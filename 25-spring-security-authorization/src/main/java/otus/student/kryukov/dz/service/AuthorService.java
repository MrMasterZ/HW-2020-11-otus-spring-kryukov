package otus.student.kryukov.dz.service;

import otus.student.kryukov.dz.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> getByAuthor(String author);

    List<Author> getAllAuthors();

    Optional<Author> getByAuthorId(Long authorId);

    void create(String author);

    void checkEmptyAuthor(String author);

    void delete(String author);

}
