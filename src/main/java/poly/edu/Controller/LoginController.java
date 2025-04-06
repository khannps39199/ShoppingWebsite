package poly.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;
import poly.edu.Service.*;

@Controller
public class LoginController {

    private final CookieService cookieService;
    private final ParamService paramService;
    private final SessionService sessionService;
    private final UserRepository userRepo;

    public LoginController(CookieService cookieService, ParamService paramService,
                           SessionService sessionService, UserRepository userRepo) {
        this.cookieService = cookieService;
        this.paramService = paramService;
        this.sessionService = sessionService;
        this.userRepo = userRepo;
    }

    @GetMapping("/account/login")
    public String loginPage(Model model) {
        if (sessionService.get("login") != null) {
            return "redirect:/asm";
        }
        return "login";
    }

    @PostMapping("/account/login")
    public String login(Model model) {
        String email = paramService.getString("email", "");
        String password = paramService.getString("password", "");
        boolean rememberMe = paramService.getBoolean("remember", false);

        User loginUser = userRepo.findByEmail(email).orElse(null);

        if (loginUser == null) {
            model.addAttribute("error", "Email không tồn tại!");
            return "login";
        }

        if (!loginUser.getPasswordHash().equals(password)) {
            model.addAttribute("error", "Mật khẩu không đúng!");
            return "login";
        }

        sessionService.set("login", loginUser);
        if (rememberMe) {
            cookieService.add("User", email, 10);
        }

        return "redirect:/asm";
    }

    @GetMapping("/account/logout")
    public String logout() {
        cookieService.remove("User");
        sessionService.remove("login");
        return "redirect:/account/login";
    }
}