package poly.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {

    @GetMapping("/pay")
    public String showPayPage(Model model) {
    	model.addAttribute("Component","pay.html");
        return "UserLayout"; 
    }
}