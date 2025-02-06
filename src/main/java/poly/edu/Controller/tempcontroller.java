package poly.edu.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.edu.Entity.Category;
import poly.edu.Repository.CategoryRepository;


@RestController
public class tempcontroller {
	@Autowired
	 private CategoryRepository categoryRepo ;

    @Autowired
    public void TempController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
	@GetMapping("/tempgetcategory")
	  public List<Category> getMethodName() throws JsonProcessingException {
      // Fetch all categories from the database
      List<Category> allCate = categoryRepo.findAll().stream()
              .peek(category -> category.setProducts(null)) // Exclude products
              .toList();
      return allCate; // Spring Boot automatically converts this to JSON
  }
	
	
}
