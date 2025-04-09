package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.Repository.OrderRepository;
import poly.edu.Service.OrderService;

import java.util.List;
import java.util.Map;

@Controller
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepo;

    // Danh sách trạng thái đơn hàng
    private static final String[] ORDER_STATUSES = {"Pending", "Processing", "Shipped", "Delivered", "Cancelled"};

    @GetMapping("/admin/order")
    public String showAllOrders(@RequestParam(defaultValue = "Pending") String status, Model model) {
        if (status == null || status.trim().isEmpty()) {
            status = "Pending";
        }

        // Lấy danh sách đơn hàng theo trạng thái
        Map<String, List<poly.edu.Entity.Order>> ordersByStatus = orderService.getAllOrdersGroupedByStatus();

        // Gửi dữ liệu đến Thymeleaf
        model.addAttribute("pageTitle", "Quản lý đơn hàng");
        model.addAttribute("orderStatuses", ORDER_STATUSES);
        model.addAttribute("currentTab", status);
        model.addAttribute("orders", ordersByStatus.getOrDefault(status, List.of()));
        model.addAttribute("Component", "AdminOrders.html"); // Định nghĩa component sẽ load vào layout

        return "AdminLayout"; // Trả về trang AdminLayout
    }


    @GetMapping("/admin/order/detail/{id}")
    public String viewOrderDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("order", orderRepo.findById(id).orElse(null));
        return "AdminOrderDetail";
    }

    @PostMapping("/admin/order/update-status/{id}")
    public String updateOrderStatus(@PathVariable("id") Integer id,
                                    @RequestParam("status") String status) {
        orderService.updateStatus(id, status);
        return "redirect:/admin/order?status=" + status;
    }

}
