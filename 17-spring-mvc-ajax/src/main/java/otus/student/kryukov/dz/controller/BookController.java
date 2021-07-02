package otus.student.kryukov.dz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import otus.student.kryukov.dz.converter.BookConverter;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.dto.BookDto;
import otus.student.kryukov.dz.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookConverter bookConverter;

    @GetMapping("/book")
    public List<BookDto> bookList() {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream()
                .map(bookConverter::convertToBookDto).collect(Collectors.toList());
        return bookDtos;
    }

    @PatchMapping("/book/{id}")
    public BookDto bookEdit(@RequestBody BookDto bookDto) {
        bookService.update(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
        Book bookObject = bookService.getByBookId(bookDto.getId());
        BookDto newBookDto = bookConverter.convertToBookDto(bookObject);
        return newBookDto;
    }

    @DeleteMapping("/book/{id}")
    public String bookDel(@PathVariable String id) {
        bookService.delete(id);
        return "ok";
    }

    @PostMapping("/book")
    public BookDto bookCreate(@RequestBody BookDto bookDto) {
        bookService.create(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
        Optional<Book> bookOptional = bookService.getByAllParams(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
        Book bookObject = null;
        if (!bookOptional.isEmpty())  // todo иначе обработка ошибок
            bookObject = bookOptional.get();
        BookDto newBookDto = bookConverter.convertToBookDto(bookObject);
        return newBookDto;
    }
}