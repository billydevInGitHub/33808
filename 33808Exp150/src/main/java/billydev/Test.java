package billydev;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {

        //static factory flux
        System.out.println("==using static factory");
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        //cant not under the following line
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
        System.out.println("\r\n==using generate method: ");
        Flux.generate(sink -> {
            sink.next("Echo");
            sink.complete();
        }).subscribe(System.out::println);

        System.out.println("\r\n==using create method: ");
        Flux.create(sink -> {
            for (char i = 'a'; i <= 'z'; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::print);


        System.out.println("\r\n==using static factory method for Mono: ");
        Mono.fromSupplier(() -> "Mono1").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Mono2")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Mono3")).subscribe(System.out::println);


        System.out.println("\r\n==using buffering for Flux: ");
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        Flux.interval(Duration.of(0, ChronoUnit.SECONDS),
                Duration.of(1, ChronoUnit.SECONDS))
                .buffer(Duration.of(5, ChronoUnit.SECONDS)).
                take(2).toStream().forEach(System.out::println);
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);


        System.out.println("\r\n==using window function: ");
        Flux.range(1, 100).window(20)
                .subscribe(flux ->
                        flux.buffer(5).subscribe(System.out::println));


        System.out.println("\r\n==using filtering: ");
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

        System.out.println("\r\n==using take: ");
        Flux.range(1, 10).take(2).subscribe(System.out::println);
        Flux.range(1, 10).takeLast(2).subscribe(System.out::println);
        Flux.range(1, 10).takeWhile(i -> i < 5).subscribe(System.out::println);
        Flux.range(1, 10).takeUntil(i -> i == 6).subscribe(System.out::println);



        System.out.println("\r\n==using map to convert: ");
        Flux.range(1, 10).map(x -> x*x).subscribe(System.out::println);


        System.out.println("\r\n==using zip: ");
        Flux.just("I", "You")
                .zipWith(Flux.just("Win", "Lose"))
                .subscribe(System.out::println);
        Flux.just("I", "You")
                .zipWith(Flux.just("Win", "Lose"),
                        (s1, s2) -> String.format("%s!%s!", s1, s2))
                .subscribe(System.out::println);


        System.out.println("\r\n==using combineLatest: ");
        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.of(0, ChronoUnit.MILLIS),
                        Duration.of(100, ChronoUnit.MILLIS)).take(2),
                Flux.interval(Duration.of(50, ChronoUnit.MILLIS),
                        Duration.of(100, ChronoUnit.MILLIS)).take(2)
        ).toStream().forEach(System.out::println);



        System.out.println("\r\n==using merge: ");
        Flux.merge(Flux.interval(
                Duration.of(0, ChronoUnit.MILLIS),
                Duration.of(100, ChronoUnit.MILLIS)).take(2),
                Flux.interval(
                        Duration.of(50, ChronoUnit.MILLIS),
                        Duration.of(100, ChronoUnit.MILLIS)).take(2))
                .toStream()
                .forEach(System.out::println);
        System.out.println("---");
        Flux.mergeSequential(Flux.interval(
                Duration.of(0, ChronoUnit.MILLIS),
                Duration.of(100, ChronoUnit.MILLIS)).take(2),
                Flux.interval(
                        Duration.of(50, ChronoUnit.MILLIS),
                        Duration.of(100, ChronoUnit.MILLIS)).take(2))
                .toStream()
                .forEach(System.out::println);


        System.out.println("\r\n==using reduce: ");
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);


        System.out.println("\r\n==using error handling: ");
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResume(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);

//        the following seem just throw exception
//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .retry(1)
//                .subscribe(System.out::println);

        System.out.println("\r\n==using thread ");
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }
}
