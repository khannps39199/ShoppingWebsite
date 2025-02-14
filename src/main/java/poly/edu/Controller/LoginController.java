package poly.edu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;
import poly.edu.Service.*;
@Controller
public class LoginController {
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	UserRepository userRepo;
	  @GetMapping("/account/login")
		public String login1() {
		return "login";
		}
	    @GetMapping("/account/logout")
		public String logout() {
	    	cookieService.remove("User");
	    	sessionService.remove("login");
		return "login";
		}
	    @PostMapping("/account/login")
		public String login2() {
	    	String un = paramService.getString("email", "");
			String pw = paramService.getString("password", "");
			 boolean rm = paramService.getBoolean("remember", false);
			 User login=userRepo.searchByEmail(un).orElse(null);
			 System.out.println(un+pw+rm+ login.getUsername());
			 if(login !=null) {
				 if(login.getEmail().equals(un)&&login.getPasswordHash().equals(pw)) {
			    		sessionService.set("login", login);
				    	 if(rm==true) {
						    	cookieService.add("User", un, 10);
					}
				    	 return "redirect:/asm";
				 }
			 }
			 return "redirect:/account/login";
	}
}
