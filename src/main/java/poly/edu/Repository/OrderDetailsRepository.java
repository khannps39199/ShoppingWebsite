package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.OrderDetails;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    // Additional query methods (if needed) can be added here
}