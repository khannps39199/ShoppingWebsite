package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Entity.OrderDetail;
import poly.edu.Entity.User;
import poly.edu.Entity.UserOrder;
import poly.edu.Repository.OrderDetailRepository;
import poly.edu.Repository.UserOrderRepository;
import poly.edu.Service.OrderService;
import poly.edu.Service.SessionService;

import java.util.*;

@Controller
public class OrderController {
	@Autowired
    private UserOrderRepository userOrderRepo;

    @Autowired
    private OrderDetailRepository orderDetailRepo;
    
    @Autowired
    private SessionService sessionService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/order")
    public String getOrder(@RequestParam(defaultValue = "Pending") String status, Model model) {
        if (status == null || status.trim().isEmpty()) {
            status = "Pending";
        }

        System.out.println("Debug: status = " + status);

        User user = (User) sessionService.get("login");
        if (user == null) {
            return "redirect:/account/login";
        }

        Map<String, List<UserOrder>> ordersByStatus = orderService.getOrdersByStatus(user.getUserId());

        model.addAttribute("ordersByStatus", ordersByStatus);
        model.addAttribute("orderStatuses", Arrays.asList("Pending", "Processing", "Shipped", "Delivered", "Cancelled"));
        model.addAttribute("currentTab", status);
        model.addAttribute("Component", "UsersOrder.html");

        return "UserLayout";
    }
    @GetMapping("/order-detail")
    public String orderDetail(@RequestParam("orderId") Integer orderId, Model model) {
        Optional<UserOrder> userOrderOpt = userOrderRepo.findById(orderId);
        
        if (!userOrderOpt.isPresent()) {
            return "redirect:/user/order";
        }

        UserOrder userOrder = userOrderOpt.get();
        List<OrderDetail> orderDetails = orderDetailRepo.findByOrder(userOrder.getOrder());

        double grandTotal = orderDetails.stream()
            .mapToDouble(detail -> detail.getPrice() * detail.getQuantity() * (1 - detail.getDiscount() / 100.0))
            .sum();

        model.addAttribute("order", userOrder.getOrder());
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("Component", "UserOrderDetail.html");
        return "UserLayout";
    }


}
