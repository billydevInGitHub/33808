package billydev;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BillyBean {

    @PostConstruct
    public void init() {
        System.out.println("In Bean BillyBean init!");
    }
}
