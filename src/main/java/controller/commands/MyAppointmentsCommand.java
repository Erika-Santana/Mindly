package controller.commands;

import java.util.ArrayList;
import java.util.List;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AppointmentDatabaseFactory;
import model.dao.DatabaseAppointmentImp;
import model.dao.DatabaseAppointmentsDAO;
import model.dao.DatabaseProfessionalDAO;
import model.dao.DatabaseUserDAO;
import model.dao.UserDatabaseFactory;
import model.entities.Appointments;
import model.entities.Client;
import model.entities.Professional;

public class MyAppointmentsCommand implements Command{
	
	private static DatabaseAppointmentsDAO repositorio = AppointmentDatabaseFactory.factory(DatabaseType.MYSQL);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		List<Appointments> listAppointments = new ArrayList<>();
					
		var session = req.getSession(false);
		if (session != null && session.getAttribute("doLogin") != null) {
			Professional profissional = (Professional) session.getAttribute("professional");
			listAppointments = repositorio.getAllAppointmentsByIDProfessional(profissional.getID());
			
			req.setAttribute("lista_appointments", listAppointments);
			return "paginaAgendamento.jsp";
		}else {
			req.setAttribute("doLogin", "Faça o login para realizar um agendamento");
			return "login.jsp";
		}

	}

}
