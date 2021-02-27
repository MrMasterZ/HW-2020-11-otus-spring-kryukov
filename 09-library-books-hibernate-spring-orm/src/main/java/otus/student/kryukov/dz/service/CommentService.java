package otus.student.kryukov.dz.service;

import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void create(String comment, Book bookObject);

    Comment getByCommentId(Long commentId);

    List<Comment> getAllComments();

    void update(Long commentId, String comment);

    void delete(Long commentId);

    Optional<Comment> getByCommentOrAllParams(String comment, Long bookId);

    List<Comment> getByBook(Book bookObject);

    void drawAsciiTableComment(Comment commentObject);

    void drawAsciiTableComments(List<Comment> comments);

}