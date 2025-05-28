<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.tailwindcss.com"></script>
<nav class="flex items-center justify-between flex-wrap bg-purple-400 p-6">
    <div class="flex items-center flex-shrink-0 text-purple mr-6">
        <a href="controller.do?action=clickHome">
            <img src="imagem/cerebro_icone.png" alt="ícone do cérebro" class="h-8 w-8 mr-2" />
        </a>
        <span class="font-semibold text-xl quicksand-font tracking-tight">Mindly</span>
    </div>
    
    <div class="block lg:hidden">
        <button class="flex items-center px-3 py-2 border rounded text-purple-200 border-purple-400 hover:text-purple hover:border-purple">
            <svg class="fill-current h-3 w-3" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                <title>Menu</title>
                <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/>
            </svg>
        </button>
    </div>
    
    <div class="w-full block flex-grow lg:flex lg:items-center lg:w-auto">
        <div class="text-sm lg:flex-grow">
            <a href="controller.do?action=clickAboutUs"
                class="block mt-4 lg:inline-block lg:mt-0 text-purple-200 hover:text-purple mr-4">
                Sobre nós 
            </a>
            <a href="controller.do?action=clickTeamProfessionals"
                class="block mt-4 lg:inline-block lg:mt-0 text-purple-200 hover:text-purple mr-4">
                Conheça a nossa equipe 
            </a>
            <a href="controller.do?action=clickAppointment"
                class="block mt-4 lg:inline-block lg:mt-0 text-purple-200 hover:text-purple mr-4">
                Agende com um profissional 
            </a>
            <a href="controller.do?action=clickContactUs"
                class="block mt-4 lg:inline-block lg:mt-0 text-purple-200 hover:text-purple">
                Contate-nos 
            </a>
        </div>
        
        <div>
            <c:choose>
                <c:when test="${not empty sessionScope.cliente or not empty sessionScope.professional}">
                
                    <div class="flex items-center space-x-4">
                        <c:if test="${not empty sessionScope.cliente}">
                            <span class="text-purple-200">Olá, ${sessionScope.cliente.client_name}</span>
                        </c:if>
                        <c:if test="${not empty sessionScope.professional}">
                            <span class="text-purple-200">Olá, ${sessionScope.professional.name}</span>
                        </c:if>
                        <a href="controller.do?action=logout"
                            class="inline-block text-sm px-4 py-2 leading-none border rounded text-purple border-purple hover:border-transparent hover:text-purple-500 hover:bg-purple mt-4 lg:mt-0">
                            Sair
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="flex items-center space-x-4">
                        <a href="controller.do?action=clickSignIn"
                            class="inline-block text-sm px-4 mr-4 py-2 leading-none text-black mt-4 lg:mt-0">
                            Cadastre-se
                        </a>
                        <a href="controller.do?action=clickLogin"
                            class="inline-block text-sm px-4 py-2 leading-none border rounded text-purple border-purple hover:border-transparent hover:text-purple-500 hover:bg-purple mt-4 lg:mt-0">
                            Login
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>