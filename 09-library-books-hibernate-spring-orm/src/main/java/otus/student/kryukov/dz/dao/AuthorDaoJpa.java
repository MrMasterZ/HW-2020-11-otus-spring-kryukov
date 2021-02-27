package otus.student.kryukov.dz.dao;

import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> getByAuthor(String author) {
        Optional<Author> optionalAuthor;
        Query queryByAuthor = em.createQuery("select a from Author a where author = :author", Author.class);
        queryByAuthor.setParameter("author", author);
        try {
            optionalAuthor = Optional.ofNullable((Author) queryByAuthor.getSingleResult());
        } catch (Exception e) {
            optionalAuthor = Optional.empty();
        }
        return optionalAuthor;
    }

    @Override
    public Optional<Author> getByAuthorId(Long authorId) {
        return Optional.ofNullable(em.find(Author.class, authorId));
    }

    @Override
    public void create(Author authorObject) {
        em.persist(authorObject);
    }

}