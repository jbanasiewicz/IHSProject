package isbnScan;

import javax.swing.table.DefaultTableModel;

public class TableFiller {
    String title;
    String author;
    String isbn;
    double price;
    String whereToBuy;

    public void fillTableWithResults(String givenTitle, DefaultTableModel model) {
        ISBNdb result = new Scanner().getBookInfo(givenTitle);

        for (BookData bookData : result.getBookList().getBookData()) {
            title = bookData.title;
            author = bookData.authorsText;
            isbn = bookData.isbn;
            model.addRow(new Object[]{title, author, isbn, " ", " "});
        }
    }
}
