package isbnScan;

import javax.swing.table.DefaultTableModel;

public class TableFiller {
    String title;
    String isbn13;
    double price;

    public void fillTableWithResults(String givenTitle, DefaultTableModel model) {
        ISBNdb result = new Scanner().getBookInfo(givenTitle);

        for (BookData bookData : result.getBookList().getBookData()) {
            title = bookData.title;
            isbn13 = bookData.isbn13;
            price = new AmazonSearch().checkPriceOnAmazon(isbn13);
            model.addRow(new Object[]{title, isbn13, price, "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=" + isbn13});
        }
    }
}
