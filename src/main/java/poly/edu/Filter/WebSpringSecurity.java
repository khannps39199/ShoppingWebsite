package poly.edu.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class WebSpringSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Nếu không dùng token bảo mật
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/account/**", "/css/**", "/js/**", "/images/**").permitAll() // cho phép truy cập không cần login
                        .requestMatchers("/admin/**").hasRole("ADMIN") // chỉ role ADMIN
                        .requestMatchers("/pay", "/cart", "/addToCart").authenticated() // chỉ cần login
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/account/login") // trang login custom
                        .loginProcessingUrl("/account/login") // URL xử lý POST login
                        .defaultSuccessUrl("/", true) // trang sau khi login thành công
                        .failureUrl("/account/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/account/logout")
                        .logoutSuccessUrl("/account/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }
}
