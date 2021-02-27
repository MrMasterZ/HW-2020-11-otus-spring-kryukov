package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Author;

public interface AuthorDao {

    Author getByAuthor(String author);

    Author getByAuthorId(long authorId);

    long createByAuthor(String author);

}
