package otus.student.kryukov.dz.service;

import otus.student.kryukov.dz.domain.BookMongo;
import otus.student.kryukov.dz.domain.BookRdb;

public interface BookConverterService {

    BookRdb mongoToRdbConvert(BookMongo bookMongo);

}
