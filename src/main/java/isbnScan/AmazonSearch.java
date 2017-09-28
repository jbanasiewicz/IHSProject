package isbnScan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AmazonSearch {
    double amazonPrice;
    double amazonPricePLN;
    //String isbn13;
    public void checkPriceOnAmazon (String isbn13) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("https://www.amazon.com/gp/search/ref=sr_adv_b/?search-alias=stripbooks&unfiltered=1&field-keywords=&field-author=&field-title=&field-isbn=" + isbn13 + "&field-publisher=&node=&field-p_n_condition-type=&p_n_feature_browse-bin=&field-age_range=&field-language=&field-dateop=During&field-datemod=&field-dateyear=&sort=relevanceexprank&Adv-Srch-Books-Submit.x=30&Adv-Srch-Books-Submit.y=15");
        WebElement element = driver.findElement(By.className("sx-price-currency"));

        driver.quit();
    }
}
