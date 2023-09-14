package be.vinci.ipl.amazing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Damien", "Muzik", 10));
        products.add(new Product(2, "Guillhermé", "LaSal", (int) (Math.random() * 10)));
        products.add(new Product(3, "Alicia", "Dépressif", (int) (Math.random() * 10)));
        products.add(new Product(4, "Miguel", "Chat", (int) (Math.random() * 10)));
    }

    /**
     * Reads all products.
     *
     * @return all products
     */
    @GetMapping("/products")
    public Iterable<Product> readAll() {
        return products;
    }

    /**
     * Reads one product associated with given id.
     *
     * @param id of the product to read
     * @return HttpStatus 404 if product does not exist and HttpStatus 200 if product does exists
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> readOne(@PathVariable int id) {
        Product product = findById(id);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a new product.
     *
     * @param product to create
     * @return HttpStatus 409 if product already exists and HttpStatus 201 if product was created
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createOne(@RequestBody Product product) {
        if (products.contains(product))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        products.add(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Updates one entire resource.
     *
     * @param product to update
     * @return HttpStatus 404 if product was not found and 200 if product was successfully updated
     */
    @PutMapping("/products")
    public ResponseEntity<Product> updateOne(@RequestBody Product product) {
        Product foundProduct = findById(product.getId());

        if (!products.contains(foundProduct))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        products.remove(foundProduct);
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Deletes all products.
     *
     * @return HttpStatus 200 if products was cleared
     */
    @DeleteMapping("/products")
    public ResponseEntity<Product> deleteAll() {
        products.clear();
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
        Product product = findById(id);

        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        products.remove(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Finds a product associated with given id.
     *
     * @param id of the product to find
     * @return the found product or null if none was found
     */
    private Product findById(int id) {
        for (Product currentproduct : products)
            if (currentproduct.getId() == id)
                return currentproduct;

        return null;
    }
}
