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
import poly.edu.Entity.User;
import poly.edu.Repository.*;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    private UserRepository userRepo;
    @GetMapping
    public String getAllAddress(Model model) {
        List<Address> allCate = addressRepo.findAll();
        model.addAttribute("AddressList", allCate);
        model.addAttribute("Address", new Address()); 
       
        model.addAttribute("CRUD", "AddressCRUD.html");
        return "CRUD";
    }


    // Thêm mới danh mục (Form sẽ được làm trống sau khi thêm)
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("Address") Address address, Model model) {
        if(address.getAddressId()==null) {
        	Address addressToSave=new Address( ( userRepo.findByUserId(address.getUser().getUserId())) ,address.getAddress());
        	addressRepo.save(address);
        }
        return "redirect:/address"; // Redirect để form trống sau khi thêm
    }

    // Chỉnh sửa danh mục (Hiển thị thông tin cần chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Integer id, Model model) {
        Address address = addressRepo.findById(id);
        if (address == null) {
            return "redirect:/address"; // Nếu không tìm thấy, quay về danh sách danh mục
        }
        model.addAttribute("Address", address);
        model.addAttribute("AddressList", addressRepo.findAll());
        model.addAttribute("CRUD", "AddressCRUD.html");
        return "CRUD";
    }

    // Xóa danh mục
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        addressRepo.deleteById(id);
    	
        return "redirect:/address"; // Quay lại danh sách sau khi xóa
    }
}
