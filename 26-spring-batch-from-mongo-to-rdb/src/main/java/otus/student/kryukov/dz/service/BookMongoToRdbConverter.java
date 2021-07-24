package otus.student.kryukov.dz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.*;
import otus.student.kryukov.dz.repository.AuthorRepositoryRdb;
import otus.student.kryukov.dz.repository.GenreRepositoryRdb;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BookMongoToRdbConverter implements BookConverterService {
    static HashMap<String, Long> authorIdMap = new HashMap<>();
    static HashMap<String, Long> genreIdMap = new HashMap<>();
    static Long authorCounter = 0L;
    static Long genreCounter = 0L;
    private final AuthorRepositoryRdb authorRepositoryRdb;
    private final GenreRepositoryRdb genreRepositoryRdb;

    @Override
    public BookRdb mongoToRdbConvert(BookMongo bookMongo){
        AuthorMongo authorMongo = bookMongo.getAuthorObject();
        GenreMongo genreMongo = bookMongo.getGenreObject();
        BookRdb bookRdb = new BookRdb(bookMongo.getTitle(),
                                      new AuthorRdb(identifyAuthor(authorMongo),
                                                    authorMongo.getAuthor()),
                                      new GenreRdb(identifyGenre(genreMongo),
                                                   genreMongo.getGenre())
                        );
        return bookRdb;
    }

    private Long identifyAuthor(AuthorMongo authorMongo) {
        String authorMongoId = authorMongo.getAuthorId();
        if(authorIdMap.containsKey(authorMongoId)) {
            return authorIdMap.get(authorMongoId);
        }
        authorCounter++;
        authorIdMap.put(authorMongoId, authorCounter);
        authorRepositoryRdb.save(
                new AuthorRdb(
                        authorCounter,
                        authorMongo.getAuthor())
        );
        return authorCounter;
    }

    private Long identifyGenre(GenreMongo genreMongo) {
        String genreMongoId = genreMongo.getGenreId();
        if(genreIdMap.containsKey(genreMongoId)) {
            return genreIdMap.get(genreMongoId);
        }
        genreCounter++;
        genreIdMap.put(genreMongoId, genreCounter);
        genreRepositoryRdb.save(
                new GenreRdb(
                        genreCounter,
                        genreMongo.getGenre())
        );
        return genreCounter;
    }
}