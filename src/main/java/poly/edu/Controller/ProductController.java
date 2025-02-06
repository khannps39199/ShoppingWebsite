package poly.edu.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.edu.Entity.Product;
import poly.edu.Repository.ProductRepository;
import poly.edu.Entity.Category;
import poly.edu.Repository.CategoryRepository;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // Phương thức này dùng để hiển thị tất cả sản phẩm
    @GetMapping("/getproducts")
    public String getAllProducts(Model model) {
        List<Product> allProducts = productRepo.findAll();
        model.addAttribute("products", allProducts);  // Thêm danh sách sản phẩm vào model
        model.addAttribute("product", new Product());  // Để dùng khi tạo sản phẩm mới
        model.addAttribute("categories", categoryRepo.findAll());  // Danh sách các danh mục
        return "ProductsCRUD.html";
    }

    // Phương thức này hiển thị form tạo mới sản phẩm
    @GetMapping("/getproducts/newproduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());  // Tạo mới sản phẩm
        model.addAttribute("categories", categoryRepo.findAll());  // Danh sách các danh mục
        model.addAttribute("products", productRepo.findAll());  // Thêm danh sách sản phẩm vào model
        return "ProductsCRUD.html";
    }

    // Phương thức này dùng để lưu hoặc cập nhật sản phẩm vào cơ sở dữ liệu
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        // Nếu sản phẩm đã có id, tức là đang chỉnh sửa
        if (product.getId() != null) {
            // Cập nhật sản phẩm
            productRepo.save(product);
        } else {
            // Lưu sản phẩm mới
            productRepo.save(product);
        }
        // Quay lại trang danh sách sản phẩm
        return "redirect:/getproducts";
    }

    // Phương thức này dùng để hiển thị thông tin sản phẩm cần chỉnh sửa
    @GetMapping("/getproducts/edit")
    public String editProduct(Model model, @RequestParam("id") Long id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/getproducts";  // Nếu không tìm thấy sản phẩm, điều hướng về trang danh sách
        }
        model.addAttribute("product", product);  // Thêm sản phẩm cần chỉnh sửa vào model
        model.addAttribute("categories", categoryRepo.findAll());  // Danh sách các danh mục
        model.addAttribute("products", productRepo.findAll());  // Danh sách các sản phẩm hiện có
        return "ProductsCRUD.html";  // Trả về trang chỉnh sửa sản phẩm
    }

    // Phương thức này dùng để xóa sản phẩm
    @GetMapping("/getproducts/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        productRepo.deleteById(id);  // Xóa sản phẩm theo id
        return "redirect:/getproducts";  // Quay lại danh sách sản phẩm
    }
}