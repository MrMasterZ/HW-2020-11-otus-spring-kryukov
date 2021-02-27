package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    void create(Comment commentObject);

    Optional<Comment> getByCommentId(Long commentId);

    List<Comment> getAllComments();

    void update(Comment commentObject);

    void delete(Long commentId);

    List<Comment> getByComment(String comment);

    List<Comment> getByBook(Book bookObject);
}