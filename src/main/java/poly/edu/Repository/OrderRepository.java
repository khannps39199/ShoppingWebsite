package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.Entity.Cart;
import poly.edu.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
}
