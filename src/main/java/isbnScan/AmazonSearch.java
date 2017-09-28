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
    double amazonPrice;

    public double checkPriceOnAmazon (String isbn13) {
        WebDriver driver = new HtmlUnitDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dstripbooks&field-keywords=" + isbn13);
        //String title = driver.findElement(By.id("productTitle")).getText();
        /*try {
            String price = driver.findElement(By.cssSelector(".a-size-medium .a-color-price .a-text-normal")).getText();
            amazonPrice = Double.parseDouble(price.replace("$", "").replace(",", ""));
        } catch (NoSuchElementException a) {
            return 0.00;
        }*/

        try {
            WebElement title = driver.findElement(By.cssSelector(".a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal"));
            String link = title.getAttribute("href");
            driver.get(link);
            try {
                List<WebElement> buttons = driver.findElements(By.cssSelector(".a-button.a-spacing-mini.a-button-toggle.format"));


                for (int i = 0; i < buttons.size(); i++) {
                    try {
                        WebElement button = buttons.get(i).findElement(By.xpath(".//*")).findElement(By.className("a-button-text"));
                        String path = button.getAttribute("href");
                        if (path.toLowerCase().contains("ref=tmm_pap_swatch")) {
                            driver.get(path);
                            //WebElement priceElement = driver.findElement(By.className("inlineBlock-display"));
                            //amazonPriceWhole = priceElement.getText();
                            try {
                                amazonPriceWhole = driver.findElement(By.cssSelector(".a-size-medium .a-color-price .offer-price .a-text-normal")).getText();
                                amazonPrice = Double.parseDouble(amazonPriceWhole.replace("$", "").replace(",", ""));
                                driver.quit();
                                return priceConverter(amazonPrice);
                            } catch (NoSuchElementException d) {
                                driver.quit();
                                return 0.00;
                            }
                        }
                    } catch (NoSuchElementException a) {
                        driver.quit();
                        return 0.00;
                    }
                }
                amazonPriceWhole = driver.findElement(By.cssSelector(".a-size-medium .a-color-price .offer-price .a-text-normal")).getText();
                amazonPrice = Double.parseDouble(amazonPriceWhole.replace("$", "").replace(",", ""));
                driver.quit();
                return priceConverter(amazonPrice);
            } catch (NoSuchElementException b) {
                driver.quit();
                return 0.00;
            }
        } catch (NoSuchElementException c) {
            driver.quit();
            return 0.00;
        }
        /*while (path.equals(path.replace("ref=tmm_kin_swatch", "ref=tmm_pap_swatch"))
                && path.equals(path.replace("ref=tmm_hrd_swatch", "ref=tmm_pap_swatch"))
                && path.equals(path.replace("ref=tmm_aud_swatch", "ref=tmm_pap_swatch"))
                && path.equals(path.replace("ref=tmm_mmp_swatch", "ref=tmm_pap_swatch"))
                && path.equals(path.replace("ref=tmm_abk_swatch", "ref=tmm_pap_swatch"))
                && path.equals(path.replace("ref=tmm_pap_swatch", "ref=tmm_abk_swatch"))) {
            i++;
            button = buttons.get(i).findElement(By.xpath(".//*")).findElement(By.className("a-button-text"));
            path = button.getAttribute("href");
        }
        path = path.replace("ref=tmm_kin_swatch", "ref=tmm_pap_swatch")
                .replace("ref=tmm_hrd_swatch", "ref=tmm_pap_swatch")
                .replace("ref=tmm_aud_swatch", "ref=tmm_pap_swatch")
                .replace("ref=tmm_mmp_swatch", "ref=tmm_pap_swatch")
                .replace("ref=tmm_abk_swatch", "ref=tmm_pap_swatch");*/

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
        //driver.quit();

        //return priceConverter(amazonPrice);

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
