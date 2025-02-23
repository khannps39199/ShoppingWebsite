package poly.edu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.Entity.*;
import poly.edu.Repository.*;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    private UserOrderRepository userOrderRepo;

    @Autowired
    private OrderDetailsRepository orderDetailRepo;

//    public Map<String, List<UserOrder>> getOrdersByStatus(Integer userId) {
//        List<String> orderStatuses = Arrays.asList("pending", "processing", "shipped", "delivered", "cancelled");
//        Map<String, List<UserOrder>> ordersByStatus = new HashMap<>();
//
//        for (String status : orderStatuses) {
//            List<UserOrder> orders = userOrderRepo.findByUser_UserIdAndStatus(userId, status);
//
//            System.out.println("Debug: Orders for status [" + status + "] = " + orders.size()); // Kiểm tra số lượng đơn hàng
//            
//            for (UserOrder userOrder : orders) {
//                List<OrderDetails> details = orderDetailRepo.findByOrder(userOrder.getOrder());
//                userOrder.getOrder().setOrderDetails(details);
//            }
//
//            ordersByStatus.put(status, orders);
//        }
//
//        return ordersByStatus;
//    }

}
