package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.Category;
import poly.edu.Entity.Product;

public interface  ProductRepository extends JpaRepository<Product, Integer>{

}
