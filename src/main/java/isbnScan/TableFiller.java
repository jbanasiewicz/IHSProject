package isbnScan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;

public class TableFiller {
    String title;
    String isbn13;
    double price;
    String whereToBuy;

    public void fillTableWithResults(String givenTitle, DefaultTableModel model) {
        ISBNdb result = new Scanner().getBookInfo(givenTitle);

        for (BookData bookData : result.getBookList().getBookData()) {
            title = bookData.title;
            isbn13 = bookData.isbn13;
            price = new AmazonSearch().checkPriceOnAmazon(isbn13);
            model.addRow(new Object[]{title, isbn13, price, " "});
        }
    }


}
