package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.Entity.*;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	@Query("SELECT od FROM OrderDetail od JOIN FETCH od.product WHERE od.order.orderId = :orderId")
	List<OrderDetail> findByOrderIdWithProduct(@Param("orderId") Integer orderId);
}