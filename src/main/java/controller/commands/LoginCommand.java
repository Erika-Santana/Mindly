package controller.commands;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.DatabaseUserDAO;
import model.dao.UserDatabaseFactory;
import model.entities.Client;

public class LoginCommand implements Command{
	private static DatabaseUserDAO repositorio = UserDatabaseFactory.factory(DatabaseType.MYSQL);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		var login = req.getParameter("login").toString();
		var password = req.getParameter("password").toString();
		
		boolean resultado = repositorio.authenticateUser(login, password);
		
		  if (resultado != false) {
		        HttpSession session = req.getSession();
		        Client cliente = repositorio.getClient(login);
		        session.setAttribute("cliente", cliente);
		      
		        return "home.jsp";
		    } else {
		        req.setAttribute("error_login", true);
		        return "login.jsp";
		    }
		
	}

}
