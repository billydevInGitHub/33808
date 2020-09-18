package hello;

import billydev.Constants;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {


	@Autowired
	Counter counter;

	public Mono<ServerResponse> hello(ServerRequest request) {
		counter.increment();
		try {
			Thread.sleep(Constants.THREAD_SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromValue("Hello, Spring!"));
	}
}
