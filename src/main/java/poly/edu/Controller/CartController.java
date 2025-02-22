
package poly.edu.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.Entity.*;
import poly.edu.Repository.*;
import poly.edu.Service.CookieService;
import poly.edu.Service.ParamService;
import poly.edu.Service.SessionService;

@Controller
public class CartController {
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	    private CartRepository cartRepo;
	@GetMapping("/cart")
	public String getMethodName(Model model) {
		User user=userRepo.findById( ((User) sessionService.get("login")).getUserId()).orElse(null);
		model.addAttribute("cartList",user.getCarts());
		model.addAttribute("Component","Cart.html");
		return "UserLayout";
	}
	
	@PostMapping("/addToCart")
	public String addToCart(Model model) {
		long productId=(long) paramService.getInt("productId", 0);
		User userId=userRepo.findById( ((User) sessionService.get("login")).getUserId()).orElse(null);		
	    List<Cart> cartData = userId.getCarts();
	    Map<Long, Product> cartMap = new HashMap<>();	  
	    if(!cartData.isEmpty()) {
	    	 for (Cart cartItem : cartData) {
	  	        cartMap.put(cartItem.getProduct().getProductID(), cartItem.getProduct());
	  	    }	    	 
	  	  if (cartMap.containsKey(productId)) {
		        Cart existingCart =(Cart) cartRepo.getCartByUserAndProduct( userId.getUserId(), productId);
		        existingCart.setQuantity(existingCart.getQuantity() + 1);
		        cartRepo.save(existingCart);
		    }else {
		    	Product product = productRepo.findById(productId).orElse(null);
		        if (product != null) {
		            Cart newCart = new Cart(userId, product, 1);
		            cartRepo.save(newCart);
		        }
		    }
	    }else {
	    	Product product = productRepo.findById(productId).orElse(null);
	        if (product != null) {
	            Cart newCart = new Cart(userId, product, 1);
	            cartRepo.save(newCart);
	        }
	    }
	   
	   
		return "redirect:/cart";
	}
	
}	