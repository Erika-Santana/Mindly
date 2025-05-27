package controller.commands;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.DatabaseProfessionalImp;
import model.dao.DatabaseUserDAO;
import model.dao.UserDatabaseFactory;
import model.entities.Client;
import model.entities.Professional;

public class LoginCommand implements Command {
    private static DatabaseUserDAO repositorioClient = UserDatabaseFactory.factory(DatabaseType.MYSQL);
    private static DatabaseProfessionalImp repositorioProfessional = new DatabaseProfessionalImp();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        var login = req.getParameter("login").toString();
        var password = req.getParameter("password").toString();

        boolean isClient = repositorioClient.authenticateUser(login, password);
        boolean isProfessional = repositorioProfessional.authenticateUser(login, password);

        if (isClient) {
            HttpSession session = req.getSession();
            Client cliente = repositorioClient.getClient(login);
            session.setAttribute("cliente", cliente);
            session.setAttribute("userType", "client");
            return "home.jsp";
        } else if (isProfessional) {
            HttpSession session = req.getSession();
            Professional professional = repositorioProfessional.getProfessionalByLogin(login);
            session.setAttribute("professional", professional);
            session.setAttribute("userType", "professional");
            return "home.jsp";
        } else {
            req.setAttribute("error_login", true);
            return "login.jsp";
        }
    }
}
