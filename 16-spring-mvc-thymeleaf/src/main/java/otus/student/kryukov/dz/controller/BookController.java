package otus.student.kryukov.dz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otus.student.kryukov.dz.converter.BookConverter;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.dto.BookDto;
import otus.student.kryukov.dz.service.AuthorService;
import otus.student.kryukov.dz.service.BookService;
import otus.student.kryukov.dz.service.GenreService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookConverter bookConverter;

    @GetMapping("/book/list")
    public String bookList(Model model) {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream()
                .map(bookConverter::convertToBookDto).collect(Collectors.toList());
        model.addAttribute("books", bookDtos);
        return "book/list";
    }

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/book/list";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEditPage(@PathVariable String id, Model model) {
        fillAuthorsGenres(model);
        Book bookObject = bookService.getByBookId(id);
        BookDto bookDto = bookConverter.convertToBookDto(bookObject);
        model.addAttribute("book", bookDto);
        return "book/create";
    }

    @PostMapping("/book/{id}/edit")
    public String bookEdit(BookDto bookDto) {
        bookService.update(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
        return "redirect:/book/list";
    }

    @GetMapping("/book/{id}/del")
    public String bookDel(@PathVariable String id) {
        bookService.delete(id);
        return "redirect:/book/list";
    }

    @GetMapping("/book/create")
    public String bookCreatePage(Model model) {
        fillAuthorsGenres(model);
        model.addAttribute("book", "");
        return "book/create";
    }

    @PostMapping("/book/create")
    public String bookCreate(BookDto bookDto) {
        bookService.create(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
        return "redirect:/book/list";
    }

    private Model fillAuthorsGenres(Model model) {
        Set<String> authors = authorService.getAllAuthors().stream()
                .map((s) -> s.getAuthor()).collect(Collectors.toSet());
        Set<String> genres = genreService.getAllGenres().stream()
                .map((s) -> s.getGenre()).collect(Collectors.toSet());
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return model;
    }
}