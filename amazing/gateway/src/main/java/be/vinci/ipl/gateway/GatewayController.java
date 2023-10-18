package be.vinci.ipl.gateway;

import be.vinci.ipl.gateway.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        Product product = service.readOneProduct(id);

        if (product == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
