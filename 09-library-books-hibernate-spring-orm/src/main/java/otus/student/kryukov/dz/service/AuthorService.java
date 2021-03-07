package otus.student.kryukov.dz.service;

import otus.student.kryukov.dz.domain.Author;

import java.util.Optional;

public interface AuthorService {

    Optional<Author> getByAuthor(String author);

    Optional<Author> getByAuthorId(Long authorId);

    void create(String author);

    void checkEmptyAuthor(String author);

}
