package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient(name = "products")
public interface ProductsProxy {
    @GetMapping("/products")
    Iterable<Product> readAll();

    @GetMapping("/products/{id}")
    ResponseEntity<Product> readOne(@PathVariable int id);

    @PostMapping("/products")
    ResponseEntity<Product> createOne(@RequestBody Product product);

    @PutMapping("/products")
    ResponseEntity<Product> updateOne(@RequestBody Product product);

    @DeleteMapping("/products")
    ResponseEntity<Product> deleteAll();

    @DeleteMapping("/products/{id}")
    ResponseEntity<Product> deleteOne(@PathVariable int id);
}
