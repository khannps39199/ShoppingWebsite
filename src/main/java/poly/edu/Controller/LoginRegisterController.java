package poly.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRegisterController {
    @GetMapping("/Login")
    public String login() {
        return "Login.html";
    }

    @GetMapping("/Register")
    public String getMethodName() {
        return "Register.html";
    }

}
