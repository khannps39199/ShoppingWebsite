package poly.edu.Configment;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.edu.Entity.User;
import poly.edu.Filter.AdminFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer, HandlerInterceptor{
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/"); // Trỏ đúng vào static/images/
    }
//    @Bean
//    public FilterRegistrationBean<AdminFilter> adminFilter() {
//        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AdminFilter());
//        registrationBean.addUrlPatterns("/pay","/admin/*","/cart");
//        registrationBean.setOrder(1); 
//        return registrationBean;
//    }
    @Override
    public boolean preHandle(HttpServletRequest request,
    HttpServletResponse response, Object handler) throws Exception {
    User user = (User) request.getSession().getAttribute("login");
    System.out.println(request.getRequestURI()+ ", " + Timestamp.from(Instant.now())+ ", " + user.getFullName());
    return true;
    }
    
}
