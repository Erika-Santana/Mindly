package controller.commands;

import java.util.List;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DatabaseProfessionalDAO;
import model.dao.ProfessionalDatabaseFactory;
import model.entities.Portifolio;
import model.entities.Professional;

public class ClickPortfolio implements Command{
	

	private static DatabaseProfessionalDAO repositorio = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		var session =  req.getSession(false);
		List<Portifolio> portfolio = null;
		
		
		if (session != null && session.getAttribute("userType") != null) {
			Professional prof = (Professional)session.getAttribute("professional");
			
			if( repositorio.doesProfessionalHasPortfolio(prof.getID())) {
				//portifolio = repositorio.getPortifolioByID();
				
			}
	
			return "paginaAgendamentos.jsp";
		}else {
			
			req.setAttribute("doLogin", "Faça o login para realizar um agendamento");
			return "login.jsp";
		}
		return null;
	}

}
