package hello;

import billydev.Constants;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class LoadTestRun {





    public static void main(String[] args) {


        System.out.println("client start time:"+ new Date().toString());
        for (int i = 0; i < Constants.THREAD_NUMBER; i++) {
            new Thread(()->{
//                System.out.println(new GreetingWebClient().getResult());
                new GreetingWebClient().getResult();
            }).start();
        }
    }
}
