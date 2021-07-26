package otus.student.kryukov.dz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.AuthorMongo;
import otus.student.kryukov.dz.domain.AuthorRdb;
import otus.student.kryukov.dz.repository.AuthorRepositoryMongo;
import otus.student.kryukov.dz.repository.AuthorRepositoryRdb;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorSingleServiceImpl implements AuthorSingleService {
    private final AuthorRepositoryMongo authorRepositoryMongo;
    private final AuthorRepositoryRdb authorRepositoryRdb;

    // Save from Mongo DB to RDB only those authors who do not have books
    @Override
    public void save() {
        List<AuthorMongo> authorMongos = authorRepositoryMongo.findAll();
        for (AuthorMongo author : authorMongos) {
            if(!BookMongoToRdbConverter.authorIdMap.containsKey(author.getAuthorId())) {
                Long id = ++BookMongoToRdbConverter.authorCounter;
                BookMongoToRdbConverter.authorIdMap.put(author.getAuthorId(), id);
                authorRepositoryRdb.save(
                        new AuthorRdb(
                                id,
                                author.getAuthor())
                );
            }
        }
    }
}