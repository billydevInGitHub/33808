package billydev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public Flux<User> listUser() {

        Flux<User> user = userRepository.getAllUsers();
        return user;

        // Uncomment the following line when do the stackback
//        return Flux.error(new RuntimeException("this is a test"));
    }

    @GetMapping("/user/{id}")
    Mono<User> findById(@PathVariable int id) {
        return this.userRepository.getUserById(id);
    }

    @PostMapping("/user")
    Mono<Void> create(@RequestBody Mono<User> userStream) {
        return this.userRepository.saveUser(userStream).then();
    }
}
