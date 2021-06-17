package otus.student.kryukov.dz.print;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrawTableImpl implements DrawTable {
    private final PrintService printService;

    @Override
    public void drawAsciiTableAuthor(List<Author> authors, List<String> titles) {
        AsciiTable table = new AsciiTable();
        for (Author authorObject : authors) {
            table.addRow(authorObject.getAuthorId(), authorObject.getAuthor());
        }
        drawAsciiTable(table.getRawContent(), titles);
    }

    @Override
    public void drawAsciiTableBook(List<Book> books, List<String> titles) {
        AsciiTable table = new AsciiTable();
        for (Book bookObject : books) {
            table.addRow(bookObject.getBookId(), bookObject.getTitle(), bookObject.getAuthorObject().getAuthor(), bookObject.getGenreObject().getGenre());
        }
        drawAsciiTable(table.getRawContent(), titles);
    }

    @Override
    public void drawAsciiTableGenre(List<Genre> genres, List<String> titles) {
        AsciiTable table = new AsciiTable();
        for (Genre genreObject : genres) {
            table.addRow(genreObject.getGenreId(), genreObject.getGenre());
        }
        drawAsciiTable(table.getRawContent(), titles);
    }

    private void drawAsciiTable(LinkedList<AT_Row> rows, List<String> titles) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        AT_Row rowHeader = table.addRow(titles);
        rowHeader.setTextAlignment(TextAlignment.CENTER);
        table.addRule();
        for (AT_Row row : rows) {
          table.addRow(row.getCells());
          table.addRule();
        }
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        printService.out(table.render());
    }
}