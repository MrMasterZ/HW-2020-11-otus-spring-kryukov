package otus.student.kryukov.dz.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.repository.AuthorRepository;
import otus.student.kryukov.dz.repository.BookRepository;
import otus.student.kryukov.dz.repository.GenreRepository;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "authors", author = "MrMasterZ")
    public void authors(AuthorRepository authorRepository) {
        authorRepository.save(new Author("1", "Isaac Asimov"));
        authorRepository.save(new Author("2", "James White"));
        authorRepository.save(new Author("3", "Connie Willis"));
    }

    @ChangeSet(order = "002", id = "genres", author = "MrMasterZ")
    public void genres(GenreRepository genreRepository) {
        genreRepository.save(new Genre("1", "fantasy"));
    }

    @ChangeSet(order = "003", id = "books", author = "MrMasterZ")
    public void books(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        bookRepository.save(
                new Book(
                        "Prelude to the foundation",
                        authorRepository.findById("1").get(),
                        genreRepository.findById("1").get()
                )
        );
        bookRepository.save(
                new Book(
                    "Martian path",
                    authorRepository.findById("1").get(),
                    genreRepository.findById("1").get()
                )
        );
        bookRepository.save(
                new Book(
                        "Space hospital",
                        authorRepository.findById("2").get(),
                        genreRepository.findById("1").get()
                )
        );
        bookRepository.save(
                new Book(
                        "Seeded moon",
                        authorRepository.findById("3").get(),
                        genreRepository.findById("1").get()
                )
        );
    }
}
