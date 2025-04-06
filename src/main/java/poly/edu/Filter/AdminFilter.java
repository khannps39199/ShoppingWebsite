package poly.edu.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;
import poly.edu.Service.CookieService;
import poly.edu.Service.ParamService;
import poly.edu.Service.SessionService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@WebFilter({"/admin/*", "/pay", "/cart", "/addToCart"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        User user = (User) req.getSession().getAttribute("login");
        String path = req.getRequestURI();

        if (user == null) {
            res.sendRedirect("/account/login");
            return;
        }

        if (path.startsWith("/admin/") && !"Admin".equals(user.getRole())) {
            res.sendRedirect("/account/login");
            return;
        }

        chain.doFilter(request, response);
    }

}
