package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.Entity.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Integer> {
}
