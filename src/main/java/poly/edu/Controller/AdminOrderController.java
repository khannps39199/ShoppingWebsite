package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Entity.Order;
import poly.edu.Entity.OrderDetail;
import poly.edu.Repository.OrderRepository;
import poly.edu.Repository.OrderDetailRepository;
import poly.edu.Service.OrderService;

import java.util.*;

@Controller
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepo; // 

    @Autowired
    private OrderDetailRepository orderDetailRepo;

    @GetMapping("/admin/order")
    public String getAllOrders(Model model) {
        List<Order> allOrders = orderRepo.findAll(); // Lấy tất cả đơn hàng

        // Nhóm đơn hàng theo trạng thái
        Map<String, List<Order>> ordersByStatus = new HashMap<>();
        for (String status : Arrays.asList("All", "Pending", "Processing", "Shipped", "Delivered", "Cancelled")) {
            if (status.equals("All")) {
                ordersByStatus.put(status, allOrders);
            } else {
                ordersByStatus.put(status, new ArrayList<>());
            }
        }

        for (Order order : allOrders) {
            String status = order.getStatus(); 
            ordersByStatus.computeIfAbsent(status, k -> new ArrayList<>()).add(order);
        }


        model.addAttribute("ordersByStatus", ordersByStatus);
        model.addAttribute("orderStatuses", Arrays.asList("All", "Pending", "Processing", "Shipped", "Delivered", "Cancelled"));
        model.addAttribute("currentTab", "All");
        model.addAttribute("Component", "AdminOrders.html");

        return "AdminLayout";
    }

    
    @GetMapping("/admin-order-detail")
    public String adminOrderDetail(@RequestParam("orderId") Integer orderId, Model model) {
        Optional<Order> orderOpt = orderRepo.findById(orderId); // 🔥 Đổi từ UserOrder -> Order

        if (!orderOpt.isPresent()) {
            return "redirect:/admin/order"; // Nếu không tìm thấy, quay lại danh sách đơn hàng
        }

        Order order = orderOpt.get();
        List<OrderDetail> orderDetails = orderDetailRepo.findByOrderIdWithProduct(orderId); // 🔥 Truy vấn bằng Order trực tiếp

        double grandTotal = orderDetails.stream()
            .mapToDouble(detail -> detail.getPrice() * detail.getQuantity() * (1 - detail.getDiscount() / 100.0))
            .sum();

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("Component", "AdminOrderDetail.html"); // Trang quản lý đơn hàng admin
        return "AdminLayout";
    }
}
