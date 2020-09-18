package billydev;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class LoadTestRun {



    public static void main(String[] args) {

        System.out.println("client start time:"+ new Date().toString());
        final String uri = "http://localhost:8080";
        for (int i = 0; i < Constants.THREAD_NUMBER; i++) {
            new Thread(()->{

                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(uri, String.class);

                System.out.println(result);
            }).start();
        }
    }
}
