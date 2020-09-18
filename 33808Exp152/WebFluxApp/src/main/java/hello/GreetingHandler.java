package hello;

import billydev.Constants;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import java.time.Duration;

import static billydev.Constants.REQUEST_TIMER_NAME;

@Component
public class GreetingHandler {


	@Autowired
	Counter counter;

	@Autowired
	MeterRegistry meterRegistry;

	public Mono<ServerResponse> hello(ServerRequest request) {
		counter.increment();
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(Constants.THREAD_SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Mono<ServerResponse> bodyReturn = ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
				.body(BodyInserters.fromValue("Hello, Spring!"));
		long endTime = System.currentTimeMillis();
		meterRegistry.timer(REQUEST_TIMER_NAME).record(Duration.ofMillis(endTime - startTime));
		return bodyReturn;
	}
}
