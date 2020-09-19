package billydev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
