package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import poly.edu.Repository.*;
import poly.edu.Service.CookieService;
import poly.edu.Service.ParamService;
import poly.edu.Service.SessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OderController {
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	OrderDetailsRepository odertDetailRepo;
	@GetMapping("/order")
	public String getOrder(Model model) {
		model.addAttribute("Component","UsersOrder.html");
		return "UserLayout";
	}
	@PostMapping("/order")
	public String insertOrder(Model model) {
		model.addAttribute("Component","UsersOrder.html");
		return "redirect:/order";
	}
}
