package otus.student.kryukov.dz.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import otus.student.kryukov.dz.domain.AuthorMongo;
import otus.student.kryukov.dz.domain.BookMongo;
import otus.student.kryukov.dz.domain.GenreMongo;
import otus.student.kryukov.dz.repository.AuthorRepositoryMongo;
import otus.student.kryukov.dz.repository.BookRepositoryMongo;
import otus.student.kryukov.dz.repository.GenreRepositoryMongo;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "000", id = "dropDb", author = "stvort", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "authors", author = "MrMasterZ")
    public void authors(AuthorRepositoryMongo authorRepository) {
      authorRepository.save(new AuthorMongo("1", "Isaac Asimov"));
      authorRepository.save(new AuthorMongo("2", "James White"));
      authorRepository.save(new AuthorMongo("3", "Connie Willis"));
      authorRepository.save(new AuthorMongo("4", "Test"));     // this author has no books
  }

    @ChangeSet(order = "002", id = "genres", author = "MrMasterZ")
    public void genres(GenreRepositoryMongo genreRepository) {
        genreRepository.save(new GenreMongo("1", "fantasy"));
        genreRepository.save(new GenreMongo("2", "test"));      // there are no books of this genre
    }

    @ChangeSet(order = "003", id = "books", author = "MrMasterZ")
    public void books(BookRepositoryMongo bookRepository, AuthorRepositoryMongo authorRepository, GenreRepositoryMongo genreRepository) {
        bookRepository.save(
                new BookMongo(
                        "Prelude to the foundation",
                        authorRepository.findById("1").get(),
                        genreRepository.findById("1").get()
                )
        );
        bookRepository.save(
                new BookMongo(
                    "Martian path",
                    authorRepository.findById("1").get(),
                    genreRepository.findById("1").get()
                )
        );
        bookRepository.save(
                new BookMongo(
                        "Space hospital",
                        authorRepository.findById("2").get(),
                        genreRepository.findById("1").get()
                )
        );
        bookRepository.save(
                new BookMongo(
                        "Seeded moon",
                        authorRepository.findById("3").get(),
                        genreRepository.findById("1").get()
                )
        );
    }
}