package controller.commands;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DatabaseProfessionalImp;
import model.dao.connection.DatabaseConnection;
import model.entities.Professional;

public class ClickInAppointments implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        var session = req.getSession(false);
        if (session != null && (session.getAttribute("cliente") != null || session.getAttribute("professional") != null)) {
            try {
                var connection = DatabaseConnection.getConnection();
                DatabaseProfessionalImp dao = new DatabaseProfessionalImp();
                List<Professional> professionals = dao.listProfessionals();
                req.setAttribute("professionals", professionals);
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "paginaAgendamentos.jsp";
        } else {
            req.setAttribute("doLogin", "Faça o login para realizar um agendamento");
            return "login.jsp";
        }
    }
}
