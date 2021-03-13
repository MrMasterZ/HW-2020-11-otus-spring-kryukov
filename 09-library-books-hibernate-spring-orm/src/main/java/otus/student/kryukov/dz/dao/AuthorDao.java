package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Author;

import java.util.Optional;

public interface AuthorDao {

    Optional<Author> getByAuthor(String author);

    Optional<Author> getByAuthorId(Long authorId);

    void create(Author authorObject);

}