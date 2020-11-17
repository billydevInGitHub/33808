package billydev;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {
    Map<Integer, User> userMap = new ConcurrentHashMap<Integer, User>();
    public UserRepositoryImpl(){
        userMap.put(1, new User(1, "Robert", "Ludlum", "rl@rl.com"));
        userMap.put(2, new User(2, "John", "Grisham", "jg@jg.com"));
        userMap.put(3, new User(3, "James", "Patterson", "jp@jp.com"));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(userMap.get(id));
    }

    @Override
    public Flux<User> getAllUsers() {

        // get as stream
        return Flux.fromStream(userMap.values().stream());

        // Uncomment the following line when do the stackback
//        return Flux.error(new RuntimeException("Test exception thrown from controller"));
    }

    @Override
    public Mono<Void> saveUser(Mono<User> user) {
        Mono<User> userMono = user.doOnNext(value->{
            userMap.put((userMap.keySet().size() +1), value);
        } );
        return userMono.then();
    }

}
