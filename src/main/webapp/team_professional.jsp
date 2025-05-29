<%@page import="model.entities.Client"%>
<%@page import="model.entities.Professional"%>
<%@page import="model.entities.Appointments"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includes/fonts.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Conheça a nossa equipe!</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-gradient-to-b from-blue-50 to-gray-100">
    <%@ include file="includes/menu_patient.jsp"%>

  <div class="flex justify-center px-4 py-8">
    <div class="w-full max-w-4xl">
        <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">Conheça nossos colaboradores</h2>

        <%
            List<Professional> listProfessionals = (List<Professional>) request.getAttribute("lista_professionals");
            List<String> listSpecialty = null;
            if (listProfessionals != null && !listProfessionals.isEmpty()) {
                for (Professional prof : listProfessionals) {
                    String profile = request.getContextPath() + "/images/" + prof.getProfileImage();
        %>
        <div class="bg-white rounded-lg shadow-md overflow-hidden mb-6">
            <div class="md:flex">
                <div class="md:w-1/4">
                    <img class="w-full h-48 md:h-full object-cover" 
                         src="<%= profile %>" 
                         alt="Foto do profissional">
                </div>
                <div class="p-6 md:w-3/4">
                    <div class="flex justify-between">
                        <div>
                            <h3 class="text-xl font-bold text-gray-800"><%= prof.getName() %></h3>
                            <p class="text-gray-500 italic"><%= prof.getTrade_name() %></p>
                        </div>
                    </div>

                    <p class="mt-2 text-gray-600"><%= prof.getAbout_me() %></p>

                    <div class="mt-4">
                        <h4 class="font-semibold text-gray-700">Especialidades:</h4>
                        <div class="flex flex-wrap gap-2 mt-2">
                            <% 
                            listSpecialty = prof.getSpecialty();
                            for (String specialty : prof.getSpecialty()) { %>
                                <span class="bg-purple-100 text-purple-800 text-xs px-3 py-1 rounded-full"><%= specialty %></span>
                            <% } %>
                        </div>
                    </div>

                    <div class="mt-4">
                        <h4 class="font-semibold text-gray-700">Abordagens:</h4>
                        <div class="flex flex-wrap gap-2 mt-2">
                            <% for (String approach : prof.getApproach()) { %>
                                <span class="bg-blue-100 text-blue-800 text-xs px-3 py-1 rounded-full"><%= approach %></span>
                            <% } %>
                        </div>
                    </div>

                    <div class="mt-6 flex justify-between items-center">
                        <div class="text-sm text-gray-500">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 inline mr-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                            </svg>
                            <%= prof.getAddress().getCity() %>
                        </div>
                        <a href="controller.do?action=viewProfessional&id=<%= prof.getID() %>" 
                           class="bg-purple-600 hover:bg-purple-700 text-white px-4 py-2 rounded-lg text-sm">
                            Saber mais sobre esse profissional
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <% 
                } 
            } else { 
        %>
        <div class="text-center py-12 bg-white rounded-lg shadow">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 mx-auto text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <h3 class="mt-4 text-lg font-medium text-gray-900">Nenhum profissional encontrado</h3>
            <p class="mt-1 text-sm text-gray-500">Não encontramos profissionais disponíveis no momento.</p>
        </div>
        <% } %>
    </div>
</div>


    <%@ include file="includes/footer.jsp"%>
</body>
</html>
