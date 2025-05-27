package controller.commands;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.DatabaseProfessionalImp;
import model.entities.Professional;

public class ClickInAppointments implements Command {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
	    HttpSession session = req.getSession(false);
	    
	    if (session == null || (session.getAttribute("cliente") == null && session.getAttribute("professional") == null)) {
	        req.setAttribute("doLogin", "Faça o login para realizar um agendamento");
	        return "login.jsp";
	    }
	    
	    try {
	        DatabaseProfessionalImp dao = new DatabaseProfessionalImp();
	        
	        String specialty = req.getParameter("specialty");
	        String city = req.getParameter("city");
	        int page = 1; 
	        int pageSize = 5;
	        try {
	            String pageParam = req.getParameter("page");
	            if (pageParam != null) {
	                page = Integer.parseInt(pageParam);
	                if (page < 1) page = 1;
	            }
	        } catch (NumberFormatException e) {
	            page = 1;
	        }
	
	        List<Professional> professionals = dao.listProfessionals(specialty, city, page, pageSize);
	        int totalProfessionals = dao.countProfessionals(specialty, city); 
	        
	        System.out.println("Profissionais encontrados: " + professionals.size());
	        
	        if (session.getAttribute("cliente") != null) {
	            req.setAttribute("professionals", professionals);
	            req.setAttribute("totalProfessionals", totalProfessionals);
	            req.setAttribute("page", page);
	            req.setAttribute("pageSize", pageSize);
	            req.setAttribute("specialty", specialty);
	            req.setAttribute("city", city);
	        } else {
	            Professional currentPro = (Professional) session.getAttribute("professional");
	            req.setAttribute("message", "Você está logado como profissional");
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("error", "Erro ao carregar profissionais");
	    }
	    
	    return "paginaAgendamentos.jsp";
	}
}
