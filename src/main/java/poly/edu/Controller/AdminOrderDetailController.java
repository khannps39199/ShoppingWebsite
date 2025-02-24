package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Entity.Order;
import poly.edu.Repository.OrderRepository;

@Controller
public class AdminOrderDetailController {
    @Autowired
    OrderRepository orderRepo;

    @GetMapping("/admin/orderDetail")
    public String getAdminOrderDetail(@RequestParam("id") Integer orderId, Model model) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order == null) {
            return "redirect:/admin/orders"; // Nếu không có đơn hàng, quay lại danh sách
        }
        model.addAttribute("order", order);
        model.addAttribute("Component", "AdminOrderDetail.html");
        return "AdminLayout"; // Trả về layout của admin
    }
}
