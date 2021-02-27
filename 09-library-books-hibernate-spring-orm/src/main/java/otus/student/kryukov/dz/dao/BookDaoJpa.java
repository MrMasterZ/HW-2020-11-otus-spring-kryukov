package otus.student.kryukov.dz.dao;

import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Book;

import javax.persistence.*;
import java.util.*;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book bookObject) {
        em.persist(bookObject);
    }

    @Override
    public Optional<Book> getByBookId(Long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Override
    public List<Book> getAllBooks() {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-entity-graph");
        TypedQuery<Book> queryBooks = em.createQuery("select b from Book b join fetch b.genreObject", Book.class);
        queryBooks.setHint("javax.persistence.fetchgraph", entityGraph);
        return queryBooks.getResultList();
    }

    @Override
    public void update(Book bookObject) {
        em.merge(bookObject);
    }

    @Override
    public void delete(Long bookId) {
        Optional<Book> optionalBook = getByBookId(bookId);
        if (optionalBook.isPresent()) em.remove(optionalBook.get());
    }

    @Override
    public List<Book> getByTitle(String title) {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-entity-graph");
        TypedQuery<Book> queryByTitle = em.createQuery("select b from Book b join fetch b.genreObject where title = :title", Book.class);
        queryByTitle.setHint("javax.persistence.fetchgraph", entityGraph);
        queryByTitle.setParameter("title", title);
        return queryByTitle.getResultList();
    }

}