package poly.edu.Controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.edu.Entity.Category;
import poly.edu.Repository.CategoryRepository;

@Controller
@RequestMapping("/admin/getcategories") // Định tuyến chung cho danh mục
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    // Lấy danh sách danh mục
    @GetMapping
    public String getAllCategories(Model model) {
        List<Category> allCate = categoryRepo.findAll();
        model.addAttribute("categories", allCate);
        model.addAttribute("category", new Category());
        // Form trống khi load trang

        model.addAttribute("CRUD", "CategoriesCRUD.html");
        return "CRUD";
    }


    // Thêm mới danh mục (Form sẽ được làm trống sau khi thêm)
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category, Model model) {
        if (category.getCreatedAt() == null) {
            category.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Gán thời gian tạo
        }
        categoryRepo.save(category);
        return "redirect:/categories"; // Redirect để form trống sau khi thêm
    }

    // Chỉnh sửa danh mục (Hiển thị thông tin cần chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null) {
            return "redirect:/categories"; // Nếu không tìm thấy, quay về danh sách danh mục
        }
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryRepo.findAll());
        return "CategoriesCRUD.html";
    }

    // Xóa danh mục
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryRepo.deleteById(id);
        return "redirect:/categories"; // Quay lại danh sách sau khi xóa
    }
}
