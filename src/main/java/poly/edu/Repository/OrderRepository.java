package poly.edu.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Additional query methods (if needed) can be added here
}