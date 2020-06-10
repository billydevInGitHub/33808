package billydev;

import org.springframework.stereotype.Component;

@Component
public class Service {
    @LogExecutionTime
    public void serve() throws InterruptedException {
        Thread.sleep(2000);
    }

    @LogExecutionTime
    public String generalServe() {
        String result = "test general serve";
        System.out.println(result);
        return result;
    }

}
