package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.Entity.UserOrder;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {
    List<UserOrder> findByUser_UserIdAndStatus(Integer userId, String status);
}
