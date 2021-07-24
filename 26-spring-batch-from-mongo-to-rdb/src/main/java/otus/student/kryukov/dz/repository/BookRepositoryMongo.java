package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.student.kryukov.dz.domain.BookMongo;

public interface BookRepositoryMongo extends MongoRepository<BookMongo, String> {

}