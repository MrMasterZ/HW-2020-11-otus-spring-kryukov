package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.student.kryukov.dz.domain.BookRdb;

public interface BookRepositoryRdb extends JpaRepository<BookRdb, Long> {

}