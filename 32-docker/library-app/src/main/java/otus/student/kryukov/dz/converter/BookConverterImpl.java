package otus.student.kryukov.dz.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.dto.BookDto;

@Service
@RequiredArgsConstructor
public class BookConverterImpl implements BookConverter {
    @Override
    public BookDto convertToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthorObject().getAuthor(),
                book.getGenreObject().getGenre()
        );
    }
}
