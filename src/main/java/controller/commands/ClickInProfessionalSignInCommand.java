package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClickInProfessionalSignInCommand implements Command{
@Override
public String execute(HttpServletRequest req, HttpServletResponse resp) {
	
	return "signin_professional.jsp";
}
}
