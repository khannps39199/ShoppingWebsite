package poly.edu.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class WebSpringSecurity {
    @Autowired
    HttpSecurity http;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Modern syntax to disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/account/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/pay", "/cart", "/addToCart").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/account/login")      // Show this page
                        .permitAll()                      // Allow everyone to access it
                        .failureForwardUrl("/account/login") // Optional: forward instead of redirect
                        .successForwardUrl("/asm")           // Optional: forward after success
                        .loginProcessingUrl("/_spring_security_should_not_use_this") // fake URL
                )

                .logout(logout -> logout
                        .logoutUrl("/account/logout")
                        .logoutSuccessUrl("/account/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }
}
