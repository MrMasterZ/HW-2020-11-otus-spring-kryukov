package otus.student.kryukov.dz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.repository.CommentRepository;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.exception.NoSuchEntityException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void create(String comment, Book bookObject) {
        checkEmptyComment(comment);
        Comment commentObject = new Comment(comment, bookObject);
        commentRepository.save(commentObject);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getByCommentId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchEntityException("no such comment exists"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAllFetch();
    }

    @Override
    @Transactional
    public void update(Long commentId, String comment) {
        checkEmptyComment(comment);
        Comment commentObject = getByCommentId(commentId);
        commentObject.setComment(comment);
        commentRepository.save(commentObject);
    }

    @Override
    @Transactional
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getByCommentOrAllParams(String comment, Long bookId) {
        Optional<Comment> optionalComment = Optional.empty();
        List<Comment> commentList = commentRepository.findByComment(comment);
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