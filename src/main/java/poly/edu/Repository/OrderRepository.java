package poly.edu.Repository;

import java.util.List;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByStatus(String status);
    List<Order> findByUserUserIdAndStatus(Integer userId, String status);


    Optional<Order> findByOrderIdAndUserUserId(Integer orderId, Integer userId);
    @Query("SELECT COUNT(o) > 0 FROM Order o WHERE o.user.id = :userId AND o.status = 'Pending' AND o.paymentMethod <> 'WHEN_RECEIVE'")


    boolean existsPendingOrderWithOtherPayment(@Param("userId") Integer userId);

}