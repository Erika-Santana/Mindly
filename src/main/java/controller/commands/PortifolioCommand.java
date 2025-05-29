package controller.commands;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DatabaseProfessionalDAO;
import model.dao.ProfessionalDatabaseFactory;
import model.entities.Professional;

public class PortifolioCommand implements Command{
	
	private static DatabaseProfessionalDAO repositorio = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		var session = req.getSession(false);
		
		if (session != null && session.getAttribute("userType") != null) {
			
			return "portifolio.jsp";
		}else {
			
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.print("id " + id);
			Professional prof = repositorio.getProfessionalByID(id);
			req.setAttribute("professional", prof);
			
			return "portifolio.jsp";
		}
		
	}

}
