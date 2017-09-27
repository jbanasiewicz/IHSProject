import isbnScan.Scanner;
import org.junit.Test;

public class ScannerTest {

    @Test
    public void checkISBNResult() {
        new Scanner().getBookInfo("harry potter i czara ognia");
    }
}
