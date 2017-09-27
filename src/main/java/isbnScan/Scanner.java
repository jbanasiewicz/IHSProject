package isbnScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Component
public class Scanner {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public ISBNdb getBookInfo (String bookTitle){
        bookTitle = "harry potter i czara ognia";
        bookTitle = bookTitle.replaceAll(" ", "+");
        String url = "http://www.isbndb.com/api/books.xml?access_key=4N35Z5BX&index1=title&value1=" + bookTitle;
        /*HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();*/

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter());

        ISBNdb bookInfo = restTemplate.getForObject(url, ISBNdb.class);

        return bookInfo;
    }
}
