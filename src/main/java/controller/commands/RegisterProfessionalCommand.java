package controller.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import enums.DatabaseType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.dao.DatabaseProfessionalDAO;
import model.dao.ProfessionalDatabaseFactory;
import model.entities.AddressI;
import model.entities.Images;
import model.entities.Professional;
import model.entities.Specialty;
import model.entities.WorkHourProfessional;

public class RegisterProfessionalCommand implements Command {
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
		var professionalMessage = req.getParameter("professionalMessage");
		var cnpj = req.getParameter("cnpj");
		var duration = req.getParameter("duracao");
		var phone_input = req.getParameter("phone-input");
		var rua = req.getParameter("rua");
		var cidade = req.getParameter("cidade");
		var estado = req.getParameter("estado");
		var pais = req.getParameter("pais");

		Part fotoPart;

		try {
			fotoPart = req.getPart("imagem-profile");
			String contentType = fotoPart.getContentType();

			if (!tiposPermitidos.contains(contentType)) {
				req.setAttribute("error_message", "A imagem que está tentando inserir é inválida!");
				throw new ServletException("Imagem inválida!");
			}

			Collection<Part> parts = req.getParts();
			String fileName = UUID.randomUUID().toString() + "_"
					+ Paths.get(fotoPart.getSubmittedFileName()).getFileName().toString();
			String fileUpload = "C:\\uploads";
			Files.createDirectories(Paths.get(fileUpload));
			fotoPart.write(fileUpload + File.separator + fileName);
			if (repositorio.doesProfessionalExists(cnpj)) {
				req.setAttribute("error_message", "O CNPJ digitado já existe no nosso banco de dados.");
			} else if (repositorio.doesProfessionalLoginExists(login)) {
				req.setAttribute("error_message", "Email já está cadastrado. Utilize outro email.");
			} else {
				
				List<Images> listImages = null;

				AddressI address = new AddressI(rua, cidade, estado, 12, pais);
				address = repositorio.registerAddress(address);
				if (address != null) {
					Professional professional = new Professional(nomeCompleto, nomeFantasia, address, descricao,
							professionalMessage, cnpj, senha, login, phone_input, fileName);

					professional.setProfileImage(fileName);
					int ID_professional = repositorio.registerProfessional(professional);
					professional.setID(ID_professional);
					int approuch = repositorio.registerApprouch(metodologia);
					int area_ = repositorio.registerArea(area);

					Specialty specialty = new Specialty(professional, approuch, area_);

					if (repositorio.registerSpecialty(specialty) != false) {
						WorkHourProfessional horario = new WorkHourProfessional(inicio, fim, duration, diasTrabalho,
								specialty);
							if(repositorio.saveImages(professional, parts) && repositorio.registerWorkHour(horario) != false){
								listImages = repositorio.getImagesByProfessional(professional);
							req.setAttribute("message", "Cadastro realizado com sucesso!");
						
						}else {
							req.setAttribute("error_message", "Erro ao realizar cadastro. Por favor tente novamente");
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
