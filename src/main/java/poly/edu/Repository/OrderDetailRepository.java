package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.Entity.OrderDetail;
import poly.edu.Entity.Order;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> { 
    List<OrderDetail> findByOrder(Order order);
}

