package poly.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {

    @GetMapping("/pay")
    public String showPayPage() {
        return "pay"; // Trả về giao diện pay.html
    }
}