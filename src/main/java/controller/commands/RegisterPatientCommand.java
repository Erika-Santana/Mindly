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
import model.dao.DatabaseUserDAO;
import model.dao.UserDatabaseFactory;
import model.entities.AddressI;
import model.entities.Client;

public class RegisterPatientCommand implements Command {
	
	private static DatabaseUserDAO repositorio = UserDatabaseFactory.factory(DatabaseType.MYSQL);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");

		var nome = req.getParameter("nome");
		if (nome == null) {
			System.out.print("Não está recebendo os valores!");
		}
		var login = req.getParameter("login");
		var password = req.getParameter("password");
		var cpf  = req.getParameter("cpf");
		var rua = req.getParameter("rua");
		var cidade = req.getParameter("cidade");
		var phone_input = req.getParameter("phone-input");
		var numero_casa = Integer.parseInt(req.getParameter("numero-casa"));
		var estado = req.getParameter("estado");
		var pais = req.getParameter("pais");		
		Part fotoPart;
		
		try {
			fotoPart = req.getPart("imagem-profile");
			String contentType = fotoPart.getContentType();
			
			if(!tiposPermitidos.contains(contentType)) {
				req.setAttribute("error_message", "A imagem que está tentando inserir é inválida!");
				throw new ServletException("Imagem inválida!");
			}
			
			String fileName = UUID.randomUUID().toString() + "_" + 
						Paths.get(fotoPart.getSubmittedFileName()).getFileName().toString();
			String fileUpload = "C:\\uploads";
			Files.createDirectories(Paths.get(fileUpload));
			fotoPart.write(fileUpload + File.separator + fileName);
			
			AddressI address = new AddressI(rua, cidade, estado, numero_casa, pais);
			Client client = new Client(nome, cpf, address, phone_input, password, login, fileName);
			System.out.printf("CPF %s", client.getCPF());
			if(repositorio.doesUserExists(client.getCPF())){
				req.setAttribute("error_message", "O CPF digitado já existe no nosso banco de dados.");
			}else if(repositorio.doesLoginExists(client.getLogin())) {
				req.setAttribute("error_message", "Email já está cadastrado. Utilize outro email.");
			}else {
				
				repositorio.registerAddress(address);
				repositorio.registerUser(client);
				req.setAttribute("message", "Cadastro realizado com sucesso!");
			}

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		

		return "signin_patient.jsp";
	}

}
