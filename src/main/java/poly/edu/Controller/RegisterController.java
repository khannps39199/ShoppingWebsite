package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;
import poly.edu.Service.ParamService;
import poly.edu.Service.SessionService;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
public class RegisterController {
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    ParamService paramService;
    
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; 
    }

    @PostMapping("/account/register")
    public String register(@RequestParam String username, 
                           @RequestParam String email, 
                           @RequestParam String password, 
                           @RequestParam String confirmPassword, 
                           @RequestParam String fullName, 
                           @RequestParam String phone, 
                           @RequestParam String address, 
                           Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu nhập lại không khớp");
            return "register";
        }

        Optional<User> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email đã tồn tại");
            return "register";
        }

        User newUser = User.builder()
                .username(username)
                .email(email)
                .passwordHash(password)
                .fullName(fullName)
                .phone(phone)
                .address(address)
                .role("Customer")
                .isActivated(true)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        userRepo.save(newUser);
        sessionService.set("login", newUser);
        return "redirect:/asm";
    }
}
