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

  /*  public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public void setTitleLong(String titleLong) {
        this.titleLong = titleLong;
    }

    public String getAuthorsText() {
        return authorsText;
    }

    public void setAuthorsText(String authorsText) {
        this.authorsText = authorsText;
    }

    public String getPublisherText() {
        return publisherText;
    }

    public void setPublisherText(String publisherText) {
        this.publisherText = publisherText;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }*/
}
