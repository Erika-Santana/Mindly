<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="includes/fonts.jsp" %>
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="min-h-screen bg-white text-gray-900">

	<%@ include file="includes/menu.jsp"%>

	<div class="flex items-center justify-center min-h-screen px-4">
		<form class="w-full max-w-md bg-white p-8 rounded-lg shadow-lg border border-gray-200" action="controller.do?action=login" method="post">
			<div class="text-center mb-6 p-6">
				<h1 class="text-2xl quicksand-font font-bold">Entre com o email e senha</h1>
			</div>

			<div class="mb-4">
				<label for="login" class="block text-sm font-medium mb-1 text-gray-700">Login</label>
				<input type="email" id="login" name="login" placeholder="name@email.com" required
					class="w-full p-2.5 border border-gray-300 rounded-lg text-sm text-gray-900 
					focus:ring-blue-500 focus:border-blue-500 bg-gray-50" />
			</div>

			<div class="mb-4">
				<label for="password" class="block text-sm font-medium mb-1 text-gray-700">Senha</label>
				<input type="password" id="password" name="password" required
					class="w-full p-2.5 border border-gray-300 rounded-lg text-sm text-gray-900 focus:ring-blue-500 
					focus:border-blue-500 bg-gray-50" />
			</div>

			<button type="submit"
				class="w-full text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:outline-none focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5">
				Entrar
			</button>
		</form>
	</div>

	<%@ include file="includes/footer.jsp"%>

</body>
</html>
