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
import java.util.List;

public class AmazonSearch {
    String amazonPriceWhole;
    String amazonCurrency;
    String amazonPriceFractional;
    double amazonPrice;

    public double checkPriceOnAmazon (String isbn13) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=" + isbn13);
        //driver.findElement(By.cssSelector(".a-size-medium.s-inline.s-access-title.a-text-normal")).click();

        try {

            WebElement currency = driver.findElement(By.className("sx-price-currency"));
            amazonCurrency = currency.getText();

            WebElement priceWhole = driver.findElement(By.className("sx-price-whole"));
            amazonPriceWhole = priceWhole.getText();

            WebElement priceFractional = driver.findElement(By.className("sx-price-fractional"));
            amazonPriceFractional = priceFractional.getText();
            amazonPrice = Double.parseDouble(amazonPriceWhole + "." + amazonPriceFractional);
        } catch (NoSuchElementException a) {
            try {
                WebElement price = driver.findElement(By.cssSelector(".a-size-base.a-color-base"));
                String amazonPriceWith$ = price.getText();
                try {
                    amazonPrice = Double.parseDouble(amazonPriceWith$.replace("$", "").replace(",", ""));
                    amazonCurrency = "$";
                } catch (java.lang.NumberFormatException c) {
                    List<WebElement> priceList = driver.findElements(By.cssSelector(".a-size-base.a-color-base"));
                    String amazonPriceWith$FromList = priceList.get(1).getText();
                    int i=1;
                    while (amazonPriceWith$FromList.equals(amazonPriceWith$FromList.replace("$", "")) && i < priceList.size()) {
                        i++;
                        amazonPriceWith$FromList = priceList.get(i).getText();
                    }
                    if (amazonPriceWith$FromList.equals(amazonPriceWith$FromList.replace("$", ""))) {
                        return 0.00;
                    }
                    amazonPrice = Double.parseDouble(amazonPriceWith$FromList.replace("$", "").replace(",", ""));
                }

            } catch (NoSuchElementException b) {
                return 0.00;
            }
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
            String b = formatter.format(valueUSDPLN * priceUSDEUR).replace(',', '.');
            pricePLN = Double.parseDouble(b.replace(",", ""));
        } else if (currency.equals("")) {

        }
        driver.quit();
        return pricePLN;
    }
}
