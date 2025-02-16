package poly.edu.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import poly.edu.Entity.Category;
import poly.edu.Repository.CategoryRepository;



@Controller
public class CategoryController {
	@Autowired
	 private CategoryRepository categoryRepo ;
	 @Autowired
	    public void TempController(CategoryRepository categoryRepo) {
	        this.categoryRepo = categoryRepo;
	    }
	 @GetMapping("/getcategories")
	  public String getMethodName(Model model) throws JsonProcessingException {
	   // Fetch all categories from the database
	   List<Category> allCate = categoryRepo.findAll(); 
	   model.addAttribute("categories", allCate);
	   model.addAttribute("category", allCate.get(0));
	   return "CategoriesCRUD.html"; 
	} 
	 @GetMapping("/getcategories/newcategory")
	  public String newCate(Model model) throws JsonProcessingException {
		   List<Category> allCate = categoryRepo.findAll(); 
		   model.addAttribute("categories", allCate);
		   model.addAttribute("category", new Category());
		   return "CategoriesCRUD.html";
		}
	 
	 // ADD COMMENT 
	 @PostMapping("/categories/save")
	  public String newCategory(@ModelAttribute("category")  Category category,Model model) throws JsonProcessingException {
		 categoryRepo.save(category); 
	   List<Category> allCate = categoryRepo.findAll(); 
	   model.addAttribute("categories", allCate);
	   Category newCate=new Category();
	   model.addAttribute("category", newCate);
	   return "CategoriesCRUD.html"; // Spring Boot automatically converts this to JSON
	}
	 @GetMapping("/getcategories/edit/{id}")
	 public String editCategory(Model model, @PathVariable("id") Integer id) {
	     Category category = categoryRepo.findById(id).orElse(null);
	     if (category == null) {
	         return "redirect:/getcategories"; // Nếu không tìm thấy, quay về danh sách danh mục
	     }
	     model.addAttribute("category", category);
	     model.addAttribute("categories", categoryRepo.findAll());
	     return "CategoriesCRUD"; // Đảm bảo file này tồn tại trong thư mục templates
	 }

	
	
	
	
	
}
