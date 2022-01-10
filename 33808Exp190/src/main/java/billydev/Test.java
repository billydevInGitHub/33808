package billydev;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;
import reactor.tools.agent.ReactorDebugAgent;

public class Test {
    public static void main(String[] args) {

        ReactorDebugAgent.init();

        Flux.range(0, 1)
                .single()
                .subscribe(System.out::println);

        Flux.range(0, 5)
                .single() // <-- Aha!
                .subscribeOn(Schedulers.parallel())
                .block();
    }

}
