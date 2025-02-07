package poly.edu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import poly.edu.Entity.Category;
import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;

@Controller
public class UsersController {
    @Autowired
    private UserRepository us;

    @GetMapping("/getUser")
    public String getMethodUser(Model model) throws JsonProcessingException {
        List<User> allUser = us.findAll();
        model.addAttribute("users", allUser); // Đảm bảo tên là 'users'
        model.addAttribute("newUser", new User());
        return "UsersCRUD"; // Trả về view với tên 'UsersCRUD'
    }


    @GetMapping("/newUser")
    public String newCate(Model model) throws JsonProcessingException {
        List<User> allUser = us.findAll(); 
        model.addAttribute("ListAll", allUser);
        model.addAttribute("newUser", new User()); // Đảm bảo bạn truyền đúng đối tượng vào model
        return "UsersCRUD";
    }

    @PostMapping("/users/save")
    public String newCategory(@ModelAttribute("newUser") User user, Model model) throws JsonProcessingException {
        us.save(user);
        List<User> allUser = us.findAll(); 
        model.addAttribute("ListAll", allUser);
        model.addAttribute("newUser", new User()); // Reset form sau khi lưu
        return "UsersCRUD"; // Trở lại trang UsersCRUD
    }

    @GetMapping("/users/edit/{id}")
    public String editMedhod(Model model, @PathVariable("id") String x) throws JsonProcessingException {
        List<User> allUser = us.findAll(); 
        model.addAttribute("ListAll", allUser);
        int id = Integer.parseInt(x) - 1;
        model.addAttribute("newUser", allUser.get(id)); // Dùng newUser thay vì category
        return "UsersCRUD"; // Trở lại trang UsersCRUD
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        us.deleteById(id); // Xóa người dùng theo id
        List<User> allUser = us.findAll(); 
        model.addAttribute("users", allUser); // Cập nhật danh sách sau khi xóa
        return "UsersCRUD"; // Quay lại trang danh sách người dùng
    }

}

