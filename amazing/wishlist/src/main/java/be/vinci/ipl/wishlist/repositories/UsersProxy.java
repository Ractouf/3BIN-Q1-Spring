package be.vinci.ipl.wishlist.repositories;

import be.vinci.ipl.wishlist.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "users")
public interface UsersProxy {
  @GetMapping("/users/{pseudo}")
  User readOne(@PathVariable String pseudo);
}
