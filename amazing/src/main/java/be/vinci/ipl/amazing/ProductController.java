package be.vinci.ipl.amazing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Reads all products.
     *
     * @return all products
     */
    @GetMapping("/products")
    public Iterable<Product> readAll() {
        return service.readAll();
    }

    /**
     * Reads one product associated with given id.
     *
     * @param id of the product to read
     * @return HttpStatus 404 if product does not exist and HttpStatus 200 if product does exists
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> readOne(@PathVariable int id) {
        Product product = service.readOne(id);

        if (product == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Creates a new product.
     *
     * @param product to create
     * @return HttpStatus 409 if product already exists and HttpStatus 201 if product was created
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createOne(@RequestBody Product product) {
        if (service.createOne(product)) return new ResponseEntity<>(product, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Updates one entire resource.
     *
     * @param product to update
     * @return HttpStatus 404 if product was not found and 200 if product was successfully updated
     */
    @PutMapping("/products")
    public ResponseEntity<Product> updateOne(@RequestBody Product product) {
        if (service.updateOne(product) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Deletes all products.
     *
     * @return HttpStatus 200 if products was cleared
     */
    @DeleteMapping("/products")
    public ResponseEntity<Product> deleteAll() {
        service.deleteAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes one product associated with given id.
     *
     * @param id of the product to delete
     * @return HttpStatus 404 if product was not found and HttpStatus 200 if product was deleted.
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteOne(@PathVariable int id) {
        Product product = service.deleteOne(id);

        if (product == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
