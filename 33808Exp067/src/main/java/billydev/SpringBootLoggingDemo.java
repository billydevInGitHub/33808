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
    }
}
