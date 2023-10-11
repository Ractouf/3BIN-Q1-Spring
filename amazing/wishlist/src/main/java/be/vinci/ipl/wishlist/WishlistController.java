package be.vinci.ipl.wishlist;

import be.vinci.ipl.wishlist.model.Product;
import be.vinci.ipl.wishlist.model.Wishlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistController {
  private final WishlistService service;

  public WishlistController(WishlistService service) {
    this.service = service;
  }

  @GetMapping("/wishlist/user/{pseudo}")
  public Iterable<Product> readFromUser(@PathVariable String pseudo) {
    return service.readFromUser(pseudo);
  }

  @PutMapping("/wishlist/{pseudo}/{productId}")
  public ResponseEntity<Wishlist> putWishlist(@PathVariable String pseudo, @PathVariable int productId) {
    Wishlist wishlist = service.putWishlist(pseudo, productId);

    if (wishlist == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(wishlist, HttpStatus.CREATED);
  }

  @DeleteMapping("/wishlist/{pseudo}/{productId}")
  public ResponseEntity<Wishlist> deleteOne(@PathVariable String pseudo, @PathVariable int productId) {
    boolean deleted = service.deleteOne(pseudo, productId);

    if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/wishlist/user/{pseudo}")
  public ResponseEntity<Wishlist> deleteByUser(@PathVariable String pseudo) {
    boolean deleted = service.deleteByUser(pseudo);

    if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/wishlist/product/{productId}")
  public ResponseEntity<Wishlist> deleteByProductId(@PathVariable int productId) {
    boolean deleted = service.deleteByProductId(productId);

    if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
