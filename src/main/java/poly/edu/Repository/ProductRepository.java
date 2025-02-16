package poly.edu.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.Category;
import poly.edu.Entity.Product;
import poly.edu.Entity.User;

public interface  ProductRepository extends JpaRepository<Product, Long>{
	Page<Product> findAll(Pageable pageable);
}