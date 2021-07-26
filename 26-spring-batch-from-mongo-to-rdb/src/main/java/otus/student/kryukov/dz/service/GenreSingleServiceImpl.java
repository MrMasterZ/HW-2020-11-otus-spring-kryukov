package otus.student.kryukov.dz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.GenreMongo;
import otus.student.kryukov.dz.domain.GenreRdb;
import otus.student.kryukov.dz.repository.GenreRepositoryMongo;
import otus.student.kryukov.dz.repository.GenreRepositoryRdb;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreSingleServiceImpl implements GenreSingleService {

    private final GenreRepositoryMongo genreRepositoryMongo;
    private final GenreRepositoryRdb genreRepositoryRdb;

    // Save from Mongo DB to RDB only those genres for which there are no books
    @Override
    public void save() {
        List<GenreMongo> genreMongos = genreRepositoryMongo.findAll();
        for (GenreMongo genre : genreMongos) {
            if(!BookMongoToRdbConverter.genreIdMap.containsKey(genre.getGenreId())) {
                Long id = ++BookMongoToRdbConverter.genreCounter;
                BookMongoToRdbConverter.genreIdMap.put(genre.getGenreId(), id);
                genreRepositoryRdb.save(
                        new GenreRdb(
                                id,
                                genre.getGenre())
                );
            }
        }
    }
}