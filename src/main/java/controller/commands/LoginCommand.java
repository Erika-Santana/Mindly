package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		var login = req.getAttribute("login");
		var password = req.getAttribute("password");
		
		
		
		
		return null;
	}

}
