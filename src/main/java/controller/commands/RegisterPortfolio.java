package controller.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterPortfolio implements Command {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Part fotoPart = req.getPart("imagem");


		String contentTypeFoto = fotoPart.getContentType();
	

		List<String> tiposPermitidos = List.of("image/jpeg", "image/png", "image/jpg");

		if (!tiposPermitidos.contains(contentTypeFoto) || !tiposPermitidos.contains(contentTypeWork)) {
		    req.setAttribute("error_message", "A imagem que está tentando inserir é inválida!");
		    throw new ServletException("Imagem inválida!");
		}

	
		String fileNameFoto = UUID.randomUUID().toString() + "_" + 
		    Paths.get(fotoPart.getSubmittedFileName()).getFileName().toString();

		String fileNameWork = UUID.randomUUID().toString() + "_" + 
		    Paths.get(workPart.getSubmittedFileName()).getFileName().toString();

		String fileUpload = "C:\\uploads";
		Files.createDirectories(Paths.get(fileUpload));

		fotoPart.write(fileUpload + File.separator + fileNameFoto);
		workPart.write(fileUpload + File.separator + fileNameWork);

		return null;
	}

}
