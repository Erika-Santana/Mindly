<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/fonts.jsp" %>
<meta charset="UTF-8">
<title>Entrar como:</title>
</head>
<body>

	<%@ include file="includes/menu_patient.jsp"%>
	<div class="flex items-center  text-center justify-center min-h-screen px-4">
		<div class="w-full max-w-md bg-white p-8 rounded-lg shadow-lg border border-gray-200">
			<div class="text-center w-full mb-6">
				<div class="flex text-center w-full items-center justify-content">
					<img src="imagem/cerebro_icone.png" alt="ícone do cérebro" class="h-8 w-8 mr-2" />
					<h1 class="text-2xl text-center quicksand-font font-bold">Cadastre-se como:</h1>
				</div>
				
				<br>
				<h5 class="quicksand-font" style="font-size: 12px">Selecione
					como deseja se cadastrar.</h5>
			</div>

			<div class=" flex items-center justify-center">
				<button type="button"
				  onclick="window.location.href='controller.do?action=clickProfessional'"
					class="text-white bg-gradient-to-r from-rose-400 
					to-pink-600 hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-purple-200 dark:focus:ring-purple-800 
					font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2 mr-12" href="controller.do?action=loginProfessional">
					 <img src="imagem/colaborador.png" alt="Ícone de calendário" class="w-24 h-24">
					Colaborador</button>

				<button type="button"
				  onclick="window.location.href='controller.do?action=clickPatient'"
					class="text-white bg-gradient-to-r from-rose-400 
					to-pink-600 hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-purple-200 dark:focus:ring-purple-800 
						font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2" href="controller.do?action=login">
						 <img src="imagem/paciente.png" alt="Ícone de calendário" class="w-24 h-24">
						
						Paciente</button>
			</div>

		</div>
	</div>



	<%@ include file="includes/footer.jsp"%>

</body>
</html>