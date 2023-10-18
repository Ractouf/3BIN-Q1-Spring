package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient(name = "users")
public interface UsersProxy {
    @PostMapping("/users/{pseudo}")
    ResponseEntity<Void> createOne(@PathVariable String pseudo, @RequestBody User user);

    @GetMapping("/users/{pseudo}")
    ResponseEntity<User> readOne(@PathVariable String pseudo);

    @PutMapping("/users/{pseudo}")
    ResponseEntity<User> updateOne(@PathVariable String pseudo, @RequestBody User user);

    @DeleteMapping("/users/{pseudo}")
    ResponseEntity<User> deleteOne(@PathVariable String pseudo);
}
