package poly.edu.Entity;

public enum OrderStatus {
    PENDING,       // Chờ xác nhận
    PROCESSING,    // Chờ lấy hàng
    SHIPPED,       // Đang giao hàng
    DELIVERED,     // Đã giao
    CANCELLED      // Hủy / Trả hàng
}
