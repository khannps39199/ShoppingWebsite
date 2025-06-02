package poly.edu.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poly.edu.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM Products WHERE price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    Page<Product> findProductsByPriceRangeNative(
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );
    @Query(value = "SELECT * FROM Products WHERE name LIKE %:keyword%", nativeQuery = true)
    Page<Product> findByKeywords(@Param("keyword") String name, Pageable pageable);


    Page<Product> findByCategory_IdAndPriceBetween(Long id, Double minPrice, Double maxPrice, Pageable pageable);

    Page<Product> findByCategory_Id(Long id, Pageable pageable);

    Product findByProductID(Long productID);

}

