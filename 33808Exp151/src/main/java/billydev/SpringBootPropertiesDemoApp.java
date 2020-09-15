package billydev;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootPropertiesDemoApp {

    private static Logger log = LoggerFactory.getLogger(SpringBootPropertiesDemoApp.class);

    @Value("${billydev.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Value("${billydev.city")
    private String city;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPropertiesDemoApp.class,args);

        //todo: need to figure out why the following not print out anything
//        System.out.println("name is:"+new SpringBootPropertiesDemoApp().getName());
    }

    @PostConstruct
    private void init(){
        log.info("name is:"+name);
    }
}
