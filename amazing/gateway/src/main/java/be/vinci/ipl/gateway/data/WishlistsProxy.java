package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.Product;
import be.vinci.ipl.gateway.models.Wishlist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Repository
@FeignClient(name = "wishlists")
public interface WishlistsProxy {
    @GetMapping("/wishlist/user/{pseudo}")
    Iterable<Product> readFromUser(@PathVariable String pseudo);

    @PutMapping("/wishlist/{pseudo}/{productId}")
    ResponseEntity<Wishlist> putWishlist(@PathVariable String pseudo, @PathVariable int productId);

    @DeleteMapping("/wishlist/{pseudo}/{productId}")
    ResponseEntity<Wishlist> deleteOne(@PathVariable String pseudo, @PathVariable int productId);

    @DeleteMapping("/wishlist/user/{pseudo}")
    ResponseEntity<Wishlist> deleteByUser(@PathVariable String pseudo);

    @DeleteMapping("/wishlist/product/{productId}")
    ResponseEntity<Wishlist> deleteByProductId(@PathVariable int productId);
}
