package poly.edu.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.Entity.Product;
import poly.edu.Repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProductsByPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findProductsByPriceRangeNative(minPrice, maxPrice, pageable);
    }

    public Page<Product> getProductsByCategoryAndPriceRange(Long categoryID, Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findByCategory_IdAndPriceBetween(categoryID, minPrice, maxPrice, pageable);
    }
}

