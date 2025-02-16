package poly.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import poly.edu.Repository.*;
import poly.edu.Service.CookieService;
import poly.edu.Service.ParamService;
import poly.edu.Service.SessionService;

@Controller
public class OderController {
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	OrderDetailsRepository odertDetailRepo;
}
