<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.entities.Client"%>
<%@page import="model.entities.Professional"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meus Agendamentos</title>
</head>
<body>
<body class="min-h-screen bg-gradient-to-b from-blue-50 to-gray-100">
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="../path/to/flowbite/dist/flowbite.min.js"></script>
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

	<div class="flex items-center justify-center w-full py-8">
		<div class="container mx-auto px-4">
			<div class="grid grid-cols-12 gap-6">
				<%
				if (professional != null) {
				%>
				<div class="col-start-2 col-span-3 mt-8">
					<div
						class="bg-white border border-gray-200 rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
						<div class="relative">
							<img class="w-full h-56 object-cover"
								src="<%=request.getContextPath()%>/images/<%=professional.getProfileImage()%>"
								alt="Perfil" />
							<div
								class="absolute inset-0 bg-gradient-to-t from-black/30 to-transparent"></div>
						</div>
						<div class="p-6 text-center">
							<h5 class="text-xl font-semibold text-gray-800">
								<%=professional.getName()%>
							</h5>

						</div>
					</div>
				</div>
				<div class="col-span-7">
					<h2
						class="text-3xl font-bold text-gray-800 quicksand-font text-center p-4 mb-6">Clientes</h2>
					<div class="flex justify-center mb-6"></div>
					<c:forEach var="appointment" items="${appointments}">
						<div
							class="mb-8 bg-white border border-gray-200 rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
							<div class="flex flex-col md:flex-row">
								<div class="relative w-full md:w-64 h-64 md:h-auto">
									<img class="w-full h-full object-cover md:rounded-l-xl"
										src="${pageContext.request.contextPath}/images/${appointment.cliente.profile}"
										alt="${appointment.cliente.client_name}" />
									<div
										class="absolute inset-0 bg-gradient-to-t from-black/20 via-transparent to-transparent"></div>
								</div>
								<div class="p-6 flex-1 space-y-4">
									<div class="flex justify-between items-start">
										<div>
											<h5 class="text-2xl font-bold text-gray-800">${appointment.cliente.client_name}</h5>
							
										</div>
									</div>
									<div class="space-y-3">
										<div>
											<h6 class="text-base font-semibold text-gray-700 flex items-center">
											
												Contato
											</h6>
											<div class="flex flex-wrap gap-2 mt-2">
												<h3 class="text-2xl font-bold text-gray-800">${appointment.cliente.contact}:</h3>
											</div>
										</div>
									</div>

									<div class="flex justify-between items-center pt-4">
										<div class="flex items-center text-gray-500 text-sm">
										<h6 class="text-base font-semibold text-gray-700 flex items-center">
												Endereço</h6>
											<svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1"
												fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            
                      					  </svg>
											<h3 class="text-base text-gray-700 flex items-center">${appointments.cliente.address.city}<h3>
										</div>
										<a
											href="professionalDetails.jsp?id=${clientes.professional.ID}"
											class="inline-flex items-center bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg transition-colors duration-200 text-sm font-medium">
											Ver Perfil Completo <svg xmlns="http://www.w3.org/2000/svg"
												class="h-4 w-4 ml-1" fill="none" viewBox="0 0 24 24"
												stroke="currentColor">
                            <path stroke-linecap="round"
													stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                        </svg>
										</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>

					<c:if test="${empty professionals}">
						<div class="text-center py-12">
							<svg xmlns="http://www.w3.org/2000/svg"
								class="h-16 w-16 mx-auto text-gray-400" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                                <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
							<h3 class="mt-4 text-lg font-medium text-gray-900">Nenhum
								cliente encontrado</h3>
							<p class="mt-1 text-sm text-gray-500">Não encontramos
								profissionais com os critérios atuais.</p>
						</div>
					</c:if>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<%@ include file="includes/footer.jsp"%>
</body>

</body>
</html>