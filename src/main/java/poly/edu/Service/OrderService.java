package poly.edu.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.edu.Entity.Order;
import poly.edu.Repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Lấy danh sách đơn hàng của User theo từng trạng thái
    public Map<String, List<Order>> getOrdersByStatus(Integer userId) {
        Map<String, List<Order>> ordersByStatus = new HashMap<>();
        String[] orderStatuses = { "Pending", "Processing", "Shipped", "Delivered", "Cancelled" };

        for (String status : orderStatuses) {
            List<Order> orders = orderRepository.findByUserUserIdAndStatus(userId, status);
            ordersByStatus.put(status.toLowerCase(), orders);
        }

        return ordersByStatus;
    }
    public Order getOrderDetail(Integer orderId, Integer userId) {
        Optional<Order> optionalOrder = orderRepository.findByOrderIdAndUserUserId(orderId, userId);
        return optionalOrder.orElse(null);
    }

}