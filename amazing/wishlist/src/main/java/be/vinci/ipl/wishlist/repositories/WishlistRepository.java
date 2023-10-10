package be.vinci.ipl.wishlist.repositories;

import be.vinci.ipl.wishlist.model.Wishlist;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
  Iterable<Wishlist> findByPseudo(String pseudo);

  boolean existsByPseudo(String pseudo);
  boolean existsByProductId(int productId);
  boolean existsByPseudoAndProductId(String pseudo, int productId);

  @Transactional
  void deleteByPseudo(String pseudo);
  @Transactional
  void deleteByProductId(int productId);
  @Transactional
  void deleteByPseudoAndProductId(String pseudo, int productId);
}
