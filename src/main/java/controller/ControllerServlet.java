package controller;

import java.io.IOException;

import controller.commands.RegisterPatientCommand;
import controller.commands.RegisterProfessionalCommand;
import controller.commands.ClickInLoginCommand;
import controller.commands.ClickSignInCommand;
import controller.commands.Command;
import controller.commands.LoginCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller.do")
@MultipartConfig(
		 fileSizeThreshold = 1024 * 1024 * 2,
		 maxFileSize = 1024 * 1024 * 2, 
		 maxRequestSize = 1024 * 1024 * 2 
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
			case "clickLogin": {
				command = new ClickInLoginCommand();
				break;
			}
			case "login": {
				command = new LoginCommand();
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
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
		
		
		
		page = command.execute(req, resp);
		var dispatcher = req.getRequestDispatcher(page);
		dispatcher.forward(req, resp);
		
	}
	
	

}
