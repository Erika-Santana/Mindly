<%@page import="model.entities.Client"%>
<%@page import="model.entities.Professional"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agendamentos</title>
</head>
<body class="min-h-screen bg-gradient-to-b from-blue-50 to-gray-100">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="../path/to/flowbite/dist/flowbite.min.js"></script>
    <%@ include file="includes/menu_patient.jsp"%>

    <div class="flex items-center justify-center w-full py-8">
        <div class="container mx-auto px-4">
            <div class="grid grid-cols-12 gap-6">
            <% var sessionExists = request.getSession(false);
               Client cliente = (sessionExists != null) ? (Client) sessionExists.getAttribute("cliente") : null;
               Professional professional = (sessionExists != null) ? (Professional) sessionExists.getAttribute("professional") : null;
               if (cliente != null || professional != null) { %>
                <div class="col-start-2 col-span-3 mt-8">
                    <div class="bg-white border border-gray-200 rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                        <div class="relative">
                            <img class="w-full h-56 object-cover" src="<%="C:\\uploads" + professional.getProfileImage()%>" alt="Perfil" />
                            <div class="absolute inset-0 bg-gradient-to-t from-black/30 to-transparent"></div>
                        </div>
                        <div class="p-6 text-center">
                            <h5 class="text-xl font-semibold text-gray-800">
                                <%= cliente != null ? cliente.getClient_name() : professional.getName() %>
                            </h5>
                            <p class="text-sm text-gray-500 mt-2">
                                <%= cliente != null ? "Cliente" : "Profissional" %>
                            </p>
                            <div class="mt-4">
                                <span class="inline-block bg-blue-100 text-blue-800 text-xs px-2 py-1 rounded-full">
                                    <%= cliente != null ? "Paciente" : professional.getSpecialty().get(0) %>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-span-7">
                    <h2 class="text-3xl font-bold text-gray-800 text-center p-4 mb-6">Profissionais Especializados</h2>
                    <div class="flex justify-center mb-6">
                
                    </div>
                    <c:forEach var="profissional" items="${professionals}">
                        <div class="mb-8 bg-white border border-gray-200 rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
                            <div class="flex flex-col md:flex-row">
                                <div class="relative w-full md:w-64 h-64 md:h-auto">
                                    <img class="w-full h-full object-cover md:rounded-l-xl" 
                                         src="${empty profissional.profileImage ? 'XX' : profissional.profileImage}" 
                                         alt="${profissional.name}" />
                                    <div class="absolute inset-0 bg-gradient-to-t from-black/20 via-transparent to-transparent"></div>
                                </div>
                                <div class="p-6 flex-1 space-y-4">
                                    <div class="flex justify-between items-start">
                                        <div>
                                            <h5 class="text-2xl font-bold text-gray-800">${profissional.name}</h5>
                                            <p class="text-gray-500 text-sm italic">${profissional.trade_name}</p>
                                        </div>
                                        <div class="flex space-x-2">
                                        </div>
                                    </div>
                                    
                                    <p class="text-gray-600 leading-relaxed">${profissional.description}</p>
                                    
                                    <div class="space-y-3">
                                        <div>
                                            <h6 class="text-base font-semibold text-gray-700 flex items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10" />
                                                </svg>
                                                Especialidades
                                            </h6>
                                            <div class="flex flex-wrap gap-2 mt-2">
                                                <c:forEach var="specialty" items="${profissional.specialty}">
                                                    <span class="bg-blue-50 text-blue-700 px-3 py-1 rounded-full text-sm">${specialty}</span>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        
                                        <div>
                                            <h6 class="text-base font-semibold text-gray-700 flex items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                                                </svg>
                                                Abordagens
                                            </h6>
                                            <div class="flex flex-wrap gap-2 mt-2">
                                                <c:forEach var="approach" items="${profissional.approach}">
                                                    <span class="bg-purple-50 text-purple-700 px-3 py-1 rounded-full text-sm">${approach}</span>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="flex justify-between items-center pt-4">
                                        <div class="flex items-center text-gray-500 text-sm">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                            </svg>
                                            <c:forEach var="cidade" items="${profissional.address.city}">
                                                    <span class="bg-purple-50 text-purple-700 px-3 py-1 rounded-full text-sm">${city}</span>
                                                </c:forEach>
                                           
                                        </div>
                                        <a href="professionalDetails.jsp?id=${profissional.ID}" 
                                           class="inline-flex items-center bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg transition-colors duration-200 text-sm font-medium">
                                            Ver Perfil Completo
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 ml-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                                            </svg>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${empty professionals}">
                        <div class="text-center py-12">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 mx-auto text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                            </svg>
                            <h3 class="mt-4 text-lg font-medium text-gray-900">Nenhum profissional encontrado</h3>
                            <p class="mt-1 text-sm text-gray-500">Não encontramos profissionais com os critérios atuais.</p>
                        </div>
                    </c:if>
                </div>
            <% } %>
            </div>
        </div>
    </div>
    <%@ include file="includes/footer.jsp"%>
</body>
</html>