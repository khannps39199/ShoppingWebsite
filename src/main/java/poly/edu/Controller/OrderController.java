package poly.edu.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Entity.Order;
import poly.edu.Entity.User;
import poly.edu.Service.OrderService;
import poly.edu.Service.SessionService;

@Controller
public class OrderController {
	@Autowired
    private SessionService sessionService;
	
    @Autowired
    private OrderService orderService;

    // Trạng thái đơn hàng (Sử dụng đúng với 5 trạng thái của bạn)
    private static final String[] ORDER_STATUSES = { "Pending", "Processing", "Shipped", "Delivered", "Cancelled" };

    @GetMapping("/user/order")
    public String getOrder(@RequestParam(defaultValue = "Pending") String status, Model model) {
        if (status == null || status.trim().isEmpty()) {
            status = "Pending";
        }

        System.out.println("Debug: status = " + status);

        // Lấy User đang đăng nhập từ session
        User user = (User) sessionService.get("login");
        if (user == null) {
            return "redirect:/account/login";
        }

        // Lấy danh sách đơn hàng của User đang đăng nhập theo trạng thái
        Map<String, List<Order>> ordersByStatus = orderService.getOrdersByStatus(user.getUserId());

        // Thêm dữ liệu vào Model để truyền sang View
        String[] orderStatuses = { "Pending", "Processing", "Shipped", "Delivered", "Cancelled" };
        model.addAttribute("orderStatuses", orderStatuses);
        model.addAttribute("currentTab", status);
        model.addAttribute("ordersByStatus", ordersByStatus);
        model.addAttribute("Component", "UsersOrder.html");

        return "UserLayout";
    }
    
    @GetMapping("/user/order-detail")
    public String getOrderDetail(@RequestParam("orderId") Integer orderId, Model model) {
        User user = (User) sessionService.get("login");
        if (user == null) {
            return "redirect:/account/login";
        }

        Order order = orderService.getOrderDetail(orderId, user.getUserId());
        if (order == null) {
            return "redirect:/user/order";
        }

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", order.getOrderDetails());

        // Tính tổng tiền
        double grandTotal = order.getOrderDetails().stream()
        	    .mapToDouble(detail -> detail.getPrice().doubleValue() 
        	                        * detail.getQuantity() 
        	                        * (1 - detail.getDiscount().doubleValue() / 100.0))
        	    .sum();
        	model.addAttribute("grandTotal", grandTotal);
        	model.addAttribute("Component", "UserOrderDetail.html");
        return "UserLayout";
    }



}
