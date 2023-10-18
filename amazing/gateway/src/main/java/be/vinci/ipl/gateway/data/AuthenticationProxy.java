package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.UnsafeCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(name = "authentication")
public interface AuthenticationProxy {
    @GetMapping("/authentication")
    ResponseEntity<String> connect(@RequestBody UnsafeCredentials unsafeCredentials);

    @PostMapping("/authentication")
    ResponseEntity<Void> createUser(@RequestBody UnsafeCredentials unsafeCredentials);
}
