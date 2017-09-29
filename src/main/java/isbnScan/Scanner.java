package isbnScan;

import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.util.Arrays;


@Component
public class Scanner {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public ISBNdb getBookInfo (String bookTitle){
        bookTitle = bookTitle.replaceAll(" ", "+");
        String url = "http://www.isbndb.com/api/books.xml?access_key=4N35Z5BX&index1=title&value1=" + bookTitle;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();

        ISBNdb bookInfo = JAXB.unmarshal(new StringReader(responseBody), ISBNdb.class);

        return bookInfo;
    }
}