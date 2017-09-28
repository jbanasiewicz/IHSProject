package isbnScan;

import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlSuperscript;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class AmazonSearch {
    String amazonPriceWhole;
    String amazonCurrency;
    String amazonPriceFractional;
    double amazonPrice;

    public double checkPriceOnAmazon (String isbn13) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("https://www.amazon.com/gp/search/ref=sr_adv_b/?search-alias=stripbooks&unfiltered=1&field-keywords=&field-author=&field-title=&field-isbn=" + isbn13 + "&field-publisher=&node=&field-p_n_condition-type=&p_n_feature_browse-bin=&field-age_range=&field-language=&field-dateop=During&field-datemod=&field-dateyear=&sort=relevanceexprank&Adv-Srch-Books-Submit.x=30&Adv-Srch-Books-Submit.y=15");
        try {
            WebElement accessTitle = driver.findElement(By.className("s-access-title"));
            accessTitle.click();
            WebElement paperBook = driver.findElement(By.id("a-autoid-3-announce"));
            paperBook.click();
            /*

            WebElement currency = driver.findElement(By.className("sx-price-currency"));
            amazonCurrency = currency.getText();

            WebElement priceWhole = driver.findElement(By.className("sx-price-whole"));
            amazonPriceWhole = priceWhole.getText();

            WebElement priceFractional = driver.findElement(By.className("sx-price-fractional"));
            amazonPriceFractional = priceFractional.getText();
            amazonPrice = Double.parseDouble(amazonPriceWhole + "." + amazonPriceFractional);*/
        } catch (NoSuchElementException a) {
            return 0.00;
        }
        driver.quit();

        return priceConverter(amazonPrice, amazonCurrency);

    }
    public double priceConverter(double priceUSDEUR, String currency) {
        double pricePLN = 0.00;
        WebDriver driver = new HtmlUnitDriver();
        if (currency.equals("$")) {
            driver.get("https://stooq.pl/q/?s=usdpln");
            WebElement usdPln = driver.findElement(By.id("aq_usdpln_c5"));
            DecimalFormat formatter = new DecimalFormat("0.00");
            String a  = usdPln.getText();
            double valueUSDPLN = Double.parseDouble(a);
            String b = formatter.format(valueUSDPLN * priceUSDEUR);
            pricePLN = Double.parseDouble(b.replace(',', '.'));
        } else if (currency.equals("")) {

        }
        driver.quit();
        return pricePLN;
    }
}
