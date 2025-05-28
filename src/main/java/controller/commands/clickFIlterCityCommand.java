package controller.commands;

import java.util.List;

import enums.DatabaseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.DatabaseProfessionalDAO;
import model.dao.DatabaseProfessionalImp;
import model.dao.ProfessionalDatabaseFactory;
import model.entities.Professional;

public class clickFIlterCityCommand implements Command{
	
	private static DatabaseProfessionalDAO repositorio = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		  
    	var session = req.getSession(false);
		
		  if (session == null || (session.getAttribute("cliente") == null && session.getAttribute("professional") == null)) {
		        req.setAttribute("doLogin", "Faça o login para realizar um agendamento");
		        return "login.jsp";
		    }		
		    
		    try {
		        String city = req.getParameter("search-dropdown");
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
		
		        List<Professional> professionals = repositorio.listProfessionalByCity(city, page, pageSize);
		        int totalProfessionals = repositorio.countProfessionalsByCity(city); 
		        
		        System.out.println("Profissionais encontrados: " + professionals.size());
		        	
		        req.setAttribute("lista_professionals", professionals);
		        
		        if (session.getAttribute("cliente") != null) {
		            req.setAttribute("professionals", professionals);
		            req.setAttribute("totalProfessionals", totalProfessionals);
		            req.setAttribute("page", page);
		            req.setAttribute("pageSize", pageSize);
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
