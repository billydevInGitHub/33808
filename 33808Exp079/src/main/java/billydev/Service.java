package billydev;

import org.springframework.stereotype.Component;

@Component
public class Service implements IService {
    @Override
    @LogExecutionTime
    public void serve() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Override
    @LogExecutionTime
    public String generalServe() {
        String result = "test general serve";
        System.out.println(result);
        return result;
    }

}
