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

import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;

@Controller
public class UsersController {
    @Autowired
    private UserRepository us;

    // Lấy danh sách người dùng và form mặc định
    @GetMapping("/getUser")
    public String getMethodUser(Model model) {
        List<User> allUser = us.findAll();
        model.addAttribute("users", allUser); 
        model.addAttribute("newUser", new User()); // Form mặc định rỗng
        return "UsersCRUD"; 
    }

    // Tạo người dùng mới với giá trị mặc định
    @GetMapping("/users/new")
    public String createNewUser(Model model) {
        User newUser = new User();
        newUser.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Gán thời gian mặc định
        model.addAttribute("newUser", newUser);
        model.addAttribute("users", us.findAll()); // Giữ nguyên danh sách
        return "UsersCRUD";
    }

    // Lưu hoặc cập nhật người dùng
    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("newUser") User user) {
        if (user.getUserId() == null) {
            // Trường hợp tạo mới
            user.setCreatedAt(new Timestamp(System.currentTimeMillis())); 
            if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
                user.setPasswordHash("defaultPassword"); 
            }
        } else {
            // Trường hợp cập nhật
            User existingUser = us.findById(user.getUserId()).orElse(null);
            if (existingUser != null) {
                user.setCreatedAt(existingUser.getCreatedAt()); // Giữ nguyên ngày tạo
                if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
                    user.setPasswordHash(existingUser.getPasswordHash()); // Giữ nguyên mật khẩu cũ
                }
            }
        }
        us.save(user);
        return "redirect:/getUser";
    }

    // Chỉnh sửa người dùng
    @GetMapping("/users/edit/{id}")
    public String editMethod(Model model, @PathVariable("id") int id) {
        List<User> allUsers = us.findAll(); // Lấy danh sách tất cả người dùng
        model.addAttribute("users", allUsers); // Giữ nguyên danh sách trong bảng

        User user = us.findById(id).orElse(null); // Lấy thông tin người cần sửa
        if (user == null) {
            return "redirect:/getUser"; // Nếu không tìm thấy user, quay lại danh sách
        }
        
        model.addAttribute("newUser", user); // Đổ dữ liệu lên form
        return "UsersCRUD";
    }

    // Xóa người dùng
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        if (us.existsById(id)) {
            us.deleteById(id);
        }
        return "redirect:/getUser";
    }
}
