package billydev;

import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(
                () -> 1,
                (count, sink) -> {
                    sink.next(count + " : " + new Date());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count >= 5) {
                        sink.complete();
                    }
                    return count + 1;
                },System.out::println)     // 1
                .subscribe(System.out::println);
    }
}
