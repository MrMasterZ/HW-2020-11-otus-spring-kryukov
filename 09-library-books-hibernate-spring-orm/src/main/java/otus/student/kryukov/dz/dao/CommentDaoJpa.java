package otus.student.kryukov.dz.dao;

import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Comment commentObject) {
        em.persist(commentObject);
    }

    @Override
    public Optional<Comment> getByCommentId(Long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }

    @Override
    public List<Comment> getAllComments() {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Comment> queryComments = em.createQuery("select c from Comment c", Comment.class);
        queryComments.setHint("javax.persistence.fetchgraph", entityGraph);
        return queryComments.getResultList();
    }

    @Override
    public void update(Comment commentObject) {
        em.merge(commentObject);
    }

    @Override
    public void delete(Long commentId) {
        Optional<Comment> optionalComment = getByCommentId(commentId);
        if (optionalComment.isPresent()) em.remove(optionalComment.get());
    }

    @Override
    public List<Comment> getByComment(String comment) {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Comment> queryByComment = em.createQuery("select c from Comment c where comment = :comment", Comment.class);
        queryByComment.setHint("javax.persistence.fetchgraph", entityGraph);
        queryByComment.setParameter("comment", comment);
        return queryByComment.getResultList();
    }

    @Override
    public List<Comment> getByBook(Book bookObject) {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Comment> queryByBook = em.createQuery("select c from Comment c where bookObject = :bookObject", Comment.class);
        queryByBook.setHint("javax.persistence.fetchgraph", entityGraph);
        queryByBook.setParameter("bookObject", bookObject);
        return queryByBook.getResultList();
    }
}