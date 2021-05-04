import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class App {
    public static void main(String[] args) {
        Flux.generate(sink -> {
            sink.next("Echo");
            sink.complete();
        }).subscribe(
                System.out::println
        );

        class ttt{
            void localmethod() {
                System.out.println("fff");
            }
        }
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> {String.format("[%s] %s", Thread.currentThread().getName(), x);
                    new ttt().localmethod();return "123";})
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }
}
