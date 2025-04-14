package poly.edu;

import org.testng.annotations.DataProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import poly.edu.Controller.LoginController;
import poly.edu.Entity.User;
import poly.edu.Repository.UserRepository;
import poly.edu.Service.*;

public class ASM_LOGIN_TETSSTBYJUNIT {

    private CookieService cookieService;
    private ParamService paramService;
    private SessionService sessionService;
    private UserRepository userRepo;
    private Model model;

    private LoginController loginController;

    @BeforeEach
    public void setup() {
        cookieService = mock(CookieService.class);
        paramService = mock(ParamService.class);
        sessionService = mock(SessionService.class);
        userRepo = mock(UserRepository.class);
        model = mock(Model.class);

        loginController = new LoginController(cookieService, paramService, sessionService, userRepo);
    }

    @Test
    public void testLoginPage_WhenNotLoggedIn_ReturnsLoginView() {
        when(sessionService.get("login")).thenReturn(null);

        String viewName = loginController.loginPage(model);

        assertEquals("login", viewName);
    }

    @Test
    public void testLoginPage_WhenLoggedIn_ShouldRedirect() {
        when(sessionService.get("login")).thenReturn(new User());

        String viewName = loginController.loginPage(model);

        assertEquals("redirect:/asm", viewName);
    }

    @Test
    public void testLogin_WithInvalidEmail_ShouldReturnLoginAndError() throws Exception {
        when(paramService.getString("email", "")).thenReturn("invalid@example.com");
        when(paramService.getString("password", "")).thenReturn("password");
        when(userRepo.findByEmail("invalid@example.com")).thenReturn(Optional.empty());

        String result = loginController.login(model);

        verify(model).addAttribute("error", "Email không tồn tại!");
        assertEquals("login", result);
    }

    @Test
    public void testLogin_WithWrongPassword_ShouldReturnLoginAndError() throws Exception {
        User user = new User();
        user.setEmail("john@example.com");
        user.setPasswordHash("correctPass");

        when(paramService.getString("email", "")).thenReturn("john@example.com");
        when(paramService.getString("password", "")).thenReturn("wrongPass");
        when(userRepo.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        String result = loginController.login(model);

        verify(model).addAttribute("error", "Mật khẩu không đúng!");
        assertEquals("login", result);
    }

    @Test
    public void testLogin_WithCorrectCredentialsAndRememberMe_ShouldRedirect() throws Exception {
        User user = new User();
        user.setEmail("john@example.com");
        user.setPasswordHash("correctPass");

        when(paramService.getString("email", "")).thenReturn("john@example.com");
        when(paramService.getString("password", "")).thenReturn("correctPass");
        when(paramService.getBoolean("remember", false)).thenReturn(true);
        when(userRepo.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        String result = loginController.login(model);

        verify(sessionService).set("login", user);
        verify(cookieService).add("User", "john@example.com", 10);
        assertEquals("redirect:/asm", result);
    }

    @Test
    public void testLogout_ShouldRedirect() {
        String result = loginController.logout();

        verify(cookieService).remove("User");
        verify(sessionService).remove("login");
        assertEquals("redirect:/account/login", result);
    }
}

