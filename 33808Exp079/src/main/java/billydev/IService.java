package billydev;

import org.springframework.stereotype.Component;

//@Component
public interface IService {
    void serve() throws InterruptedException;

    String generalServe();
}
