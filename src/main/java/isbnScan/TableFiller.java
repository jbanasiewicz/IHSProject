package isbnScan;

import javax.swing.table.DefaultTableModel;

public class TableFiller {
    String title;
    String author;
    String isbn13;
    double price;
    String whereToBuy;

    public void fillTableWithResults(String givenTitle, DefaultTableModel model) {
        ISBNdb result = new Scanner().getBookInfo(givenTitle);

        for (BookData bookData : result.getBookList().getBookData()) {
            title = bookData.title;
            author = bookData.authorsText;
            isbn13 = bookData.isbn13;
            model.addRow(new Object[]{title, author, isbn13, " ", " "});
        }
    }
}
