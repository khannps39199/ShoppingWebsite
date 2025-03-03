package poly.edu.Configment;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Kích hoạt một "simple broker" trên prefix "/topic"
        config.enableSimpleBroker("/topic");
        // Định nghĩa prefix cho các endpoint server nhận: "/app"
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Tạo endpoint cho client kết nối WebSocket
        registry.addEndpoint("/ws")  // endpoint
                .setAllowedOrigins("*")
                .withSockJS();       // dùng SockJS fallback nếu trình duyệt ko hỗ trợ WS
    }
}
