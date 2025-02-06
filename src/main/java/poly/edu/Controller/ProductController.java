package poly.edu.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import poly.edu.DTO.ProductDTO;
import poly.edu.Entity.Category;
import poly.edu.Entity.Product;
import poly.edu.Repository.CategoryRepository;
import poly.edu.Repository.ProductRepository;
@RestController

public class ProductController {
	@Autowired
	 private ProductRepository productRepo ;
	 @Autowired
	    public void TempController(ProductRepository productRepo) {
	        this.productRepo=productRepo;
	    }
	@GetMapping("/api/getProducts")
	  public List<ProductDTO> getMethodName() throws JsonProcessingException {
	   // Fetch all categories from the database
		List<Product> allPro = productRepo.findAll();
		
		List<ProductDTO> allProDTO =allPro.stream()
			    .map(product -> new ProductDTO(
			            product.getProductId(),
			            product.getName(),
			            product.getDescription(),
			            product.getPrice(),
			            product.getDiscount(),
			            product.getStock(),
			            product.getImage(),
			            product.getCreatedAt(),
			            product.getCategory() != null ? product.getCategory().getId() : null
			        ))
			        .collect(Collectors.toList());
	   return allProDTO; // Spring Boot automatically converts this to JSON
	}
}
