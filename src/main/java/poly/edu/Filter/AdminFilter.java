package poly.edu.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;
import poly.edu.Service.CookieService;
import poly.edu.Service.ParamService;
import poly.edu.Service.SessionService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminFilter implements Filter{
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        if (path.startsWith("/admin/")) {
            User user = (User) req.getSession().getAttribute("login");
            if (user == null || !"Admin".equals(user.getRole())) { // Kiểm tra role đúng cách
                res.sendRedirect("/account/login");
                return;
            }
        }
        chain.doFilter(request, response);
    }

}
