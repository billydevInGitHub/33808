package billydev;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Test {
    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        // code go through normally.
        Flux.range(0, 1)
                .single()
                .map(
                        a->a+10
                )
                .subscribe((a)-> {
                    System.out.println(a);
                        }
                );

        // Option BillyDev: break point in assembly method and look into the "source chain"
        Flux.range(0, 2)
                .single()
                .flatMap(a-> Mono.just(a+10))
                .flatMap(b->Mono.just(b+1000))
                .map(
                        a->a+10
                )
                .block();

        // Option A: Hooks.onOperatorDebug()
//        Hooks.onOperatorDebug();
//        Flux.range(0, 5)
//                .single() // <-- Aha!
//                .subscribeOn(Schedulers.parallel())
//                .block();

        // Option B: checkpoint()  NEED COMMENT OUT Option A
//        Hooks.onOperatorDebug();
//        Flux.range(0, 5)
//                .single() // <-- Aha!
//                .checkpoint()
//                .subscribe();

        // Option C: Add log()  NEED COMMENT OUT Opation A and Option B
//        Hooks.onOperatorDebug();
//        Flux.range(0, 5)
//                .single() // <-- Aha!
//                .log()
//                .subscribe();
    }
}
