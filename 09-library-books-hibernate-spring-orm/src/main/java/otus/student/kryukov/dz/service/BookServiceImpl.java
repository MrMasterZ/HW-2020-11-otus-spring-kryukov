package otus.student.kryukov.dz.service;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.dao.BookDao;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.exception.SameEntityExistsException;
import otus.student.kryukov.dz.print.PrintService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final PrintService printService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @Override
    @Transactional
    public void create(String title, String author, String genre) {
        checkBook(title, author, genre);
        Author authorObject = prepareAuthor(author);
        Genre genreObject = prepareGenre(genre);
        Book bookObject = new Book(title, authorObject, genreObject);
        bookDao.create(bookObject);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByBookId(Long bookId) {
        Book bookObject = bookDao.getByBookId(bookId)
                .orElseThrow(() -> new NoSuchEntityException("no such book exists"));
        return bookObject;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    @Transactional
    public void update(Long bookId, String title, String author, String genre) {
        checkBook(title, author, genre);
        Book bookObject = getByBookId(bookId);
        Author authorObject = bookObject.getAuthorObject();
        Genre genreObject = bookObject.getGenreObject();
        if (authorObject.getAuthor() != author)
            authorObject = prepareAuthor(author);
        if (genreObject.getGenre() != genre)
            genreObject = prepareGenre(genre);
        bookObject = new Book(bookId, title, authorObject, genreObject);
        bookDao.update(bookObject);
    }

    @Override
    @Transactional
    public void delete(Long bookId) {
        bookDao.delete(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getByAllParams(String title, String author, String genre) {
        Optional<Book> optionalBook = Optional.empty();
        List<Book> bookList = bookDao.getByTitle(title);
        for (Book bookInList : bookList) {
            if (bookInList.getAuthorObject().getAuthor().equals(author) && bookInList.getGenreObject().getGenre().equals(genre)) {
                optionalBook = Optional.ofNullable(bookInList);
                break;
            }
        }
        return optionalBook;
    }

    private Author prepareAuthor(String author) {
        return authorService.getByAuthor(author)
                .orElseGet(() -> prepareCreateAuthor(author));
    }

    private Author prepareCreateAuthor(String author) {
        authorService.create(author);
        return authorService.getByAuthor(author).get();
    }

    private Genre prepareGenre(String genre) {
        return genreService.getByGenre(genre)
                .orElseGet(() -> prepareCreateGenre(genre));
    }

    private Genre prepareCreateGenre(String genre) {
        genreService.create(genre);
        return genreService.getByGenre(genre).get();
    }

    @Override
    public void drawAsciiTableBook(Book bookObject) {
        List<Book> books = new ArrayList();
        books.add(bookObject);
        drawAsciiTableBooks(books);
    }

    @Override
    public void drawAsciiTableBooks(List<Book> books) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("book_id", "title", "author", "genre");
        table.addRule();
        for (Book bookObject : books) {
            table.addRow(bookObject.getBookId(), bookObject.getTitle(), bookObject.getAuthorObject().getAuthor(), bookObject.getGenreObject().getGenre());
            table.addRule();
        }
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        printService.out(table.render());
    }

    @Override
    public void drawCommentsForBook(Book bookObject) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("comment_id", "comment");
        table.addRule();
        List<Comment> comments = commentService.getByBook(bookObject);
        table.setTextAlignment(TextAlignment.CENTER);
        for (Comment comment : comments) {
            AT_Row rowData = table.addRow(comment.getCommentId(), comment.getComment());
            table.addRule();
            rowData.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
            rowData.getCells().get(1).getContext().setTextAlignment(TextAlignment.LEFT);
        }
        table.getRenderer().setCWC(new CWC_LongestLine());
        printService.out(table.render());
    }

    private void checkBook(String title, String author, String genre) {
        checkEmptyBook(title, author, genre);
        checkIsSameBook(title, author, genre);
    }

    private void checkEmptyTitle(String title) {
        if (title == null || title.trim().equals(""))
            throw new EmptyEntityInsertException("title cannot be empty");
    }

    private void checkEmptyBook(String title, String author, String genre) {
        checkEmptyTitle(title);
        authorService.checkEmptyAuthor(author);
        genreService.checkEmptyGenre(genre);
    }

    private void checkIsSameBook(String title, String author, String genre) {
        if (!getByAllParams(title, author, genre).isEmpty())
            throw new SameEntityExistsException("already such a book in the database");
    }
}