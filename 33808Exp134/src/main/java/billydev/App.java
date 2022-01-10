package billydev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

import java.util.ArrayList;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(App.class, args);
        UserWebClient uwc = new UserWebClient();
        System.out.println(uwc.getResult());

    }
}
