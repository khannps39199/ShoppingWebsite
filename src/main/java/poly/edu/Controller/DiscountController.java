package poly.edu.Controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.edu.Entity.Address;
import poly.edu.Entity.Category;
import poly.edu.Entity.Discount;
import poly.edu.Entity.User;
import poly.edu.Repository.*;

@Controller
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private DiscountRepository discountRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;

    @GetMapping
    public String getAllAddress(Model model) {
        List<Discount> allCate = discountRepo.findAll();
        model.addAttribute("DiscountList", allCate);
        model.addAttribute("Discount", new Discount());

        model.addAttribute("CRUD", "DiscountCRUD.html");
        return "CRUD";
    }


    // Thêm mới danh mục (Form sẽ được làm trống sau khi thêm)
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("Discount") Discount discount, Model model) {

        if (discount.getDiscountId() == null) {
            Discount discountToSave = new Discount((productRepo.findByProductID(discount.getProduct().getProductID())), discount.getDiscountValue());
            System.out.println(discountToSave);
            discountRepo.save(discountToSave);
        }
        return "redirect:/discount"; // Redirect để form trống sau khi thêm
    }

    // Chỉnh sửa danh mục (Hiển thị thông tin cần chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        Discount address = discountRepo.findByDiscountId(id);
        if (address == null) {
            return "redirect:/discount"; // Nếu không tìm thấy, quay về danh sách danh mục
        }
        model.addAttribute("Discount", address);
        model.addAttribute("DiscountList", discountRepo.findAll());
        model.addAttribute("CRUD", "DisCountCRUD.html");
        return "CRUD";
    }

    // Xóa danh mục
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        discountRepo.deleteById(id);

        return "redirect:/discount"; // Quay lại danh sách sau khi xóa
    }
}
