package controller.commands;

import java.util.ArrayList;
import java.util.List;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DatabaseProfessionalDAO;
import model.dao.ProfessionalDatabaseFactory;
import model.entities.Professional;

public class ClickTeamProfessional implements Command {
	
	private static DatabaseProfessionalDAO repositorio = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
	List<Professional> listCompletaProfessionals = new ArrayList<>();
			
		
			listCompletaProfessionals = repositorio.getAllProfessional();

			req.setAttribute("lista_professionals", listCompletaProfessionals);
			return "team_professional.jsp";
	


	}

}
