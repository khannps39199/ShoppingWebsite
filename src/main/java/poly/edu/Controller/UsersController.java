package poly.edu.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.edu.DTO.UserDTO;
import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsersController {
    @Autowired
    private UserRepository us;

    // Lấy danh sách người dùng với phân trang
//    @GetMapping("/admin/getUser")
//    public String getUsers(Model model, 
//                           @RequestParam(defaultValue = "0") int page, 
//                           @RequestParam(defaultValue = "5") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<User> userPage = us.findAll(pageable);
//
//        model.addAttribute("users", userPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", userPage.getTotalPages());
//        model.addAttribute("newUser", new User());
//        model.addAttribute("size", size);
//        model.addAttribute("CRUD","UsersCRUD.html");
//        return "CRUD";
//    }
//    
//    @GetMapping("/admin/getUser")

    @GetMapping("/getUser")
    public List<UserDTO>  getUsers(Model model, 
                           @RequestParam(defaultValue = "0") int page, 
                           @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = us.findAll(pageable);
        List<UserDTO> listUserDT0=new ArrayList<>();
        userPage.forEach(tempUser->{
        	UserDTO toJSON=new UserDTO(tempUser.getUserId(),tempUser.getUsername(),tempUser.getPasswordHash(),tempUser.getEmail(),
        			tempUser.getFullName(),tempUser.getPhone(),tempUser.getAddress(),tempUser.getRole(),tempUser.getIsActivated(),tempUser.getCreatedAt());
        	listUserDT0.add(toJSON);
        });
//        model.addAttribute("users", userPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", userPage.getTotalPages());
//        model.addAttribute("newUser", new User());
//        model.addAttribute("size", size);
//        model.addAttribute("CRUD","UsersCRUD.html");
        return listUserDT0;
    }

    // Tạo người dùng mới
    @GetMapping("/users/new")
    public String createNewUser(Model model, 
                                @RequestParam(defaultValue = "0") int page, 
                                @RequestParam(defaultValue = "5") int size) {
        User newUser = new User();
        newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        model.addAttribute("newUser", newUser);
        return "redirect:/getUser?page=" + page + "&size=" + size;
    }

    // Lưu hoặc cập nhật người dùng
    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("newUser") User user,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size) {
        if (user.getUserId() == null) {
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
                user.setPasswordHash("defaultPassword");
            }
        } else {
            User existingUser = us.findById(user.getUserId()).orElse(null);
            if (existingUser != null) {
                user.setCreatedAt(existingUser.getCreatedAt());
                if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
                    user.setPasswordHash(existingUser.getPasswordHash());
                }
            }
        }
        us.save(user);
        return "redirect:/admin/getUser?page=" + page + "&size=" + size;
    }

    // Chỉnh sửa người dùng
    @GetMapping("/users/edit/{id}")
    public String editUser(Model model, 
                           @PathVariable("id") int id,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = us.findAll(pageable);

        User user = us.findById(id).orElse(null);
        if (user == null) {
            return "redirect:adUser?page=" + page + "&size=" + size;
        }

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("newUser", user);

        return "UsersCRUD";
    }

    // Xóa người dùng
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, 
                             @RequestParam(defaultValue = "0") int page, 
                             @RequestParam(defaultValue = "5") int size) {
        if (us.existsById(id)) {
            us.deleteById(id);
        }
        return "redirect:/getUser?page=" + page + "&size=" + size;
    }
}
