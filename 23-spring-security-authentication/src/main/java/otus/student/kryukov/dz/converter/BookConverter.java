package otus.student.kryukov.dz.converter;

import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.dto.BookDto;

public interface BookConverter {

    BookDto convertToBookDto(Book book);

}
