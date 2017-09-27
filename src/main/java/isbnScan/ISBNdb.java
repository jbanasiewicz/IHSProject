package isbnScan;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "ISBNdb")
public class ISBNdb {
    @XmlAttribute(name = "server_time")
    Date server_time;
    @XmlElement (name = "BookList")
    BookList bookList;

    public BookList getBookList() {
        return bookList;
    }
}
