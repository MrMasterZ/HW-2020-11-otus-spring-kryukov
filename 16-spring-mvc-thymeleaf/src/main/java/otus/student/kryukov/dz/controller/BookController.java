package otus.student.kryukov.dz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.dto.BookDto;
import otus.student.kryukov.dz.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/list")
    public String bookList(Model model) {
        List<Book> books = bookService.getAllBooks();
        List<BookDto> bookDtos = books.stream()
                .map(this::convertToBookDto).collect(Collectors.toList());
        model.addAttribute("books", bookDtos);
        return "book/list";
    }

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/book/list";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEditPage(@PathVariable String id, Model model) {
        Book bookObject = bookService.getByBookId(id);
        BookDto bookDto = convertToBookDto(bookObject);
        model.addAttribute("book", bookDto);
        return "/book/edit";
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
    public String bookCreatePage() {
        return "book/create";
    }

    @PostMapping("/book/create")
    public String bookCreate(BookDto bookDto) {
        bookService.create(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getGenre());
        return "redirect:/book/list";
    }

    private BookDto convertToBookDto(Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthorObject().getAuthor(),
                book.getGenreObject().getGenre()
        );
    }

    @ExceptionHandler(value= RuntimeException.class)
    public String emptyEntityError(Model model, RuntimeException ex) {
        model.addAttribute("errMessage", "ERROR: " + ex.getMessage());
        return "book/error";
    }
}