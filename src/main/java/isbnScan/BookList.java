package isbnScan;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "BookList")
public class BookList {
    @XmlAttribute (name = "total_results")
    Integer total_results;
    @XmlAttribute (name = "page_size")
    Integer page_size;
    @XmlAttribute (name = "page_number")
    Integer page_number;
    @XmlAttribute (name = "shown_results")
    Integer shown_results;
    @XmlElement (name = "BookData")
    List<BookData> bookData;

    public List<BookData> getBookData() {
        return bookData;
    }
}