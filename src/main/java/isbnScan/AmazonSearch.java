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
    double amazonPrice;

    public double checkPriceOnAmazon (String isbn13) {
        WebDriver driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=" + isbn13);
        try {
            WebElement title = driver.findElement(By.cssSelector(".a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal"));
            String link = title.getAttribute("href");
            driver.get(link);
            try {
                List<WebElement> buttons = driver.findElements(By.cssSelector(".a-button.a-spacing-mini.a-button-toggle.format"));
                for (int i = 0; i < buttons.size(); i++) {
                    try {
                        String button = buttons.get(i).getText();
                        if (button.toLowerCase().contains("paperback")) {
                            if (button.toLowerCase().contains("$")) {
                                if (button.toLowerCase().contains("from")) {
                                    if (button.toLowerCase().contains("perfect")) {
                                        amazonPrice = Double.parseDouble(button.replace("Perfect Paperback\n" + " from $", "").replace(",", ""));
                                        driver.quit();
                                        return priceConverter(amazonPrice);
                                    } else {
                                        amazonPrice = Double.parseDouble(button.replace("Paperback\n" + " from $", "").replace(",", ""));
                                        driver.quit();
                                        return priceConverter(amazonPrice);
                                    }
                                } else {
                                    if (button.toLowerCase().contains("perfect")) {
                                        amazonPrice = Double.parseDouble(button.replace("Perfect Paperback\n" + " $", "").replace(",", ""));
                                        driver.quit();
                                        return priceConverter(amazonPrice);
                                    } else {
                                        amazonPrice = Double.parseDouble(button.replace("Paperback\n" + " $", "").replace(",", ""));
                                        driver.quit();
                                        return priceConverter(amazonPrice);
                                    }
                                }
                            }
                        }
                    } catch (NoSuchElementException a) {
                        driver.quit();
                        return 0.00;
                    }
                }
                driver.quit();
                return 0.00;
            } catch (NoSuchElementException b) {
                driver.quit();
                return 0.00;
            }
        } catch (NoSuchElementException b) {
            driver.quit();
            return 0.00;
        }
    }

    public double priceConverter(double priceUSDEUR) {
        double pricePLN = 0.00;
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://stooq.pl/q/?s=usdpln");
        WebElement usdPln = driver.findElement(By.id("aq_usdpln_c5"));
        DecimalFormat formatter = new DecimalFormat("0.00");
        String a  = usdPln.getText();
        double valueUSDPLN = Double.parseDouble(a);
        String b = formatter.format(valueUSDPLN * priceUSDEUR).replace(',', '.');
        pricePLN = Double.parseDouble(b.replace(",", ""));

        driver.quit();
        return pricePLN;
    }
}
