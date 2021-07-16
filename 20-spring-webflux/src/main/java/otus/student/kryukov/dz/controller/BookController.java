package otus.student.kryukov.dz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import otus.student.kryukov.dz.checker.BookChecker;
import otus.student.kryukov.dz.converter.BookConverter;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.domain.dto.BookDto;
import otus.student.kryukov.dz.exception.SameEntityExistsException;
import otus.student.kryukov.dz.repository.AuthorRepository;
import otus.student.kryukov.dz.repository.BookRepository;
import otus.student.kryukov.dz.repository.GenreRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookConverter bookConverter;
    private final BookChecker bookChecker;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Transactional
    @GetMapping("/book")
    public Flux<BookDto> bookList() {
        bookRepository.findAll().subscribe(System.out::println);
        return bookRepository.findAll().map(bookConverter::convertToBookDto);
    }

    @Transactional
    @PatchMapping("/book/{id}")
    public Mono<BookDto> bookEdit(@PathVariable String id, @RequestBody BookDto bookDto) {
        checkBook(bookDto);
        return bookRepository.findById(id)
                .flatMap(bookObject -> {
                    bookObject.setBookId(id);
                    bookObject.setTitle(bookDto.getTitle());
                    return Mono.just(bookObject);})
                .flatMap(bookObject -> getOrCreateAuthor(bookObject, bookDto.getAuthor()))
                .flatMap(bookObject -> getOrCreateGenre(bookObject, bookDto.getGenre()))
                .flatMap(bookRepository::save)
                .map(bookConverter::convertToBookDto);
    }

    @Transactional
    @DeleteMapping("/book/{id}")
    public Mono<String> bookDel(@PathVariable String id) {
        bookRepository.deleteById(id).subscribe();
        return Mono.just("ok");
    }

    @Transactional
    @PostMapping("/book")
    public Mono<BookDto> bookCreate(@RequestBody BookDto bookDto) {
        checkBook(bookDto);
        return Mono.just(new Book())
                .flatMap(bookObject -> {
                    bookObject.setTitle(bookDto.getTitle());
                    return Mono.just(bookObject);})
                .flatMap(bookObject -> getOrCreateAuthor(bookObject, bookDto.getAuthor()))
                .flatMap(bookObject -> getOrCreateGenre(bookObject, bookDto.getGenre()))
                .flatMap(bookRepository::save)
                .map(bookConverter::convertToBookDto);
    }

    private Mono<Author> createAuthor(String author) {
        return authorRepository.save(new Author(author));
    }

    private Mono<Genre> createGenre(String genre) {
        return genreRepository.save(new Genre(genre));
    }

    private Book checkIsSameBook(Book book, String author, String genre) {
        if (book.getAuthorObject().getAuthor().equals(author) && book.getGenreObject().getGenre().equals(genre))
            throw new SameEntityExistsException("already such a book in the database");
        return book;
    }

    private Mono<Book> getOrCreateAuthor(Book bookObject, String author) {
        return authorRepository.findByAuthor(author)
                .timeout(Duration.ofSeconds(5))
                .switchIfEmpty(createAuthor(author))
                .map(authorObject -> {
                    bookObject.setAuthorObject(authorObject);
                    return bookObject;
                });
    }

    private Mono<Book> getOrCreateGenre(Book bookObject, String genre) {
        return genreRepository.findByGenre(genre)
                .timeout(Duration.ofSeconds(5))
                .switchIfEmpty(createGenre(genre))
                .map(genreObject -> {
                    bookObject.setGenreObject(genreObject);
                    return bookObject;
                });
    }

    private void checkBook(BookDto bookDto) {
        String title = bookDto.getTitle();
        String author = bookDto.getAuthor();
        String genre = bookDto.getGenre();
        bookChecker.checkEmpty(title, author, genre);
        bookRepository.findByTitle(title)
                .map(bookObject -> checkIsSameBook(bookObject, author, genre))
                .subscribe();
    }
}