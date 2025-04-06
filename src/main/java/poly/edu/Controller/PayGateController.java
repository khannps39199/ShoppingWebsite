package poly.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PayGateController {
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
