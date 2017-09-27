package isbnScan;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "BookData")
public class BookData {
    @XmlAttribute (name = "book_id")
    String book_id;
    @XmlAttribute (name = "isbn")
    String isbn;
    @XmlAttribute (name = "isbn13")
    String isbn13;
    @XmlElement (name = "Title")
    String title;
    @XmlElement (name = "TitleLong")
    String titleLong;
    @XmlElement (name = "AuthorsText")
    String authorsText;
    @XmlElement (name = "PublisherText")
    String publisherText;
    @XmlAttribute(name = "publisher_id")
    String publisher_id;

    public String getTitle() {
        return title;
    }
}
