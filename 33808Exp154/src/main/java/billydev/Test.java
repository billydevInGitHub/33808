package billydev;

import reactor.core.publisher.Mono;

public class Test {
    public static void main(String[] args) {

        System.out.println("begin");
        //todo:
        //Flux<String> manyWords = Flux.fromIterable(words);

        //Mono.fromFuture(completableFuture);

        //Medium state

        Mono.just("Hello")
                .flatMap(v -> doStep1(v))
                .flatMap(v -> doStep2(v))
                .flatMap(v -> doStep3(v));
    }

    private static Mono<String> doStep1(String v) {
        System.out.println("Step 1: v = " + v);
        return Mono.just(v);
    }

    private static Mono<String> doStep2(String v) {
        System.out.println("Step 2: v = " + v);
        return Mono.just(v);
    }
    private static Mono<String> doStep3(String v) {
        System.out.println("Step 3: v = " + v);
        return Mono.just(v);
    }


}
