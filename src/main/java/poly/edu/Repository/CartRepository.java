package poly.edu.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import poly.edu.Entity.Cart;
import poly.edu.Entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.userId = :userId AND c.product.id = :productId")
    Cart getCartByUserAndProduct(@Param("userId") Integer userId,
                                 @Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.user.userId = :userId")
    void handleOrder(@Param("userId") Integer userId);

}