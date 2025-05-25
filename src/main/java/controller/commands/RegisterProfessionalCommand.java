package controller.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import enums.DatabaseType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.dao.DatabaseProfessionalDAO;
import model.dao.DatabaseUserDAO;
import model.dao.ProfessionalDatabaseFactory;
import model.dao.UserDatabaseFactory;
import model.entities.AddressI;
import model.entities.Client;
import model.entities.Professional;

public class RegisterProfessionalCommand implements Command{
	private static DatabaseProfessionalDAO repositorio = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");

	
		var login = req.getParameter("loginProfessional");
		var senha = req.getParameter("passwordProfessional");
		var confirmarSenha = req.getParameter("passwordConfirmProfessional");
		var nomeCompleto = req.getParameter("nome-completo");
		var nomeFantasia = req.getParameter("nome-fantasia");
		var area = req.getParameter("area");
		var metodologia = req.getParameter("metodologia");
		var diasTrabalho = req.getParameterValues("dias-trabalho");
		var inicio = req.getParameter("start-time");
		var fim = req.getParameter("end-time");
		var sobre = req.getParameter("message");
		var cnpj = req.getParameter("cnpj");
		var contato = req.getParameter("phone-input");
		var rua = req.getParameter("rua");
		var cidade = req.getParameter("cidade");
		var numero_casa = Integer.parseInt(req.getParameter("numero-casa"));
		var estado = req.getParameter("estado");
		var pais = req.getParameter("pais");	
		Part workPart;
		Part fotoPart;
		
		try {
			fotoPart = req.getPart("imagem-profile");
			workPart = req.getPart("local-work");
			String contentType = fotoPart.getContentType();
			String contentTypeWork = fotoPart.getContentType();
			
			if(!tiposPermitidos.contains(contentType)&& !tiposPermitidos.contains(contentTypeWork)) {
				req.setAttribute("error_message", "A imagem que está tentando inserir é inválida!");
				throw new ServletException("Imagem inválida!");
			}
			
			String fileName = UUID.randomUUID().toString() + "_" + 
						Paths.get(fotoPart.getSubmittedFileName()).getFileName().toString();
			String fileUpload = "C:\\uploads";
			Files.createDirectories(Paths.get(fileUpload));
			fotoPart.write(fileUpload + File.separator + fileName);
			
			AddressI address = new AddressI(rua, cidade, estado, numero_casa, pais);
			Professional professional = null;
				
			if(repositorio.doesProfessionalExists(professional.getCNPJ())){
				req.setAttribute("error_message", "O CNPJ digitado já existe no nosso banco de dados.");
			}else if(repositorio.doesProfessionalLoginExists(professional.getLogin())) {
				req.setAttribute("error_message", "Email já está cadastrado. Utilize outro email.");
			}else {
				req.setAttribute("message", "Cadastro realizado com sucesso!");
				repositorio.registerProfessional(professional)
				repositorio.registerProfessional(client);
			}
			

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		

		return "signin_patient.jsp";
		
	}

}
