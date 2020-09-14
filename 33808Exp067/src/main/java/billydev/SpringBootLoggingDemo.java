package billydev;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLoggingDemo {

    private static Logger log = LoggerFactory.getLogger(SpringBootLoggingDemo.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootLoggingDemo.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run();

        log.info("spring boot application started!");
        log.error("test error");
        log.info(log.toString());


        String message = "This is a String";
        Integer zero = 0;

        try {
            log.debug("Logging message: {}", message);
            log.debug("Going to divide {} by {}", 42, zero);
            int result = 42 / zero;
        } catch (Exception e) {
            log.error("Error dividing {} by {} ", 42, zero, e);
        }
    }
}
