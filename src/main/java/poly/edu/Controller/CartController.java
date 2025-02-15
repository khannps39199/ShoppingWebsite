
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
	    private CartRepository cartRepo;
	@GetMapping("/cart")
	public String getMethodName(Model model) {
		model.addAttribute("Component","GioHang.html");
		return "UserLayout";
	}
	@PostMapping("/addToCart")
	public String addToCart(Model model) {
		long productId=(long) paramService.getInt("productId", 0);
		User userId=sessionService.get("login");
		
	    List<Cart> cartData = userId.getCarts();
	    Map<Long, Product> cartMap = new HashMap<>();
	    for (Cart cartItem : cartData) {
	    	 System.out.println(cartItem.getProduct().getId()+" "+productId);
  	    }
	    if(!cartData.isEmpty()) {
	    	 for (Cart cartItem : cartData) {
	  	        cartMap.put(cartItem.getProduct().getId(), cartItem.getProduct());
	  	       System.out.println(cartItem.getCartId()+cartItem.getProduct().getId());
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
	    // Chuyển danh sách giỏ hàng thành Map<ProductID, Cart>
	  
//	    if (cartMap.get(productId).getProduct().getId()==productId) {
//	        Cart existingCart = cartMap.get(productId);
//	        existingCart.setQuantity(existingCart.getQuantity() + 1);
//	        cartRepo.save(existingCart);
//	    } else {
//	        // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
//	        Product product = productRepo.findById(productId).orElse(null);
//	        if (product != null) {
//	            Cart newCart = new Cart(userId, product, 1);
//	            cartRepo.save(newCart);
//	        }
//	    }
//	    model.addAttribute("cartList",userId.getCarts());
		model.addAttribute("Component","GioHang.html");
		return "UserLayout";
	}
	
}	