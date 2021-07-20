package otus.student.kryukov.dz.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private String genre;

    public BookDto(Long id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}