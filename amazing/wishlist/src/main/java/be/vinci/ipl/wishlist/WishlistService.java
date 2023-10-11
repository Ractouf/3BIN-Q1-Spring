package be.vinci.ipl.wishlist;

import be.vinci.ipl.wishlist.model.Product;
import be.vinci.ipl.wishlist.model.Wishlist;
import be.vinci.ipl.wishlist.repositories.ProductsProxy;
import be.vinci.ipl.wishlist.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class WishlistService {
  private final WishlistRepository repository;
  private final ProductsProxy productsProxy;

  public WishlistService(WishlistRepository repository, ProductsProxy productsProxy) {
    this.repository = repository;
    this.productsProxy = productsProxy;
  }

  public Iterable<Product> readFromUser(String pseudo) {
    Iterable<Wishlist> wishlists = repository.findByPseudo(pseudo);
    ArrayList<Product> products = new ArrayList<>();

    for (Wishlist wishlist : wishlists) products.add(productsProxy.readOne(wishlist.getProductId()));

    return products;
  }

  public Wishlist putWishlist(String pseudo, int productId) {
    if (repository.existsByPseudoAndProductId(pseudo, productId)) return null;

    Wishlist wishlist = new Wishlist();
    wishlist.setPseudo(pseudo);
    wishlist.setProductId(productId);

    repository.save(wishlist);

    return wishlist;
  }

  public boolean deleteOne(String pseudo, int productId) {
    if (!repository.existsByPseudoAndProductId(pseudo, productId)) return false;

    repository.deleteByPseudoAndProductId(pseudo, productId);

    return true;
  }

  public boolean deleteByUser(String pseudo) {
    if (!repository.existsByPseudo(pseudo)) return false;

    repository.deleteByPseudo(pseudo);

    return true;
  }

  public boolean deleteByProductId(int productId) {
    if (!repository.existsByProductId(productId)) return false;

    repository.deleteByProductId(productId);

    return true;
  }
}
