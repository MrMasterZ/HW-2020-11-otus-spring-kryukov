package otus.student.kryukov.dz.print;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.service.CommentService;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrawTableImpl implements DrawTable {
    private final PrintService printService;
    private final CommentService commentService;

    @Override
    public void drawAsciiTableAuthor(List<Author> authors, List<String> titles) {
        AsciiTable table = new AsciiTable();
        for (Author authorObject : authors) {
            table.addRow(authorObject.getAuthorId(), authorObject.getAuthor());
        }
        drawAsciiTable(table.getRawContent(), titles, "author");
    }

    @Override
    public void drawAsciiTableBook(List<Book> books, List<String> titles) {
        AsciiTable table = new AsciiTable();
        for (Book bookObject : books) {
            table.addRow(bookObject.getBookId(), bookObject.getTitle(), bookObject.getAuthorObject().getAuthor(), bookObject.getGenreObject().getGenre());
        }
        drawAsciiTable(table.getRawContent(), titles, "book");
    }

    @Override
    public void drawAsciiTableComment(List<Comment> comments, List<String> titles) {
        AsciiTable table = new AsciiTable();
        for (Comment commentObject : comments) {
            table.addRow(commentObject.getCommentId(), commentObject.getComment(), commentObject.getBookObject().getTitle());
        }
        drawAsciiTable(table.getRawContent(), titles, "comment");
    }

    @Override
    public void drawAsciiTableCommentsForBook(Book bookObject, List<String> titles) {
        AsciiTable table = new AsciiTable();
        List<Comment> comments = bookObject.getComments();
        for (Comment commentObject : comments) {
            table.addRow(commentObject.getCommentId(), commentObject.getComment());
        }
        drawAsciiTable(table.getRawContent(), titles, "comment");
    }

    @Override
    public void drawAsciiTableGenre(List<Genre> genres, List<String> titles) {
        AsciiTable table = new AsciiTable();
        for (Genre genreObject : genres) {
            table.addRow(genreObject.getGenreId(), genreObject.getGenre());
        }
        drawAsciiTable(table.getRawContent(), titles, "genre");
    }

    private void drawAsciiTable(LinkedList<AT_Row> rows, List<String> titles, String entity) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        AT_Row rowHeader = table.addRow(titles);
        rowHeader.setTextAlignment(TextAlignment.CENTER);
        table.addRule();
        if("comment".equals(entity))
        {
            for (AT_Row row : rows) {
                AT_Row rowData = table.addRow(row.getCells());
                rowData.setTextAlignment(TextAlignment.LEFT);
                rowData.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
                table.addRule();
            }
        } else {
            for (AT_Row row : rows) {
                table.addRow(row.getCells());
                table.addRule();
            }
        }
        if(!"comment".equals(entity))
            table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        printService.out(table.render());
    }
}