package billydev;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.util.List;

public class UserWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");
    // For getting all users
    private Mono<ClientResponse> result = client.get()
            .uri("/user")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange().checkpoint();

/*    // Getting user by ID
    private Mono<User> singleUser = client.get()
            .uri("/user/{1}")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .flatMap(res -> res.bodyToMono(User.class));*/


    public List<User> getResult() {
        Hooks.onOperatorDebug();
        Flux<User> userList = result.flatMapMany(res -> res.bodyToFlux(User.class)).log();
        return userList
                .collectList()
                .block();
    }
}
