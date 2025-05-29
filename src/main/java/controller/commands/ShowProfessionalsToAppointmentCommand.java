package controller.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DatabaseProfessionalDAO;
import model.dao.ProfessionalDatabaseFactory;
import model.entities.Professional;
	
public class ShowProfessionalsToAppointmentCommand implements Command{


	private static DatabaseProfessionalDAO repositorio = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		List<Professional> listCompletaProfessionals = new ArrayList<>();
		
		var session = req.getSession(false);
		
		
		if (session != null && session.getAttribute("userType") != null) {
			listCompletaProfessionals = repositorio.getAllProfessional();

			req.setAttribute("lista_professionals", listCompletaProfessionals);
			return "paginaAgendamentos.jsp";
		}else {
			
			req.setAttribute("doLogin", "Faça o login para realizar um agendamento");
			return "login.jsp";
		}

	}

}
