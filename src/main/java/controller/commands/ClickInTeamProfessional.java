package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClickInTeamProfessional implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		return "team_professional.jsp";
	}

}
