package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClickSignInCommand implements Command{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		return "choose_sign_in.jsp";
	}


}
