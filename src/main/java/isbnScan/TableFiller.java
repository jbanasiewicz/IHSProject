package isbnScan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableFiller {
    String title;
    String author;
    String isbn;
    double price;
    String whereToBuy;

    public void fillTableWithResults(String givenTitle, JTable table) {
        ISBNdb result = new Scanner().getBookInfo(givenTitle);


        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{"Title", "Author", "ISBN", "Best price", "Where to buy"});
        for (BookData bookData : result.getBookList().getBookData()) {
            title = bookData.title;
            author = bookData.authorsText;
            isbn = bookData.isbn;
            model.addRow(new Object[]{title, author, isbn});
        }
    }
}
