package poly.edu.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.Entity.Order;
import poly.edu.Entity.User;
import poly.edu.Repository.OrderRepository;
import poly.edu.Service.OrderService;
import poly.edu.Service.SessionService;

@Controller
public class OrderController {
	@Autowired
    private SessionService sessionService;
	
	@Autowired
	OrderService OrderService;
    @Autowired
    OrderRepository orderRepo;

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
        Map<String, List<Order>> ordersByStatus = OrderService.getOrdersByStatus(user.getUserId());

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

        Order order = OrderService.getOrderDetail(orderId, user.getUserId());
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

    
    @GetMapping("/shipper/orders")
    public String listOrders(Model model){
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "shipper_orders";
    }
    @PostMapping("/shipper/update-status")
    public String updateOrderStatus(@RequestParam("orderId") Integer orderId, RedirectAttributes redirectAttributes) {
        // Kiểm tra xem đơn hàng có tồn tại không
        Order order = OrderService.findById(orderId);
        if (order == null) {
            redirectAttributes.addFlashAttribute("error", "Đơn hàng không tồn tại!");
            return "redirect:/shipper/orders"; // Điều hướng về trang danh sách đơn hàng
        }

        if(order.getStatus().equals("Pending")) {
        	order.setStatus("Processing");
            OrderService.save(order);
        } else if(order.getStatus().equals("Processing")) {
        	order.setStatus("Shipped");
            OrderService.save(order);
        } else if(order.getStatus().equals("Shipped")) {
        	order.setStatus("Delivered");
            OrderService.save(order);
        } else{
        	order.setStatus("Cancelled");
            OrderService.save(order);
        }


        redirectAttributes.addFlashAttribute("success", "Cập nhật trạng thái thành công!");
        return "redirect:/shipper/orders";
    }
}
