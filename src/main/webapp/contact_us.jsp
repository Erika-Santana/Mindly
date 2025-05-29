<%@page import="model.entities.Client"%>
<%@page import="model.entities.Professional"%>
<%@page import="model.entities.Appointments"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includes/fonts.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Entre em contato conosco!</title>
</head>
<body class="min-h-screen bg-white text-gray-900">
	<%
		var sessionExists = request.getSession(false);
		Client cliente = (sessionExists != null) ? (Client) sessionExists.getAttribute("cliente") : null;
		Professional professional = (sessionExists != null) ? (Professional) sessionExists.getAttribute("professional") : null;
		if (cliente != null || (cliente == null && professional == null)) {
	%>
	<%@ include file="includes/menu_patient.jsp"%>
	<%
	} else if (professional != null) {
	%>
	<%@ include file="includes/menu_professional.jsp"%>
	<%
	}
	%>
	
	<div class="flex items-center justify-center min-h-screen m-8 px-4">
		<form class="w-full max-w-md bg-white p-8 m-8 rounded-lg shadow-lg border border-gray-200">
		<div class="text-left mb-6">
				<h1 class="text-2xl quicksand-font font-bold">Entre em contato conosco!</h1>
				<br>
				<h5 class="quicksand-font" style="font-size: 12px">Gostaria de enviar sugestões para a nossa equipe ou teve alguma dúvida? Deixe seu email e a sua mensagem nos campos abaixo :) </h5>
			</div>
      <div class="mb-6">
         <label for="email" class="block mb-2 text-sm font-medium text-gray-900 ">Email</label>
         <input type="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg
          focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" placeholder="name@company.com" required />
      </div>
      <div class="mb-6">
         <label for="subject" class="block mb-2 text-sm font-medium text-gray-900">Assunto</label>
         <input type="text" id="subject" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg
          focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
          " placeholder="Nos deixe saber como podemos ajuda-lo(a)." required />
      </div>
      <div class="mb-6">
         <label for="message" class="block mb-2 text-sm font-medium text-gray-900 ">Mensagem</label>
         <textarea id="message" rows="4" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 
         focus:ring-blue-500 focus:border-blue-500" placeholder="Sua mensagem..."></textarea>
      </div>
      <button type="submit" class="text-white bg-rose-700 hover:bg-rose-800 w-full focus:ring-4 focus:ring-blue-300 font-medium rounded-lg 
      text-sm px-5 py-2.5 mb-2  focus:outline-none  block">Enviar mensagem</button>
   </form>
	</div>

    <%@ include file="includes/footer.jsp"%>
	

</body>
</html>