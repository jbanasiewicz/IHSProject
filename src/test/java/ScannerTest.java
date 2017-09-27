import isbnScan.Scanner;
import isbnScan.TableFiller;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScannerTest {

    @Test
    public void checkISBNResult() {
        new Scanner().getBookInfo("harry potter i czara ognia");
    }
    @Test
    public void checkTableFiller() {
        JTable table = new JTable(new DefaultTableModel(11, 5));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        new TableFiller().fillTableWithResults("harry potter i czara ognia", model);
    }
}
