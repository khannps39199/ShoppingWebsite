package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.OrderDetail;


public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {
    // Additional query methods (if needed) can be added here
}