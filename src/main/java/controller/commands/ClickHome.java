package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClickHome implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		return "home.jsp";
		
	}

}
