package poly.edu.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserUserIdAndStatus(Integer userId, String status);
    Optional<Order> findByOrderIdAndUserUserId(Integer orderId, Integer userId);
}

