package otus.student.kryukov.dz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.dao.CommentDao;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.exception.NoSuchEntityException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    @Transactional
    public void create(String comment, Book bookObject) {
        checkEmptyComment(comment);
        Comment commentObject = new Comment(comment, bookObject);
        commentDao.create(commentObject);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getByCommentId(Long commentId) {
        return commentDao.getByCommentId(commentId)
                .orElseThrow(() -> new NoSuchEntityException("no such comment exists"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    @Override
    @Transactional
    public void update(Long commentId, String comment) {
        checkEmptyComment(comment);
        Comment commentObject = getByCommentId(commentId);
        commentObject.setComment(comment);
        commentDao.update(commentObject);
    }

    @Override
    @Transactional
    public void delete(Long commentId) {
        commentDao.delete(commentId);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getByCommentOrAllParams(String comment, Long bookId) {
        Optional<Comment> optionalComment = Optional.empty();
        List<Comment> commentList = commentDao.getByComment(comment);
        int commentListSize = commentList.size();
        if (commentListSize == 1) optionalComment = Optional.ofNullable(commentList.get(0));
        else {
            for (Comment commentInList : commentList) {
                if (commentInList.getBookObject().getBookId() == bookId) {
                    optionalComment = Optional.ofNullable(commentInList);
                    break;
                }
            }
        }
        return optionalComment;
    }

    private void checkEmptyComment(String comment) {
        if (comment == null || comment.trim().equals(""))
            throw new EmptyEntityInsertException("comment cannot be empty");
    }

}