package be.vinci.ipl.gateway;

import be.vinci.ipl.gateway.models.Product;
import be.vinci.ipl.gateway.models.UnsafeCredentials;
import be.vinci.ipl.gateway.models.User;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    private final GatewayService service;

    public GatewayController(GatewayService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public Iterable<Product> readAllProducts() {
        return service.readAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> readOneProduct(@PathVariable int id) {
        return service.readOneProduct(id);
    }


    @PostMapping("/users/{pseudo}")
    public ResponseEntity<Void> createOne(@PathVariable UnsafeCredentials unsafeCredentials, @RequestBody User user) {
        if (!Objects.equals(user.getPseudo(), unsafeCredentials.getPseudo())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return service.createOne(unsafeCredentials, user);
    }
}
