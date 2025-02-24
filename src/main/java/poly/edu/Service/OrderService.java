package poly.edu.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poly.edu.Entity.Order;
import poly.edu.Repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // ✅ Lấy danh sách đơn hàng của User theo trạng thái (Tối ưu với JPA Query)
    public Map<String, List<Order>> getOrdersByStatus(Integer userId) {
        List<Order> orders = orderRepository.findByUserUserId(userId);
        return orders.stream().collect(Collectors.groupingBy(Order::getStatus));
    }

    // ✅ Lấy chi tiết đơn hàng của user, throw exception nếu không tìm thấy
    public Order getOrderDetail(Integer orderId, Integer userId) {
        return orderRepository.findByOrderIdAndUserUserId(orderId, userId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng ID: " + orderId));
    }

    // ✅ Lấy chi tiết đơn hàng cho Admin
    public Order getAdminOrderDetail(Integer orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng ID: " + orderId));
    }

    // ✅ Lấy tất cả đơn hàng và nhóm theo trạng thái (Tối ưu bằng JPA Query)
    public Map<String, List<Order>> getAllOrdersGroupedByStatus() {
        return orderRepository.findAll()
            .stream()
            .collect(Collectors.groupingBy(Order::getStatus));
    }

    // ✅ Lấy tất cả đơn hàng (Không nhóm)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Lấy đơn hàng theo ID (Dùng Optional tránh NullPointerException)
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    // ✅ Cập nhật đơn hàng (Thêm @Transactional để đảm bảo tính nhất quán)
    @Transactional
    public void updateStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng ID: " + orderId));
        order.setStatus(status);
        orderRepository.save(order);
    }

}
