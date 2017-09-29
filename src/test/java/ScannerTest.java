import isbnScan.AmazonSearch;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ScannerTest {

    @Test
    public void checkAmazonResult() {
        AmazonSearch amazonSearch = spy(new AmazonSearch());
        when(amazonSearch.checkPriceOnAmazon("9781855496507")).thenReturn(30.48);
        amazonSearch.setAmazonPrice(amazonSearch.checkPriceOnAmazon("9781855496507"));
        Assert.assertEquals(30.48, amazonSearch.getAmazonPrice(), 3.6);
    }
    @Test
    public void checkPriceConversion() {
        AmazonSearch amazonSearch = spy(new AmazonSearch());
        when(amazonSearch.convertPrice(2.20)).thenReturn(2.20 * 3.65);
        Assert.assertEquals(8.03, amazonSearch.convertPrice(2.20), 0.1);
    }
}