package isbnScan;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@SpringBootApplication
public class BookSearch {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BookSearch.class);
        builder.headless(false).run(args);
        BookSearch booksearch = new BookSearch();
        booksearch.createUI();

    }
    public void createUI() {
        JFrame frame = new JFrame("BookSearch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelText = new JLabel();
        labelText.setText("Enter the book title:");

        JTextField field = new JTextField(50);

        JButton checkForPrices = new JButton();
        checkForPrices.setText("Check for book price");

        JTable table = new JTable(new DefaultTableModel());
        checkForPrices.addActionListener(TableFiller.fillTableWithResults("jakistytul", table));

        frame.add(labelText, BorderLayout.NORTH);
        frame.add(field, BorderLayout.WEST);
        frame.add(checkForPrices, BorderLayout.EAST);
        frame.add(table, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}