package be.vinci.ipl.products;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Iterable<Product> readAll() {
        return repository.findAll();
    }

    public Product readOne(int id) {
        return repository.findById(String.valueOf(id)).orElse(null);
    }

    public boolean createOne(Product product) {
        if (repository.existsById(String.valueOf(product.getId()))) return false;

        repository.save(product);

        return true;
    }

    public Product updateOne(Product product) {
        if (repository.existsById(String.valueOf(product.getId()))) return repository.save(product);

        return null;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Product deleteOne(int id) {
        Product product = repository.findById(String.valueOf(id)).orElse(null);
        if (product == null) return null;

        repository.deleteById(String.valueOf(id));

        return product;
    }
}
