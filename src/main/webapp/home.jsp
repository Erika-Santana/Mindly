<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.entities.Client"%>
<%@page import="model.entities.Professional"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="includes/fonts.jsp" %>
<title>Página inicial</title>
</head>
<body>

	<% var sessionExists = request.getSession(false);
	    Client cliente = (sessionExists != null) ? (Client) sessionExists.getAttribute("cliente") : null;
	    Professional professional = (sessionExists != null) ? (Professional) sessionExists.getAttribute("professional") : null;
    if (cliente != null || (cliente == null && professional == null)) { %>
	<%@ include file="includes/menu_patient.jsp"%>
	<% }else if( professional != null){%>
	<%@ include file="includes/menu_professional.jsp"%>
	<% } %>
	
	<div class="flex justify-center min-h-screen">
		<div class="flex items-center justify-center space-x-16 w-full h-max p-8 m-4 ">
			<div>
				<img alt="Teste" src="imagem/Cerebro_Home.png"
					class="w-full h-96 object-cover rounded opacity-0 transition-opacity duration-2000 ease-in-out"
					onload="this.classList.remove('opacity-0')">
			</div>
			<div class="text-start w-1/3">
				<div class=" pl-8 flex items-center space-x-4">

					<img src="imagem/cerebro_icone.png" class="w-8 h-8 object-cover circle">
					<h2 class="quicksand-font font-black p-4 m-4 ">Mindly</h2>

				</div>

				<p class="font-bold text-purple-700 mb-4 sacramento-regular text-center" style="font-size: 32px">Cuidar
					da mente é um ato de coragem e amor próprio</p>
				<p class="text-sm quicksand-font tracking-wider">
					Nosso espaço foi criado para tornar esse caminho mais leve: aqui,
					você pode agendar sua consulta com praticidade, <strong>segurança</strong> e o
					<strong>acolhimento</strong> que merece.<br> <br> Conecte-se com
					profissionais dedicados ao seu bem-estar e encontre apoio para os
					desafios do dia a dia
				</p>
				
				<div>
					<% if(cliente != null){ %>
					
					<form action="controller.do?action=clickAppointment" method="post">
						<div class="mt-8 flex justify-center">
							<button type="button"
								class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300
								 font-medium rounded-lg mr-8 text-sm px-5 py-2.5 mb-2 dark:bg-purple-600 dark:hover:bg-purple-700
								  dark:focus:ring-purple-900">Agende
								com nossos profissionais!</button>
						</div>
					</form>
					<%} %>
						
				</div>

			</div>
			
		</div>
	</div>

	<div class="mt-4 mb-8">
		<div>
			<h1 class="text-center font-bold quicksand-font text-purple-700 p-8" style="font-size:24px">Perguntas Frequentes</h1>
		</div>
		
			<div
			class="faq-item group w-full max-w-4xl mx-auto mb-1 overflow-hidden rounded-xl bg-white shadow-sm hover:shadow-md transition-all duration-300 border border-gray-200">
			<details class="relative group">
				<summary
					class="list-none cursor-pointer p-4 pr-12 flex justify-between items-center">
					<h3	class="quicksand-font sm:text-xl font-semibold text-gray-800 group-hover:text-purple-600 transition-colors">
						 Como funciona o serviço?</h3>
					<div class="ml-4 transform transition-transform duration-300 group-open:rotate-180">
						<svg class="w-6 h-6 text-gray-500 group-hover:text-purple-600"
							fill="none" viewBox="0 0 24 24" stroke="currentColor">
       				   <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" d="M19 9l-7 7-7-7" />
        			</svg>
					</div>
				</summary>
				<div class="faq-content px-6 pb-4 pt-2 text-gray-600">
					<p class="text-base quicksand-font  leading-relaxed">É um site que conecta pacientes a
					 profissionais especializados em saúde cerebral, como neurologistas, neuropsicólogos, psiquiatras e
					  terapeutas cognitivos, permitindo o agendamento de consultas online ou presenciais de forma rápida e segura.</p>
  				</div>
  				</details>
			</div>
			<div
			class="faq-item group w-full max-w-4xl mx-auto mb-1 overflow-hidden rounded-xl bg-white shadow-sm hover:shadow-md transition-all duration-300 border border-gray-200">
			<details class="relative group">
				<summary
					class="list-none cursor-pointer p-4 pr-12 flex justify-between items-center">
					<h3	class="quicksand-font sm:text-xl font-semibold text-gray-800 group-hover:text-purple-600 transition-colors">
						 Que tipos de profissionais posso encontrar?</h3>
					<div class="ml-4 transform transition-transform duration-300 group-open:rotate-180">
						<svg class="w-6 h-6 text-gray-500 group-hover:text-purple-600"
							fill="none" viewBox="0 0 24 24" stroke="currentColor">
       				   <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" d="M19 9l-7 7-7-7" />
        			</svg>
					</div>
				</summary>
				<div class="faq-content px-6 pb-4 pt-2 text-gray-600">
					<p class="text-base quicksand-font leading-relaxed">Neurologistas<br>
					Neuropsicólogos<br>
					Psiquiatras <br>
					Psicólogos <br>
					Terapeutas ocupacionais</p>
  				</div>
  				</details>
			</div>
			
			<div
			class="faq-item group w-full max-w-4xl mx-auto mb-1 overflow-hidden rounded-xl bg-white shadow-sm hover:shadow-md transition-all duration-300 border border-gray-200">
			<details class="relative group">
				<summary
					class="list-none cursor-pointer p-4 pr-12 flex justify-between items-center">
					<h3	class="quicksand-font sm:text-xl font-semibold text-gray-800 group-hover:text-purple-600 transition-colors">
						Como faço para agendar uma consulta?</h3>
					<div class="ml-4 transform transition-transform duration-300 group-open:rotate-180">
						<svg class="w-6 h-6 text-gray-500 group-hover:text-purple-600"
							fill="none" viewBox="0 0 24 24" stroke="currentColor">
       				   <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" d="M19 9l-7 7-7-7" />
        			</svg>
					</div>
				</summary>
				<div class="faq-content px-6 pb-4 pt-1 text-gray-600">
					<p class="text-base quicksand-font leading-relaxed">Crie uma conta gratuita<br>
					Escolha o profissional desejável<br>
					Selecione o dia e horário disponíveis<br>
					Aguarde a confirmação do profissional e orientações</p>
  				</div>
  				</details>
			</div>
			<div
			class="faq-item group w-full max-w-4xl mx-auto mb-1 overflow-hidden rounded-xl bg-white shadow-sm hover:shadow-md transition-all duration-300 border border-gray-200">
			<details class="relative group">
				<summary
					class="list-none cursor-pointer p-4 pr-12 flex justify-between items-center">
					<h3	class="quicksand-font sm:text-xl font-semibold text-gray-800 group-hover:text-purple-600 transition-colors">
						É gratuito agendar pela plataforma?</h3>
					<div class="ml-4 transform transition-transform duration-300 group-open:rotate-180">
						<svg class="w-6 h-6 text-gray-500 group-hover:text-purple-600"
							fill="none" viewBox="0 0 24 24" stroke="currentColor">
       				   <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" d="M19 9l-7 7-7-7" />
        			</svg>
					</div>
				</summary>
				<div class="faq-content px-6 pb-4 pt-1 text-gray-600">
					<p class="text-base quicksand-font leading-relaxed">Sim. O uso do site é 
					gratuito para pacientes. Você só paga pela consulta ao profissional, conforme
					 os valores definidos por ele.</p>
  				</div>
  				</details>
			</div>
			<div
			class="faq-item group w-full max-w-4xl mx-auto mb-1 overflow-hidden rounded-xl bg-white shadow-sm hover:shadow-md transition-all duration-300 border border-gray-200">
			<details class="relative group">
				<summary
					class="list-none cursor-pointer p-4 pr-12 flex justify-between items-center">
					<h3	class="quicksand-font sm:text-xl font-semibold text-gray-800 group-hover:text-purple-600 transition-colors">
						Gostaria de ser um colaborador. Como proceder?</h3>
					<div class="ml-4 transform transition-transform duration-300 group-open:rotate-180">
						<svg class="w-6 h-6 text-gray-500 group-hover:text-purple-600"
							fill="none" viewBox="0 0 24 24" stroke="currentColor">
       				   <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" d="M19 9l-7 7-7-7" />
        			</svg>
					</div>
				</summary>
				<div class="faq-content px-6 pb-4 pt-1 text-gray-600">
					<p class="text-base quicksand-font leading-relaxed">Basta clicar em “Quero Me Cadastrar como Profissional” 
					no site, preencher o formulário e,
					 após validação, seu perfil estará ativo.</p>
  				</div>
  				</details>
			</div>
			<div
			class="faq-item group w-full max-w-4xl mx-auto mb-1 overflow-hidden rounded-xl bg-white shadow-sm hover:shadow-md transition-all duration-300 border border-gray-200">
			<details class="relative group">
				<summary
					class="list-none cursor-pointer p-4 pr-12 flex justify-between items-center">
					<h3	class="quicksand-font sm:text-xl font-semibold text-gray-800 group-hover:text-purple-600 transition-colors">
						O que oferecemos aos profissionais?</h3>
					<div class="ml-4 transform transition-transform duration-300 group-open:rotate-180">
						<svg class="w-6 h-6 text-gray-500 group-hover:text-purple-600"
							fill="none" viewBox="0 0 24 24" stroke="currentColor">
       				   <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" d="M19 9l-7 7-7-7" />
        			</svg>
					</div>
				</summary>
				<div class="faq-content px-6 pb-4 pt-1 text-gray-600">
					<p class="text-base quicksand-font leading-relaxed">
					Agenda digital com gerenciamento completo<br>
					Recebimento automático de agendamentos<br>
					Perfil profissional público e personalizável<br>
					Lembretes automáticos por e-mail para pacientes<br>
					Visibilidade para novos pacientes interessados em nosso mural dos profissionais</p>
  				</div>
  				</details>
			</div>
		</div>

	<%@ include file="includes/footer.jsp"%>

</body>
</html>