package be.vinci.ipl.wishlist;

import be.vinci.ipl.wishlist.model.Wishlist;
import be.vinci.ipl.wishlist.repositories.ProductsProxy;
import be.vinci.ipl.wishlist.repositories.UsersProxy;
import be.vinci.ipl.wishlist.repositories.WishlistRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
  private final WishlistRepository repository;
  private final ProductsProxy productsProxy;
  private final UsersProxy usersProxy;

  public WishlistService(WishlistRepository repository, ProductsProxy productsProxy, UsersProxy usersProxy) {
    this.repository = repository;
    this.productsProxy = productsProxy;
    this.usersProxy = usersProxy;
  }

  public Iterable<Wishlist> readFromUser(String pseudo) {
    return repository.findByPseudo(pseudo);
  }

  public Wishlist putWishlist(String pseudo, int productId) {
    try {
      usersProxy.readOne(pseudo);
      productsProxy.readOne(productId);
    } catch (FeignException e) {
      return null;
    }

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
