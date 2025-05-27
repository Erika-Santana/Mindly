package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClickContactUsCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//Lógica de verificação de sessão. Se o usuário estiver logado, e não é profissional, mas patient.
		return "contact_us.jsp";
	}

}
