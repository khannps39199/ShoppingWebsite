package poly.edu.Controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import poly.edu.Entity.*;
import poly.edu.Repository.*;
import poly.edu.Service.CookieService;
import poly.edu.Service.ParamService;

import poly.edu.Service.SessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderDetailsController {
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	UserRepository	userRepo;
	@Autowired
	CartRepository	cartRepo;
	@Autowired
	OrderDetailRepository odertDetailRepo;
	@GetMapping("/order")
	public String getOrder(Model model) {
		model.addAttribute("Component","UsersOrder.html");
		return "UserLayout";
	}
	@GetMapping("/orderDetail")
	public String getOrderDetails(Model model) {
		model.addAttribute("Component","UsersOrder.html");
		return "UserLayout";
	}
	@PostMapping("/order")
	public String insertOrder(Model model) {
		User user=userRepo.findById( ((User) sessionService.get("login")).getUserId()).orElse(null);
		List<Cart> cartToOrder=user.getCarts();
		String paymentMethod=paramService.getString("Payment_method", "WHEN_RECEIVE");
		System.out.println(paymentMethod);
		double totalAmount=0;
		for(Cart tempItem:cartToOrder) {
			totalAmount=totalAmount+tempItem.getQuantity()*tempItem.getProduct().getPrice()*tempItem.getProduct().getDiscount();
		}
		Order order=new Order( user,Timestamp.from(Instant.now()),totalAmount,"Pending",user.getAddress(),paymentMethod);
		orderRepo.save(order);
		
		List<OrderDetail> listOrderDetails = new ArrayList<>();
		for (Cart itemCart : cartToOrder) {
		    OrderDetail tempOrderDetail = new OrderDetail(
		        order, 
		        itemCart.getProduct(), 
		        itemCart.getQuantity(), 
		        BigDecimal.valueOf(itemCart.getProduct().getPrice()), 
		        BigDecimal.valueOf(itemCart.getProduct().getDiscount())
		    );
		    listOrderDetails.add(tempOrderDetail);
		}
		cartRepo.handleOrder(user.getUserId());
		
		odertDetailRepo.saveAll(listOrderDetails);
		model.addAttribute("Component","UsersOrder.html");
		return "redirect:/user/order";
	}
}