<%@page import="model.entities.Client"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Professional" %>
<%@ page import="model.entities.Specialty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<title>Agendamentos</title>
</head>
<body class="min-h-screen">
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="../path/to/flowbite/dist/flowbite.min.js"></script>
	<%@ include file="includes/menu_patient.jsp"%>


	<div class="flex items-center justify-center w-full">
		<div class="grid grid-cols-12 gap-2  ">
		<% var sessionExists = request.getSession(false);
			Client cliente = (Client) session.getAttribute("client");
			if(cliente != null){%>
			<div class="p-4 col-start-3 col-span-2 mt-8 text-center">
				<div
					class="max-w-sm bg-white border border-gray-200 rounded-lg shadow-sm">
					<a href="#"> <img class="rounded-t-lg w-full h-48 object-cover"
						src="images/Profissional1.jpg" alt="" />
					</a>
					<div class="p-5">
						<a href="#">
							<h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900"><%= cliente.getClient_name() %></h5>
						</a>
					</div>
				</div>
			</div>
			<div class="col-span-6 text-center ">
				<h2 class="quicksand-font font-bold p-4 m-4" style="font-size: 20px">Profissionais
					especializados</h2>
				<div class=" flex items-center text-center space-x-4">
					<img src="images/cerebro_icone.png"
					class="w-8 h-8 object-cover circle ">
				</div>
				<c:forEach var="profissional" items="${professionals}">
            <a href="professionalDetails.jsp?id=${profissional.}"
               class="flex flex-col mb-4 w-full items-center bg-white border border-gray-200 rounded-lg shadow-sm md:flex-row hover:bg-gray-100">
                <img class="w-full h-32 object-cover rounded-t-lg md:h-auto md:w-32 md:rounded-none md:rounded-s-lg"
                     src="${profissional.profileImage}" alt="${profissional.professionalName}">
                <div class="flex flex-col justify-between p-4 leading-normal">
                    <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900">${profissional.professionalName}</h5>
                    <p class="mb-3 font-normal text-gray-700">${profissional.description}</p>
                    <p class="mb-3 font-normal text-gray-700">${profissional.fantasyName}</p>
                    <div class="mb-3">
                        <h6 class="text-lg font-semibold text-gray-800">Especialidades:</h6>
                        <ul class="list-disc list-inside font-normal text-gray-700">
                            <c:forEach var="specialty" items="${profissional.specialties}">
                                <li>${specialty}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="mb-3">
                        <h6 class="text-lg font-semibold text-gray-800">Abordagens:</h6>
                        <ul class="list-disc list-inside font-normal text-gray-700">
                            <c:forEach var="approach" items="${profissional.approaches}">
                                <li>${approach}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </a>
        </c:forEach>

			</div>
			<div class="flex flex-col items-center pb-10"></div>
		</div>

	</div>
		<%@ include file="includes/footer.jsp"%>
	<%} %>

</body>
</html>