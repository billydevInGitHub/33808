package billydev.service.bean;

import billydev.OneClickProperties;
import billydev.service.bean.jobAndControlThreads.ClientJobThread;
import billydev.service.bean.jobAndControlThreads.InfoExchange;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServiceBean {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    OneClickProperties oneClickProperties;

    @Autowired
    DataSource dataSource;

    @Autowired
    MeterRegistry meterRegistry;


    public synchronized String service(int timeInSeconds) {

        System.out.println("Service bean starting ....with query: "+oneClickProperties.getDbquery());
        //run db query and do custom metrix
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(new Runnable() {
                                                   @Override
                                                   public void run() {
                                                       try {
                                                           StopWatch stopWatch = new StopWatch();
                                                           stopWatch.start("DBQuery");
                                                           Statement statement=dataSource.getConnection().createStatement();
                                                           statement.executeQuery(oneClickProperties.getDbquery());
                                                           stopWatch.stop();
                                                           meterRegistry.timer("DBQuery").record(
                                                                   Duration.ofMillis(
                                                                   stopWatch.getLastTaskTimeMillis()));
                                                       } catch (SQLException throwables) {
                                                           throwables.printStackTrace();
                                                       }
                                                   }
                                               }, 500, 500,
                TimeUnit.MILLISECONDS
        );

        // test configuratoin properties
        System.out.println("configured dbquery: " + oneClickProperties.getDbquery());


        //test linux jobs
        Thread restCallThread = new Thread() {
            @Override
            public void run() {
                super.run();
                restTemplateBuilder.build().getForEntity("http://localhost:8080/api/posts", Object.class);
            }
        };
        restCallThread.start();


        InfoExchange infoExchange = new InfoExchange();

        //linux call
        Thread thread = new ClientJobThread("TcpDump",
                "cmd /c pause",
                "",
                infoExchange
        );
        thread.start();

        Thread clientJobControlThread = new Thread() {
            public void run() {
                LocalDateTime begin = LocalDateTime.now();
                while (true) {
                    try {
                        LocalDateTime now = LocalDateTime.now();
                        long diff = ChronoUnit.SECONDS.between(begin, now);
                        System.out.println("time difference in seconds" + diff + " timeInSeconds:" + timeInSeconds);
                        if (diff > timeInSeconds) {
                            infoExchange.getP().destroy();//todo: how to get the result of destroy .
                            infoExchange.setContent("job force completed");
                            break; //should break the while loop
                        } else {
                            Thread.currentThread().sleep(200);
                        }
                    } catch (InterruptedException e) {
                        infoExchange.getP().destroy();
                        infoExchange.setContent("job force completed");
                        break;
                    }
                }
            }
        };
        clientJobControlThread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //when job completed normally,stop the jobControlThread as well
        clientJobControlThread.interrupt();
        System.out.println("ClientJobThread:  JobControlThread is interrupted");


        return infoExchange.getContent();
    }
}
