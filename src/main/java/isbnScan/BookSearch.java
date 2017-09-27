package isbnScan;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JTable table = new JTable(new DefaultTableModel(0,5));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);

        checkForPrices.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                while (model.getRowCount() > 0) {
                    model.removeRow(model.getRowCount() - 1);
                }
                String message = field.getText();
                if (!message.equals("")) {
                    model.addRow(new Object[]{"Title", "Author", "ISBN", "Best price", "Where to buy"});
                    searchButtonPressed(message, model);
                }
                frame.add(table, BorderLayout.SOUTH);
                frame.setVisible(false);
                frame.pack();
                frame.setVisible(true);
            }
        } );


        frame.add(labelText, BorderLayout.NORTH);
        frame.add(field, BorderLayout.WEST);
        frame.add(checkForPrices, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
    }
    public void searchButtonPressed(String message, DefaultTableModel model) {
        TableFiller tableFiller = new TableFiller();
        tableFiller.fillTableWithResults(message, model);
    }
}