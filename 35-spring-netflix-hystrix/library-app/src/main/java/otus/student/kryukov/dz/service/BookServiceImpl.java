package otus.student.kryukov.dz.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.exception.SameEntityExistsException;
import otus.student.kryukov.dz.exception.ServerException;
import otus.student.kryukov.dz.repository.BookRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private Map<Long, Book> cache = new HashMap<>();

    @Override
    @HystrixCommand(commandKey="updateBooks", fallbackMethod = "throwException")
    public void create(String title, String author, String genre) {
        checkBook(title, author, genre);
        Author authorObject = prepareAuthor(author);
        Genre genreObject = prepareGenre(genre);
        Book bookObject = new Book(title, authorObject, genreObject);
        bookRepository.save(bookObject);
    }

    @Override
    @HystrixCommand(commandKey = "getBooks", fallbackMethod = "getBookFromCache")
    public Book getByBookId(Long bookId) {
        Book bookObject = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchEntityException("no such book exists"));
        cache.put(bookId, bookObject);
        return bookObject;
    }

    @Override
    @HystrixCommand(commandKey = "getBooks", fallbackMethod = "getBooksFromCache")
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        updateCache(books);
        return books;
    }

    @Override
    @HystrixCommand(commandKey="updateBooks", fallbackMethod = "throwException")
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
        bookRepository.save(bookObject);
    }

    @Override
    @HystrixCommand(commandKey="updateBooks", fallbackMethod = "throwException")
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @HystrixCommand(commandKey = "getBooks", fallbackMethod = "getBookFromCacheByParams")
    public Optional<Book> getByAllParams(String title, String author, String genre) {
        Optional<Book> optionalBook = Optional.empty();
        List<Book> bookList = bookRepository.findByTitle(title);
        for (Book bookInList : bookList) {
            if (bookInList.getAuthorObject().getAuthor().equals(author) && bookInList.getGenreObject().getGenre().equals(genre)) {
                optionalBook = Optional.ofNullable(bookInList);
                break;
            }
        }
        return optionalBook;
    }

    @Override
    @HystrixCommand(commandKey = "getBooks", fallbackMethod = "throwException")
    public Boolean existsByAuthorId(Long authorId){
        Author authorObject = authorService.getByAuthorId(authorId)
                .orElseThrow(() -> new NoSuchEntityException("no such author exists"));
        if(bookRepository.findByAuthorObject(authorObject).size() == 0)
            return false;
        else return true;
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

    private void throwException(Long id) {
        throwException();
    }

    private void throwException(Long id, String title, String author, String genre) {
        throwException();
    }

    private void throwException(String title, String author, String genre) {
        throwException();
    }

    private void throwException() {
        throw new ServerException("Temporary server problems, try a little later, please");
    }

    private Book getBookFromCache(Long bookId) {
        return cache.get(bookId);
    }

    private List<Book> getBooksFromCache() {
        return new ArrayList<>(cache.values());
    }

    private void updateCache(List<Book> books) {
        cache = books.stream().collect(Collectors.toMap(
                Book::getId, book -> book
        ));
    }

    private Optional<Book> getBookFromCacheByParams(String title, String author, String genre) {
        Optional<Book> optionalBook = Optional.empty();
        List<Book> bookList = new ArrayList<>(cache.values());
        for (Book bookInList : bookList) {
            if (bookInList.getTitle().equals(title) && bookInList.getAuthorObject().getAuthor().equals(author) && bookInList.getGenreObject().getGenre().equals(genre)) {
                optionalBook = Optional.ofNullable(bookInList);
                break;
            }
        }
        return optionalBook;
    }
}