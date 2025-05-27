package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClickInAppointments implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		var session = req.getSession(false);
		if (session != null && session.getAttribute("doLogin") != null) {
			return "paginaAgendamentos.jsp";
		}else {
			req.setAttribute("doLogin", "Faça o login para realizar um agendamento");
			return "login.jsp";
		}

	}

}
