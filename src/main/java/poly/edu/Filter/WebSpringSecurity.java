package poly.edu.Filter;

import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.config.Customizer;

import poly.edu.Entity.MyUserDetailsService;
import poly.edu.Entity.UserDetail;

@Configuration
public class WebSpringSecurity {

	@Autowired
    private MyUserDetailsService myUserDetailsService;
	@Autowired
	private RoleDebugFilter roleDebugFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(roleDebugFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/pay", "/cart", "/addToCart").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/account/login")
                        .permitAll()
                        .failureForwardUrl("/account/login")
                        .successForwardUrl("/asm")
                        .loginProcessingUrl("/_spring_security_should_not_use_this")
                )
                .logout(logout -> logout
                        .logoutUrl("/account/logout")
                        .logoutSuccessUrl("/account/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(myUserDetailsService) // Custom UserDetailsService
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
