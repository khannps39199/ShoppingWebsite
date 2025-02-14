package poly.edu.Service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CookieService {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public String getValue(String name) {
        Cookie cookie = get(name);
        return (cookie != null) ? cookie.getValue() : "";
    }

   
    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/"); 
        cookie.setMaxAge(hours * 60 * 60*24);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * Xóa cookie khỏi client
     * @param name tên cookie cần xóa
     */
    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0); // Hết hạn ngay lập tức
        response.addCookie(cookie);
    }
}
