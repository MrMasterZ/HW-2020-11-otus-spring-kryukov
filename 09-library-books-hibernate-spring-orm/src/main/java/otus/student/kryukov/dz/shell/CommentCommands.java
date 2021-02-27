package otus.student.kryukov.dz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.domain.Comment;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.print.PrintService;
import otus.student.kryukov.dz.service.BookService;
import otus.student.kryukov.dz.service.CommentService;

import java.util.List;

@ShellComponent
public class CommentCommands {
    private final CommentService commentService;
    private final BookService bookService;
    private final PrintService printService;

    public CommentCommands(CommentService commentService, BookService bookService, PrintService printService) {
        this.commentService = commentService;
        this.bookService = bookService;
        this.printService = printService;
    }

    @ShellMethod(value = "create comment-object in database", key = {"cc", "createComment"})
    public void createComment(@ShellOption String comment, @ShellOption Long bookId) {
        commentService.create(comment, bookService.getByBookId(bookId));
        printService.out("comment created:");
        getCommentByCommentOrAllParams(comment, bookId);
    }

    @ShellMethod(value = "get comment-object by comment_id", key = {"gcid", "getByCommentId"})
    public void getByCommentId(@ShellOption Long commentId) {
        Comment commentObject = commentService.getByCommentId(commentId);
        commentService.drawAsciiTableComment(commentObject);
    }

    @ShellMethod(value = "get all comment-objects", key = {"gac", "getAllComments"})
    public void getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        commentService.drawAsciiTableComments(comments);
    }

    @ShellMethod(value = "update comment-object in database", key = {"uc", "updateComment"})
    public void updateComment(@ShellOption Long commentId, @ShellOption String comment) {
        commentService.update(commentId, comment);
        printService.out("comment updated:");
        getByCommentId(commentId);
    }

    @ShellMethod(value = "delete comment-object from database", key = {"dc", "deleteComment"})
    public void deleteComment(@ShellOption Long commentId) {
        commentService.delete(commentId);
        printService.out("comment deleted");
    }

    @ShellMethod(value = "get comment-object by comment-string or all params", key = {"gc", "getCommentByCommentOrAllParams"})
    public void getCommentByCommentOrAllParams(@ShellOption String comment, @ShellOption Long bookId) {
        Comment commentObject = commentService.getByCommentOrAllParams(comment, bookId)
                .orElseThrow(() -> new NoSuchEntityException("no such comment exists"));
        commentService.drawAsciiTableComment(commentObject);
    }

    @ShellMethod(value = "get comment-object by book-object", key = {"gcb", "getCommentByBook"})
    public void getCommentByBook(@ShellOption Long bookId) {
        List<Comment> comments = commentService.getByBook(bookService.getByBookId(bookId));
        commentService.drawAsciiTableComments(comments);
    }
}