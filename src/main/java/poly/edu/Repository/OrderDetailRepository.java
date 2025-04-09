package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.Entity.*;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT od FROM OrderDetail od JOIN FETCH od.product WHERE od.order.orderId = :orderId")
    List<OrderDetail> findByOrderIdWithProduct(@Param("orderId") Integer orderId);

    @Query("Select o.product.category.id as ma ,o.product.category.name as ten , sum(o.quantity) as so From OrderDetail o group by o.product.category.id ,o.product.category.name")
    List<ReportSoldProduct> reportSoldProduct();
}