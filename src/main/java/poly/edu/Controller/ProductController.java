package poly.edu.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.edu.Entity.Product;
import poly.edu.Repository.ProductRepository;
import poly.edu.Entity.Category;
import poly.edu.Repository.CategoryRepository;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;
    @GetMapping("/admin/getproducts")
    public String getAllProducts(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size, 
            Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findAll(pageable);

        model.addAttribute("products", productPage.getContent());  
        model.addAttribute("currentPage", page);                   
        model.addAttribute("totalPages", productPage.getTotalPages()); 
        model.addAttribute("pageSize", size);
        model.addAttribute("product", new Product()); 
        model.addAttribute("categories", categoryRepo.findAll());

        model.addAttribute("CRUD","ProductsCRUD.html");
        return "CRUD";
    }


    @GetMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());  
        model.addAttribute("categories", categoryRepo.findAll());  
        model.addAttribute("products", productRepo.findAll()); 
        return "ProductsCRUD.html";
    }


    // Lưu hoặc cập nhật sản phẩm
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("imageFile") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                // Lấy tên file gốc
                String fileName = file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/";

                // Tạo thư mục nếu chưa tồn tại
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lưu file vào thư mục
                Path path = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                // Cập nhật tên file ảnh vào product
                product.setImage(fileName);
            } else {
                // Nếu không có file mới, giữ nguyên ảnh cũ (tránh bị NULL)
                Product existingProduct = productRepo.findById(product.getId()).orElse(null);
                if (existingProduct != null) {
                    product.setImage(existingProduct.getImage());
                }
            }

            // Lưu sản phẩm vào database
            productRepo.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/getproducts";
    }


    @GetMapping("/products/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") Long id,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/getproducts?page=" + page + "&size=" + size;
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepo.findAll(pageable);

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "ProductsCRUD"; // Đảm bảo file này tồn tại
    }



    // Xóa sản phẩm
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {  // Sử dụng @PathVariable thay vì @RequestParam
        if (productRepo.existsById(id)) {  // Kiểm tra nếu sản phẩm tồn tại
            productRepo.deleteById(id);
        }
        return "redirect:/getproducts";  // Quay lại danh sách sản phẩm
    }

}
