package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.edu.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Additional query methods (if needed) can be added here
	
}