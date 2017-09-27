package isbnScan;


import java.util.List;

public class BookList {
    Integer total_results;
    Integer page_size;
    Integer page_number;
    Integer shown_results;
    List<BookData> bookData;

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getPage_number() {
        return page_number;
    }

    public void setPage_number(Integer page_number) {
        this.page_number = page_number;
    }

    public Integer getShown_results() {
        return shown_results;
    }

    public void setShown_results(Integer shown_results) {
        this.shown_results = shown_results;
    }

    public List<BookData> getBookData() {
        return bookData;
    }

    public void setBookData(List<BookData> bookData) {
        this.bookData = bookData;
    }

    public Integer getTotal_results() {
        return total_results;
    }
}
