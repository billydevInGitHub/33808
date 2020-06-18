package billydev;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;

public class TheCustomConverter extends StringHttpMessageConverter {
    //just an empty custom converter
    @Override
    protected Long getContentLength(String str, @Nullable MediaType contentType) {
        Charset charset = Charset.defaultCharset();
        System.out.println("from customer converter!!!!!");
        return (long) str.getBytes(charset).length;
    }

}
