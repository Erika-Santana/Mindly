<%@page import="model.entities.Client"%>
<%@page import="model.entities.Professional"%>
<%@page import="model.entities.Appointments"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="includes/fonts.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Client cliente = (Client) session.getAttribute("cliente");
Professional professional = (Professional) session.getAttribute("professional");

String fotoPerfil = "https://dummyimage.com/160x160/cccccc/000000&text=Sem+Imagem";

if (cliente != null && cliente.getProfile() != null && !cliente.getProfile().isEmpty()) {
	fotoPerfil = request.getContextPath() + "/images/" + cliente.getProfile();
} else if (professional != null && professional.getProfileImage() != null
		&& !professional.getProfileImage().isEmpty()) {
	fotoPerfil = request.getContextPath() + "/images/" + professional.getProfileImage();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conheça a nossa equipe!</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-gradient-to-b from-blue-50 to-gray-100">
	<%@ include file="includes/menu_patient.jsp"%>

	<div class="container mx-auto px-4 py-8">
		<div class="grid grid-cols-1 md:grid-cols-4 gap-6">

			<div class="md:col-span-1">
				<div
					class="bg-white border border-gray-200 rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
					<div class="p-4 text-center sacramento-regular text-purple">
						<h3 class="text-lg font-semibold" style="font-size: 24px">Meu
							Perfil</h3>
					</div>
					<div class="p-4">
						<div class="flex justify-center mb-4">
							<img
								class="w-24 h-24 rounded-full object-cover border-4 border-purple-200"
								src="<%=fotoPerfil%>" alt="Foto do perfil">
						</div>
						<h4 class="text-center font-semibold text-gray-800">
							<%=cliente != null ? cliente.getClient_name() : professional.getName()%>
						</h4>
						<div class="text-center mt-4">
							<span
								class="inline-block bg-purple-100 text-purple-800 text-xs px-3 py-1 rounded-full">
								<%=cliente != null ? "Paciente" : professional.getSpecialty().get(0)%>
							</span>
						</div>
						<div class="mt-4 pt-4">
							<p class="text-sm text-gray-600">
								<strong>Contato:</strong>
								<%=cliente != null ? cliente.getContact() : professional.getContato()%>
							</p>
						</div>
					</div>
				</div>
			</div>


			<div class="md:col-span-3">
				<div>
					<div class="flex items-center">
						<h2 class="text-2xl font-bold text-gray-800 mb-6">Profissionais
							Disponíveis</h2>

						<form class="max-w-lg mx-auto"
							action="controller.do?action=clickFilter" method="post" onsubmit="return testeEnvio()">
							<div class="flex">
								<label for="search-dropdown"
									class="mb-2 text-sm font-medium text-gray-900 sr-only ">Your
									Email</label>
								
								<button id="dropdown-button" onclick="toggleDropdown()"
									class="shrink-0 z-10 inline-flex items-center py-2.5 px-4 text-sm font-medium 
									text-center text-gray-900 bg-gray-100 border border-gray-300 
									rounded-s-lg hover:bg-gray-200 focus:ring-4 
									focus:outline-none focus:ring-gray-100"
									type="button">
									<span id="dropdown-label">Opções</span>
									<svg class="w-2.5 h-2.5 ms-2.5" aria-hidden="true"
										xmlns="http://www.w3.org/2000/svg" fill="none"
										viewBox="0 0 10 6">
									<path stroke="currentColor" stroke-linecap="round"
												stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4" />
								</svg>
								</button>

				
								<div id="dropdown"
									class="absolute mt-1 z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow-sm w-44">
									<ul class="py-2 text-sm text-gray-700"
										aria-labelledby="dropdown-button">
										<li>
											<button type="button" onclick="setFiltro('cidade')"
												class="inline-flex w-full px-4 py-2 hover:bg-gray-100">Cidade</button>
										</li>
										<li>
											<button type="button" onclick="setFiltro('abordagem')"
												class="inline-flex w-full px-4 py-2 hover:bg-gray-100">Abordagem</button>
										</li>
										<li>
											<button type="button" onclick="setFiltro('area')"
												class="inline-flex w-full px-4 py-2 hover:bg-gray-100">Área
												de Atuação</button>
										</li>
									</ul>
								</div>
								
								<script>
									function validarFiltro() {
									    const filtro = document.getElementById("filtroSelecionado").value;
									    if (!filtro) {
									      alert("Selecione uma opção antes de pesquisar.");
									      return false;
									    }
									    return true;
									  }
									
									function testeEnvio() {
									    console.log("Filtro:", document.getElementById("filtroSelecionado").value);
									    console.log("Busca:", document.getElementById("search-dropdown").value);
									    return true; 
									  }
									function toggleDropdown() {
										document.getElementById("dropdown").classList
												.toggle("hidden");
									}

									function capitalize(text) {
										return text.charAt(0).toUpperCase()
												+ text.slice(1);
									}

									function setFiltro(valor) {
										if(!valor){
											valor = "cidade";
										}
										document.getElementById("filtroSelecionado").value = valor;
										document.getElementById("dropdown-label").innerText = capitalize(valor);
										document.getElementById("dropdown").classList
												.add("hidden");
										 setTimeout(() => {
											    console.log("Filtro setado:", valor);
											  }, 50);
									}

									window.addEventListener(
													"click",
													function(event) {
														const dropdown = document
																.getElementById("dropdown");
														const button = document
																.getElementById("dropdown-button");

														if (!dropdown
																.contains(event.target)
																&& !button
																		.contains(event.target)) {
															dropdown.classList
																	.add("hidden");
														}
													});
								</script>
								<input type="hidden" name="filtro" id="filtroSelecionado" />

								<div class="relative w-full">
									<input type="text" id="search-dropdown"
										name="search-dropdown"
										class="block p-2.5 w-full z-20 text-sm text-gray-900 bg-gray-50 rounded-e-lg border-s-gray-50 border-s-2 border border-gray-300 focus:ring-blue-500 focus:border-blue-500 "
										placeholder="Search Mockups, Logos, Design Templates..."
										required />
									<button type="submit"
										class="absolute top-0 end-0 p-2.5 text-sm font-medium h-full text-white bg-blue-700 rounded-e-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none
									 focus:ring-blue-300 ">
										<svg class="w-4 h-4" aria-hidden="true"
											xmlns="http://www.w3.org/2000/svg" fill="none"
											viewBox="0 0 20 20">
                   						 <path stroke="currentColor"
												stroke-linecap="round" stroke-linejoin="round"
												stroke-width="2"
												d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z" />
                					</svg>
										<span class="sr-only">Pesquisar</span>
									</button>
								</div>
							</div>
						</form>
					</div>

				</div>


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
								src="<%=profile%>" alt="Foto do profissional">
						</div>
						<div class="p-6 md:w-3/4">
							<div class="flex justify-between">
								<div>
									<h3 class="text-xl font-bold text-gray-800"><%=prof.getName()%></h3>
									<p class="text-gray-500 italic"><%=prof.getTrade_name()%></p>
								</div>
							</div>

							<p class="mt-2 text-gray-600"><%=prof.getAbout_me()%></p>

							<div class="mt-4">
								<h4 class="font-semibold text-gray-700">Especialidades:</h4>
								<div class="flex flex-wrap gap-2 mt-2">
									<%
									listSpecialty = prof.getSpecialty();
									for (String specialty : prof.getSpecialty()) {
									%>
									<span
										class="bg-purple-100 text-purple-800 text-xs px-3 py-1 rounded-full"><%=specialty%></span>
									<%
									}
									%>
								</div>
							</div>

							<div class="mt-4">
								<h4 class="font-semibold text-gray-700">Abordagens:</h4>
								<div class="flex flex-wrap gap-2 mt-2">
									<%
									for (String approach : prof.getApproach()) {
									%>
									<span
										class="bg-blue-100 text-blue-800 text-xs px-3 py-1 rounded-full"><%=approach%></span>
									<%
									}
									%>
								</div>
							</div>

							<div class="mt-6 flex justify-between items-center">
								<div class="text-sm text-gray-500">
									<svg xmlns="http://www.w3.org/2000/svg"
										class="h-4 w-4 inline mr-1" fill="none" viewBox="0 0 24 24"
										stroke="currentColor">
                                        <path stroke-linecap="round"
											stroke-linejoin="round" stroke-width="2"
											d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                        <path stroke-linecap="round"
											stroke-linejoin="round" stroke-width="2"
											d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                    </svg>
									<%=prof.getAddress().getCity()%>
								</div>
								<a
									href="controller.do?action=viewProfessional&id=<%=prof.getID()%>"
									class="bg-purple-600 hover:bg-purple-700 text-white px-4 py-2 rounded-lg text-sm">
									Saber mais sobre esse profissional </a>
							</div>
						</div>
					</div>
				</div>
				<%
				}
				} else {
				%>
				<div class="text-center py-12 bg-white rounded-lg shadow">
					<svg xmlns="http://www.w3.org/2000/svg"
						class="h-12 w-12 mx-auto text-gray-400" fill="none"
						viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2"
							d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
					<h3 class="mt-4 text-lg font-medium text-gray-900">Nenhum
						profissional encontrado</h3>
					<p class="mt-1 text-sm text-gray-500">Não encontramos
						profissionais disponíveis no momento.</p>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>
