<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="includes/fonts.jsp"%>
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
</head>
<body class="min-h-screen bg-white text-gray-900">

	<%@ include file="includes/menu_patient.jsp"%>

	<div class="flex items-center justify-center min-h-screen px-4">
		<form
			class="w-full max-w-md bg-white p-8 rounded-lg shadow-lg border border-gray-200"
			action="controller.do?action=login" method="post">
			<div class="text-left mb-6">
				<h1 class="text-2xl quicksand-font font-bold">Fazer Login</h1>
				<br>
				<h5 class="quicksand-font" style="font-size: 12px">Informe os
					dados de login para ter acesso à plataforma.</h5>
			</div>
			  <%  Boolean usuarioNaoLogado = (Boolean) request.getAttribute("error_login");
		    if (usuarioNaoLogado != null ) {%>
		   			 <div class="flex items-center p-4 mb-4 mt-4 text-sm text-red-800 rounded-lg bg-red-50" role="alert">
					  <svg class="shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
					    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
					  </svg>
					  <span class="sr-only">Info</span>
					  <div>
					    <span class="font-medium">Erro!</span> Email e senha estão incorretos ou o cadastro não foi realizado.
					    </div>
					</div>	
			    <% }%>
			    
			    <% var messageLogin = request.getAttribute("doLogin");
				    if(messageLogin != null){%>
				    <div class="flex items-center p-4 mb-4 text-sm text-blue-800 rounded-lg bg-blue-50" role="alert">
					  <svg class="shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
					    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
					  </svg>
					  <span class="sr-only">Info</span>
					  <div>
					    <span class="font-medium">Info!</span> Por favor faça o login para realizar um agendamento.
					  </div>
					</div>
				<% }%>
			<div class="mb-4">
				<label for="login"
					class="block text-sm font-medium mb-1 text-gray-700">Login</label>
				<input type="email" id="login" name="login"
					placeholder="name@email.com" required
					class="w-full p-2.5 border border-gray-300 rounded-lg text-sm text-gray-900 
					focus:ring-blue-500 focus:border-blue-500 bg-gray-50" />
			</div>

			<div class="mb-4">
				<label for="password"
					class="block text-sm font-medium mb-1 text-gray-700">Senha</label>
				<input type="password" id="password" name="password" required
					class="w-full p-2.5 border border-gray-300 rounded-lg text-sm text-gray-900 focus:ring-blue-500 
					focus:border-blue-500 bg-gray-50" />
			</div>
			
			<button type="submit"
				class="w-full text-white mt-4 bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5">
				Entrar</button>
		</form>
	</div>

	<%@ include file="includes/footer.jsp"%>

</body>
</html>
