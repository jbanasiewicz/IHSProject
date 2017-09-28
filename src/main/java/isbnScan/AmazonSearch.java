package isbnScan;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonSearch {
    String amazonPriceWhole;
    String amazonCurrency;
    String amazonPriceFractional;
    double amazonPrice;

    public double checkPriceOnAmazon (String isbn13) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=" + isbn13);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement title = driver.findElement(By.cssSelector(".a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal"));
        //a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal
        //a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal
        //a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal
        String link = title.getAttribute("href");
        driver.get(link);
        List<WebElement> buttons = driver.findElements(By.cssSelector(".a-button.a-spacing-mini.a-button-toggle.format"));
        for (int i = 0; i < buttons.size(); i++) {
            if (true) {
                String button = buttons.get(i).findElement(By.name("element")).findElement(By.name("firstChild_")).findElement(By.name("firstChild_")).findElement(By.name("firstChild_")).findElement(By.name("nextSibling_")).findElement(By.name("firstChild_")).getText();
            }
        }


        /*try {

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
        }*/
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
