package controller.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

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
import model.entities.Specialty;
import model.entities.WorkHourProfessional;

public class RegisterProfessionalCommand implements Command{
	private static DatabaseProfessionalDAO repositorio = ProfessionalDatabaseFactory.factory(DatabaseType.MYSQL);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");

	
		var login = req.getParameter("loginProfessional");
		var senha = req.getParameter("passwordProfessional");
		var nomeCompleto = req.getParameter("nome-completo");
		var nomeFantasia = req.getParameter("nome-fantasia");
		var area = req.getParameter("area");
		var metodologia = req.getParameter("metodologia");
		var diasTrabalho = req.getParameterValues("dias-trabalho");
		var inicio = req.getParameter("start-time");
		var fim = req.getParameter("end-time");
		var descricao = req.getParameter("message");
		var cnpj = req.getParameter("cnpj");
		var duration = req.getParameter("duracao");
		var phone_input = req.getParameter("phone-input");
		System.out.print(phone_input);
		var rua = req.getParameter("rua");
		var cidade = req.getParameter("cidade");
		
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
			
			String fileNameWork = UUID.randomUUID().toString() + "_" + 
					Paths.get(workPart.getSubmittedFileName()).getFileName().toString();

			workPart.write(fileUpload + File.separator + fileNameWork);
		
			//Vai dar erro pois para regisrar o profissional é preciso já ter o address..
			
			//entretanto é necessário verificar antes x opções pra não registrar antes de verifica
			//File name work vai ser para as fotos
			//Pegar a datetime na hora da inserção no banco.
				
			if(repositorio.doesProfessionalExists(cnpj)){
				req.setAttribute("error_message", "O CNPJ digitado já existe no nosso banco de dados.");
			}else if(repositorio.doesProfessionalLoginExists(login)) {
				req.setAttribute("error_message", "Email já está cadastrado. Utilize outro email.");
			}else {
				
				
				AddressI address = new AddressI(rua, cidade, estado, 12, pais);
			    address = repositorio.registerAddress(address);
			    if (address != null) {
			    	Professional professional = new Professional(nomeCompleto, nomeFantasia, address,
							descricao, cnpj, senha, login, fileNameWork, phone_input, fileName);
					professional.setWorkImage(fileNameWork);
					professional.setProfileImage(fileName);
					int ID_professional = repositorio.registerProfessional(professional);
					professional.setID(ID_professional);
					int approuch = repositorio.registerApprouch(metodologia);
					int area_ = repositorio.registerArea(area);
					
					Specialty specialty = new Specialty(professional, approuch, area_);
					
					
					if(repositorio.registerSpecialty(specialty) != false) {
						WorkHourProfessional horario = new WorkHourProfessional(inicio, fim, duration, diasTrabalho, specialty);
						if(repositorio.registerWorkHour(horario) != false) {
							req.setAttribute("message", "Cadastro realizado com sucesso!");
						}else {
							req.setAttribute("error_message", "Erro ao realizar cadastro. Por favor tente novamente.");
						}
					}
					
				}
				
	
			}

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		

		return "signin_professional.jsp";
		
	}

}
