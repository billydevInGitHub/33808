package billydev;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class QuickTest {
    public static void main(String[] args) {
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);

        Flux<Integer> flux = Flux.range(1, 10);
        Flux<Integer> flux2=new Flux<Integer>() {
            @Override
            public void subscribe(CoreSubscriber<? super Integer> actual) {

            }
        };
        Flux<Integer> flux3=new Flux<Integer>() {
            @Override
            public void subscribe(CoreSubscriber<? super Integer> actual) {

            }
        };
        flux2=flux.map(x -> x*x);
        flux3=flux2.map(x -> x+10);
        flux3.subscribe(System.out::println);
    }
}
