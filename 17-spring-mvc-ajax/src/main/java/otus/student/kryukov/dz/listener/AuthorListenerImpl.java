package otus.student.kryukov.dz.listener;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.exception.DependencyOfEntityException;
import otus.student.kryukov.dz.service.BookService;

@Service
public class AuthorListenerImpl extends AbstractMongoEventListener<Author> {

    private final BookService bookService;

    public AuthorListenerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        Document authorDocument = event.getSource();
        String authorId = authorDocument.get("_id").toString();
        if(bookService.existsByAuthorId(authorId))
            throw new DependencyOfEntityException("deleting of the author is prohibited (it has a book in the DB)");
    }
}
