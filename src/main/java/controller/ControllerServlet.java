package controller;

import java.io.IOException;



import controller.commands.RegisterPatientCommand;
import controller.commands.RegisterProfessionalCommand;
import controller.commands.ShowProfessionalsToAppointmentCommand;
import controller.commands.clickFIlterApprouchCommand;
import controller.commands.clickFIlterCityCommand;
import controller.commands.clickFIlterSpecialtyCommand;
import controller.commands.ClickAboutUsCommand;
import controller.commands.ClickContactUsCommand;
import controller.commands.ClickHome;
import controller.commands.ClickInAppointments;
import controller.commands.ClickInLoginCommand;
import controller.commands.ClickInProfessionalSignInCommand;
import controller.commands.ClickMyAppointmentsCommand;
import controller.commands.ClickPatientSignIn;
import controller.commands.ClickSignInCommand;
import controller.commands.ClickTeamProfessional;
import controller.commands.Command;
import controller.commands.LoginCommand;
import controller.commands.LoginProfessionalCommand;
import controller.commands.LogoutCommand;
import controller.commands.PortifolioCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller.do")
@MultipartConfig(
		 fileSizeThreshold = 1024 * 1024 * 5,
		 maxFileSize = 1024 * 1024 * 5, 
		 maxRequestSize = 1024 * 1024 * 5 
		)

public class ControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
		
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String action = (String)req.getParameter("action");
	
		Command command = null;
		String page;
		
		if (action == null) {
			System.out.print("Action nulo");

		}
		switch (action) {
			case "clickSignIn": {
				command = new ClickSignInCommand();
				break;
			}
			case "clickHome": {
				command = new ClickHome();
				break;
			}
			case "clickLogin": {
				command = new ClickInLoginCommand();
				break;
			}
			
			case "clickAppointment": {
				command = new ShowProfessionalsToAppointmentCommand();
				break;
			}
			case "clickProfessional": {
				command = new ClickInProfessionalSignInCommand();
				break;
			}
			case "clickMyAppointments": {
				command = new ClickMyAppointmentsCommand();
				break;
			}
			case "clickContactUs": {
				command = new ClickContactUsCommand();
				break;
				
			}case "clickPatient": {
				command = new ClickPatientSignIn();
				break;
			}case "clickAboutUs": {
				command = new ClickAboutUsCommand();
				break;
			}
			case "clickTeamProfessionals": {
				command = new ClickTeamProfessional();
				break;
			}
			case "login": {
				command = new LoginCommand();
				break;
			}
			case "loginProfessional": {
				command = new LoginProfessionalCommand();
				break;
			}
			case "register_paciente": {
				command = new RegisterPatientCommand();
				break;
			}
			case "sign-in-professional": {
				command = new RegisterProfessionalCommand();
				break;
			}
			case "logout": {
				command = new LogoutCommand();
				break;
			}
			case "viewProfessional": {
				req.setAttribute("id", command);
				command = new PortifolioCommand();
				break;
			}
			case "clickPortifolio": {
				command = new PortifolioCommand();
				break;
			}
			case "clickFilter": {
				String filtro = req.getParameter("filtro");
				if (filtro.equals("cidade")) {
					command = new clickFIlterCityCommand();
				}else if(filtro.equals("abordagem")) {
					command = new clickFIlterApprouchCommand();
				}else if(filtro.equals("area")) {
					command = new clickFIlterSpecialtyCommand();
					System.out.print("Entrou command Specialty");
				}else {
					command = new clickFIlterCityCommand();
				}

				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
		
		page = command.execute(req, resp);
		var dispatcher = req.getRequestDispatcher(page);
		dispatcher.forward(req, resp);
		
	}
	
	

}
