package billydev;

import org.springframework.stereotype.Component;

//@Component
public interface IService {
    @LogExecutionTime
    void serve() throws InterruptedException;

    @LogExecutionTime
    String generalServe();
}
