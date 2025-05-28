<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.entities.Client"%>
<%@page import="model.entities.Professional"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/fonts.jsp"%>
<meta charset="UTF-8">
<title>Sobre nós</title>
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

		<div
			class="flex items-center justify-center bg-teal-100 space-x-16 w-full p-16 h-max ">
			<div class="text-left w-1/3">
				<div class=" pl-8 flex items-center space-x-4">

					<img src="imagem/cerebro_icone.png"
						class="w-8 h-8 object-cover circle">
					<h2 class="quicksand-font font-black p-4 m-4"
						style="font-size: 24px">Sobre nós</h2>

				</div>

				<p class="text-sm quicksand-font mb-4 tracking-wider">
					No nosso espaço, acreditamos que cuidar da saúde mental deve ser <strong>simples,
						acessível e humano</strong>. <br>Criamos esta plataforma com um único
					propósito: <strong>conectar</strong> pessoas que buscam <strong>apoio
						emocional</strong>, <strong>psicológico e terapêutico</strong> com <strong>profissionais
						especializados</strong> e comprometidos com o bem-estar. Aqui, reunimos
					psicólogos, terapeutas e outros profissionais da saúde mental em um
					só lugar — facilitando a busca por atendimento qualificado e
					respeitoso.
				</p>
				<p
					class="font-bold text-purple-700 mb-4 sacramento-regular text-center"
					style="font-size: 32px">Seja você alguém em busca de apoio aqui
					é o seu lugar. 💜</p>

				<form action="controller.do?action=clickAppointment" method="post">
					<div class="mt-8 flex justify-center">
						<button type="button"
							class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300
						 font-medium rounded-lg mr-8 text-sm px-5 py-2.5 mb-2 dark:bg-purple-600 dark:hover:bg-purple-700
						  dark:focus:ring-purple-900">Agende
							com nossos profissionais!</button>
					</div>
				</form>

			</div>
			<div>
				<img alt="Teste" src="imagem/homem_about_us.png"
					class="w-full h-96 object-cover rounded opacity-0 transition-opacity duration-2000 ease-in-out"
					onload="this.classList.remove('opacity-0')">
			</div>
		</div>
	</div>

	<div
		class="flex items-center justify-center bg-pink-200 space-x-16 w-full p-16 h-max ">
		<div>
			<img alt="Teste" src="imagem/mulher_about_us.png"
				class="w-full h-96 object-cover rounded opacity-0 transition-opacity duration-2000 ease-in-out"
				onload="this.classList.remove('opacity-0')">
		</div>
		<div class="text-start w-1/3">
			<div class=" flex items-center space-x-4">

				<img src="imagem/cerebro_icone.png"
					class="w-8 h-8 object-cover circle ">
				<h2 class="quicksand-font font-black p-4 m-4" style="font-size: 20px">Profissionais
					especializados</h2>

			</div>

			<p class="text-sm quicksand-font tracking-wider">
				Na nossa plataforma, você encontra uma rede de <strong>profissionais
				qualificados</strong> e <strong>dedicados</strong> à saúde mental, cuidadosamente selecionados
				para oferecer acolhimento, escuta ativa e acompanhamento humanizado.<br>
				<br> Cada colaborador do nosso time é <strong>especialista</strong> em sua área
				de atuação, com <strong>formação</strong> e <strong>experiência</strong> para lidar com os mais
				diversos desafios emocionais e comportamentais. Contamos com
				<strong>psicólogos</strong>, <strong>terapeutas</strong>, <strong>psiquiatras</strong> e outros profissionais
				da saúde mental que atuam com ética , empatia e respeito à
				individualidade de cada pessoa.

			</p>
		</div>
	</div>
	<div
		class="flex items-center justify-center pb-8 bg-pink-200 space-x-16 w-full h-max ">
		<div class="text-start w-1/3">

			<p class="text-sm quicksand-font tracking-wider">Nosso
				compromisso é <strong>garantir</strong> que você se sinta <strong>seguro(a) e bem acolhido(a)</strong>
				desde o primeiro contato. Por isso, <strong>oferecemos perfis detalhados</strong>
				para que você conheça a <strong>abordagem de cada profissional</strong>, suas
				<strong>especialidades</strong>, <strong>horários disponíveis e metodologias de trabalho</strong>.<br><br>

				Além de atenderem com excelência, nossos profissionais também
				<strong>escolhem estar aqui</strong> porque acreditam em uma nova forma de cuidar da
				saúde mental: <strong> mais próxima</strong>, <strong>mais acessível</strong> e <strong>mais conectada</strong> com a
				realidade de cada cliente. Cuidar de você é a nossa prioridade — e
				essa jornada começa com quem está ao seu lado.</p>
				
				<form action="controller.do?action=clickProfessionals" method="post">
					<div class="mt-8 flex justify-center">
						<button type="button"
							class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300
						 font-medium rounded-lg mr-8 text-sm px-5 py-2.5 mb-2 dark:bg-purple-600 dark:hover:bg-purple-700
						  dark:focus:ring-purple-900">Conheça nossos profissionais!</button>
					</div>
				</form>
		</div>
		<div>
			<img alt="Teste" src="imagem/maos_aboutus.png"
				class="w-full h-96 object-cover rounded opacity-0 transition-opacity duration-2000 ease-in-out"
				onload="this.classList.remove('opacity-0')">
		</div>
	</div>
	
	
	<%@ include file="includes/footer.jsp"%>

</body>
</html>