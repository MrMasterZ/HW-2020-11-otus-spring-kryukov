package otus.student.kryukov.dz.service;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.dao.AuthorDao;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.print.PrintService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final PrintService printService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getByAuthor(String author) {
        return authorDao.getByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getByAuthorId(Long authorId) {
        return authorDao.getByAuthorId(authorId);
    }

    @Override
    @Transactional
    public void create(String author) {
        checkEmptyAuthor(author);
        authorDao.create(new Author(author));
    }

    @Override
    public void drawAsciiTableAuthor(Author authorObject) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("author_id", "author");
        table.addRule();
        table.addRow(authorObject.getAuthorId(), authorObject.getAuthor());
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        printService.out(table.render());
    }

    @Override
    public void checkEmptyAuthor(String author) {
        if (author == null || author.trim().equals(""))
            throw new EmptyEntityInsertException("author cannot be empty");
    }

}