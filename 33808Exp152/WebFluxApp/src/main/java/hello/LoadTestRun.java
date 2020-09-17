package hello;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class LoadTestRun {



    private static final int THREAD_NUMBER = 300;

    public static void main(String[] args) {


        System.out.println("client start time:"+ new Date().toString());
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(()->{
                System.out.println(new GreetingWebClient().getResult());
            }).start();
        }
    }
}
