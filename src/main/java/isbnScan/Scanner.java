package isbnScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Arrays;


@Component
@EnableWebMvc
@Configuration
public class Scanner {

    @Autowired
    private RestTemplate restTemplate;

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

        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        ResponseEntity<ISBNdb> bookInfo = restTemplate().exchange(url, HttpMethod.GET, entity, ISBNdb.class);
        ISBNdb bookInfoBody = bookInfo.getBody();*/
        //ISBNdb bookInfo = restTemplate.getForObject(url, ISBNdb.class);

        return bookInfo;
    }
}
