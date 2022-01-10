package hello;

import com.sun.xml.internal.ws.spi.db.DatabindingException;

import java.util.Date;

public class LoadTestRun {
    public static void main(String[] args) {

        System.out.println("client start time:"+ new Date().toString());
        int threadNumber=100;
        for (int i = 0; i < threadNumber; i++) {
            new Thread(()->{
                System.out.println(new GreetingWebClient().getResult());
            }).start();
        }
    }
}
