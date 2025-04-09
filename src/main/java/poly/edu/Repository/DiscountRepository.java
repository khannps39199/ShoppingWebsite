package poly.edu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.Address;
import poly.edu.Entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByDiscountId(Long discountId);
}
