package otus.student.kryukov.dz.service;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.dao.CommentDao;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.print.PrintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final PrintService printService;

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

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByBook(Book bookObject) {
        return commentDao.getByBook(bookObject);
    }

    @Override
    public void drawAsciiTableComment(Comment commentObject) {
        List<Comment> comments = new ArrayList();
        comments.add(commentObject);
        drawAsciiTableComments(comments);
    }

    @Override
    public void drawAsciiTableComments(List<Comment> comments) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        AT_Row rowHeader = table.addRow("comment_id", "comment", "book title");
        rowHeader.setTextAlignment(TextAlignment.CENTER);
        table.addRule();
        for (Comment commentObject : comments) {
            AT_Row rowData = table.addRow(commentObject.getCommentId(), commentObject.getComment(), commentObject.getBookObject().getTitle());
            rowData.setTextAlignment(TextAlignment.LEFT);
            rowData.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
            table.addRule();
        }
        table.getRenderer().setCWC(new CWC_LongestLine());
        printService.out(table.render());
    }

    private void checkEmptyComment(String comment) {
        if (comment == null || comment.trim().equals(""))
            throw new EmptyEntityInsertException("comment cannot be empty");
    }

}