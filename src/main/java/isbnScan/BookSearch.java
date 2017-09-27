package isbnScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookSearch {
    public static void main(String[] args) {
        SpringApplication.run(BookSearch.class, args);
        /*JFrame frame = new JFrame("BookSearch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);*/
    }
}