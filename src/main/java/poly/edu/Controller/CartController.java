
package poly.edu.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.DTO.CartItemUpdate;
import poly.edu.DTO.CartUpdateForm;
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
//		@GetMapping("/cart")
//		public String getMethodName(Model model) {
//			User user=userRepo.findById( ((User) sessionService.get("login")).getUserId()).orElse(null);
//			model.addAttribute("cartList",user.getCarts());
//			model.addAttribute("Component","Cart.html");
//			return "UserLayout";
//		}

    @PostMapping("/addToCart")
    public String addToCart(Model model) {
        long productId = (long) paramService.getInt("productId", 0);
        User userId = userRepo.findById(((User) sessionService.get("login")).getUserId()).orElse(null);
        List<Cart> cartData = userId.getCarts();
        Map<Long, Product> cartMap = new HashMap<>();
        if (!cartData.isEmpty()) {
            for (Cart cartItem : cartData) {
                cartMap.put(cartItem.getProduct().getProductID(), cartItem.getProduct());
            }
            if (cartMap.containsKey(productId)) {
                Cart existingCart = (Cart) cartRepo.getCartByUserAndProduct(userId.getUserId(), productId);
                existingCart.setQuantity(existingCart.getQuantity() + 1);
                cartRepo.save(existingCart);
            } else {
                Product product = productRepo.findById(productId).orElse(null);
                if (product != null) {
                    Cart newCart = new Cart(userId, product, 1);
                    cartRepo.save(newCart);
                }
            }
        } else {
            Product product = productRepo.findById(productId).orElse(null);
            if (product != null) {
                Cart newCart = new Cart(userId, product, 1);
                cartRepo.save(newCart);
            }
        }


        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
    	User user=null;
    	try {
    		 user = userRepo.findById(
    	                ((User) sessionService.get("login")).getUserId()
    	        ).orElse(null);
    	}catch(Exception ex){
    		
    	}
       

        if (user == null) {
            return "redirect:/login";
        }

        List<Cart> cartList = user.getCarts();

        CartUpdateForm cartUpdateForm = new CartUpdateForm();
        List<CartItemUpdate> itemList = new java.util.ArrayList<>();
        for (Cart c : cartList) {
            CartItemUpdate ciu = new CartItemUpdate();
            ciu.setProductId(c.getProduct().getProductID());
            ciu.setQuantity(c.getQuantity());
            ciu.setSelected(true);
            itemList.add(ciu);
        }
        cartUpdateForm.setCartItemList(itemList);
        cartUpdateForm.setVoucherCode("");

        double subTotal = 0;
        double shippingFee = 20;
        double discount = 0;
        for (Cart c : cartList) {
            subTotal += c.getProduct().getPrice() * c.getQuantity();
        }
        double total = subTotal + shippingFee - discount;

        model.addAttribute("cartUpdateForm", cartUpdateForm);
        model.addAttribute("cartList", cartList);
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("discount", discount);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("total", total);
        model.addAttribute("Component", "Cart.html");

        return "UserLayout";
    }

    @PostMapping("/cart/update")
    public String updateCart(
            @ModelAttribute("cartUpdateForm") CartUpdateForm cartUpdateForm,
            @RequestParam("action") String action,
            Model model
    ) {

        User user = userRepo.findById(
                ((User) sessionService.get("login")).getUserId()
        ).orElse(null);

        if (user == null) {
            return "redirect:/login";
        }


        for (CartItemUpdate item : cartUpdateForm.getCartItemList()) {

            Cart cart = cartRepo.getCartByUserAndProduct(user.getUserId(), item.getProductId());
            if (cart != null) {

                if (!item.isSelected() || item.getQuantity() <= 0) {
                    cartRepo.delete(cart);
                } else {
                    cart.setQuantity(item.getQuantity());
                    cartRepo.save(cart);
                }
            }
        }

        double discount = 0;
        String voucherCode = cartUpdateForm.getVoucherCode();
        if ("DISCOUNT10".equalsIgnoreCase(voucherCode)) {
            discount = 10;
        } else if ("DISCOUNT20".equalsIgnoreCase(voucherCode)) {
            discount = 20;
        }

        List<Cart> updatedCartList = user.getCarts();
        double subTotal = 0;
        for (Cart c : updatedCartList) {
            subTotal += c.getProduct().getPrice() * c.getQuantity();
        }

        double shippingFee = 20;
        double total = subTotal + shippingFee - discount;
        if (total < 0) total = 0;

        model.addAttribute("cartList", updatedCartList);
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("discount", discount);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("total", total);
        model.addAttribute("voucherCode", voucherCode);
        model.addAttribute("Component", "Cart.html");
        if ("checkout".equals(action)) {
            return "redirect:/checkout";
        }

        return "UserLayout";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("productId") Long productId, Model model) {
        User user = userRepo.findById(
                ((User) sessionService.get("login")).getUserId()
        ).orElse(null);

        if (user == null) {
            return "redirect:/login";
        }

        Cart cart = cartRepo.getCartByUserAndProduct(user.getUserId(), productId);
        if (cart != null) {
            cartRepo.delete(cart);
        }

        List<Cart> updatedCartList = user.getCarts();
        double subTotal = 0;
        for (Cart c : updatedCartList) {
            subTotal += c.getProduct().getPrice() * c.getQuantity();
        }

        double shippingFee = 20;
        double discount = 0;
        double total = subTotal + shippingFee - discount;
        if (total < 0) total = 0;

        model.addAttribute("cartList", updatedCartList);
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("discount", discount);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("total", total);
        model.addAttribute("Component", "Cart.html");

        return "UserLayout";
    }

}	