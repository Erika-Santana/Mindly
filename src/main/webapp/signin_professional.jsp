<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css"
	rel="stylesheet">
<title>Cadastro Profissional</title>
</head>
<body>
	<script src="https://cdn.tailwindcss.com"></script>
	<%@ include file="includes/menu_patient.jsp"%>


	<div class="flex items-center justify-center min-h-screen m-8">
		<form class="max-w-4xl w-full bg-white p-8 rounded-lg shadow-lg" action="controller.do?action=sign-in-professional" method="post" enctype="multipart/form-data">
			<div class="text-center m-4 text-xl p-4">
				<h1 class="font-bold">Venha se juntar ao nosso time!</h1>
			</div>
			
			<% if(request.getAttribute("error_message") != null){ %>
					<div class="flex items-center p-4 mb-4 mt-4 text-sm text-red-800 rounded-lg bg-red-50" role="alert">
					  <svg class="shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
					    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
					  </svg>
					  <span class="sr-only">Info</span>
					  <div>
					    <span class="font-medium">Erro!</span> <%=request.getAttribute("error_message") %>
					  </div>
					</div>
				<%}else if(request.getAttribute("message") != null){ %>
				<div class="flex mt-4 items-center p-4 mb-4 text-sm text-green-800 rounded-lg bg-green-50" role="alert">
				  <svg class="shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
				    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
				  </svg>
				  <span class="sr-only">Info</span>
				  <div>
				    <span class="font-medium">Successo!</span> <%= request.getAttribute("message") %>
				  </div>
				</div>
				<% }; %>
			<div class="mb-5">
				<label for="loginProfessional" 
				class="block text-sm font-medium mb-3 text-gray-700">Login:</label>
				<input type="email" name="loginProfessional" id="loginProfessional"
					class="border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
					placeholder="name@email.com" required />
			</div>
			<div class="mb-5">
				<label for="password"  
					class="block text-sm font-medium mb-2 text-gray-700">Senha:</label>
				<input type="password" name="passwordProfessional"  id="passwordProfessional"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
					required />
			</div>
			<div class="mb-5">
				<label for="passwordConfirmar" 
					class="block text-sm font-medium mb-3 text-gray-700">Confirmar
					senha: </label> <input type="password"  name="passwordConfirmProfessional"  id="passwordConfirmProfessional"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
					required />
			</div>

			<div class="mb-5">
				<label for="Nome"

					class="block text-sm font-medium mb-3 text-gray-700">Nome
					completo:</label> <input type="text"  name="nome-completo"  id="nome-completo"
					class="bg-white-100 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
					required />
			</div>
			<div class="mb-5">
				<label for="Nome" 
					class="block text-sm font-medium mb-3 text-gray-700">Nome
					fantasia:</label> <input type="text" name="nome-fantasia"  id="nome-fantasia"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-5">
				<label for="area" 
					class="block text-sm font-medium mb-3 text-gray-700">Área
					especializada:</label> <input type="text" name="area"  id="area"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
					required />
			</div>
			<div class="mb-5">
				<label for="metodologia"
					class="block text-sm font-medium mb-3 text-gray-700">Metodologia
					Aplicada:</label> <input type="text" name="metodologia" id="metodologia"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
					required />
			</div>
			<div class="mb-5">		
				<h3 class="mb-4 font-semibold text-gray-900">Dias da semana na qual trabalha:</h3>	
				<div class="flex w-full">
					<div class="mb-3 w-full">		
						<ul class="items-center w-full text-sm p-2 font-medium text-gray-900 
						bg-gray-50 border border-gray-200 rounded-lg sm:flex ">
						    <li class="w-full border-b border-gray-600 sm:border-b-0 sm:border-r">
						        <div class="flex items-center ps-3">
						            <input id="vue-checkbox-list" name="dias-trabalho" type="checkbox" value="Monday" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded-sm focus:ring-blue-500 
									  focus:ring-2">
						            <label for="segunda" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 ">Segunda</label>
						        </div>
						    </li>
						    <li class="w-full border-b border-gray-600 sm:border-b-0 sm:border-r">
						        <div class="flex items-center ps-3">
						            <input id="terca" name="dias-trabalho" type="checkbox" value="Tuesday" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded-sm focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500">
						            <label for="react-checkbox-list" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 ">Terça</label>
						        </div>
						    </li>
						    <li class="w-full border-b border-gray-600 sm:border-b-0 sm:border-r">
						        <div class="flex items-center ps-3">
						            <input id="quarta" name="dias-trabalho" type="checkbox" value="Wednesday" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded-sm focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500">
						            <label for="angular-checkbox-list" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 ">Quarta</label>
						        </div>
						    </li>
						    <li class="w-full border-gray-600 sm:border-b-0 sm:border-r">
						        <div class="flex items-center ps-3">
						            <input id="laravel-checkbox-list" name="dias-trabalho" type="checkbox" value="Thirsday" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded-sm focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500">
						            <label for="quinta" class="w-full py-3 ms-2 text-sm font-medium text-gray-900">Quinta</label>
						        </div>
						    </li>
						     <li class="w-full">
						        <div class="flex items-center ps-3">
						            <input id="laravel-checkbox-list" name="dias-trabalho" type="checkbox" value="Friday" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded-sm focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500">
						            <label for="sexta" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 ">Sexta</label>
						        </div>
						    </li>
						</ul>													
					</div>
				</div>
				<div class="mb-5 flex justify-direction items-center">
				<div class="max-w-[16rem] mr-4">
					        <label for="start-time" class="block mb-1 text-sm font-medium text-gray-900">Inicio:</label>
					        <div class="relative">
					            <div class="absolute inset-y-0 end-0 top-0 flex items-center pe-3.5 pointer-events-none">
					                <svg class="w-4 h-4 text-gray-500 " aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 24 24">
					                    <path fill-rule="evenodd" d="M2 12C2 6.477 6.477 2 12 2s10 4.477 10 10-4.477 10-10 10S2 17.523 2 12Zm11-4a1 1 0 1 0-2 0v4a1 1 0 0 0 .293.707l3 3a1 1 0 0 0 1.414-1.414L13 11.586V8Z" clip-rule="evenodd"/>
					                </svg>
					            </div>
					            <input type="time" id="start-time"  name="start-time"           border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500
					             focus:border-blue-500 block w-full p-2.5" min="09:00" max="18:00" value="00:00" required />
					        </div>
					    </div>
					    <div>
					        <label for="end-time" class="block mb-1 text-sm font-medium text-gray-900">Final:</label>
					        <div class="relative">
					            <div class="absolute inset-y-0 end-0 top-0 flex items-center pe-3.5 pointer-events-none">
					                <svg class="w-4 h-4 text-gray-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 24 24">
					                    <path fill-rule="evenodd" d="M2 12C2 6.477 6.477 2 12 2s10 4.477 10 10-4.477 10-10 10S2 17.523 2 12Zm11-4a1 1 0 1 0-2 0v4a1 1 0 0 0 .293.707l3 3a1 1 0 0 0 1.414-1.414L13 11.586V8Z" clip-rule="evenodd"/>
					                </svg>
					            </div>
					            <input type="time" id="end-time"  name="end-time" class="bg-gray-50 border leading-none  class="bg-gray-50 border leading-none border-gray-300
					             text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full
					              p-2.5" min="09:00" max="18:00" value="00:00" required />
					        </div>
					    </div>
					</div>
				</div>
				
				<div class="mb-5">
				<label for="duracao"
					class="block text-sm font-medium mb-3 text-gray-700">Duração de cada sessão:</label> 
					<input	type="text" id="duracao" name="duracao"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
				</div>
				
			<div class="mb-5">

				<label for="message"
					class="block text-sm font-medium mb-3 text-gray-700">Fale
					sobre você:</label>
				<textarea id="message" rows="4" name="message"
					class="block p-2.5 w-full text-sm text-gray-900 
					bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500
					 focus:border-blue-500"
					placeholder="Descreva a sua jornada, sobre a sua metodologia profissional, e como você busca ajudar as pessoas que solicitam o seu serviço..."></textarea>

			</div>
			<div class="mb-5">
				<label for="cnpj" 

					class="block text-sm font-medium mb-3 text-gray-700">CNPJ:</label>
				<input type="text" id="cnpj" name="cnpj"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
					required />
			</div>

			<div class="mb-5">
				<label for="phone-input"
					class="block text-sm font-medium mb-3 text-gray-700">Contato:</label>
				<div class="relative">
					<div
						class="absolute inset-y-0 start-0 top-0 flex items-center 
						ps-3.5 pointer-events-none">
						<svg class="w-4 h-4 text-gray-500 dark:text-gray-400"
							aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
							fill="currentColor" viewBox="0 0 19 18">
            			    <path
								d="M18 13.446a3.02 3.02 0 0 0-.946-1.985l-1.4-1.4a3.054 3.054 0 0 0-4.218 0l-.7.7a.983.983 0 0 1-1.39 0l-2.1-2.1a.983.983 0 0 1 0-1.389l.7-.7a2.98 2.98 0 0 0 0-4.217l-1.4-1.4a2.824 2.824 0 0 0-4.218 0c-3.619 3.619-3 8.229 1.752 12.979C6.785 16.639 9.45 18 11.912 18a7.175 7.175 0 0 0 5.139-2.325A2.9 2.9 0 0 0 18 13.446Z" />
          				  </svg>
					</div>
					<input type="text" id="phone-input"
						aria-describedby="helper-text-explanation"
						class="bg-gray-50 border border-gray-300 text-gray-900 
						text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5  "
						pattern="[0-9]{2}-[0-9]{5}-[0-9]{4}" placeholder="12-43156-7890" name="phone-input"
						required />
				</div>
			</div>

			<div class="mb-9">
				<label for="imagem-profile"
					class="block text-sm font-medium mb-3 text-gray-700">Imagem Perfil:</label>
				<div class="flex items-center justify-center w-full">
				    <label for="imagem-profile" class="flex flex-col items-center justify-center 
				    w-full h-32 border-2 border-purple-300 border-dashed rounded-lg cursor-pointer 
				   bg-gray-50">
				        <div class="flex flex-col items-center justify-center pt-5 pb-6">
				            <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
				                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
				            </svg>
				            <p class="mb-2 text-sm text-center text-gray-500 dark:text-gray-400"><span class="font-semibold">Clique para upload</span> ou arraste a imagem aqui e solte</p>
				            <p class="text-xs text-gray-500 dark:text-gray-400">PNG, JPG or JPEG (MAX. 800x400px)</p>
				        </div>
				        <input id="imagem-profile" type="file" class="hidden" name="imagem-profile" accept="image/png, image/jpeg, image/jpg" />
				    </label>
				</div> 
			</div>
			<script>
			const input = document.getElementById("imagem-profile");

			input.addEventListener("change", function () {
			  const file = this.files[0];
			  if (file) {
			    const validTypes = ["image/png", "image/jpeg"];
			    if (!validTypes.includes(file.type)) {
			      alert("Tipo de arquivo inválido. Só são permitidos PNG e JPG.");
			      this.value = "";
			    }
			  }
			});</script>
				<div class="mb-5">
				<label for="rua"
					class="block text-sm font-medium mb-3 text-gray-700">Rua do estabelecimento:</label>
					 <input  name="rua" type="text" id="rua"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
					required />
			</div>
			<div class="mb-5">
				<label for="cidade" class="block text-sm font-medium mb-3 text-gray-700">Cidade:</label> 
				<input	type="text" id="cidade" name="cidade"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-5">
				<label for="numero-casa"
					class="block text-sm font-medium mb-3 text-gray-700">Número:</label>
					 <input	type="text" id="numero-casa" name="numero-casa"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-5">
				<label for="estado"
					class="block text-sm font-medium mb-3 text-gray-700">Estado:</label> 
					<input	type="text" id="estado" name="estado"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
				<div class="mb-5">
				<label for="pais"
					class="block text-sm font-medium mb-3 text-gray-700">País:</label>
					 <input type="text" id="pais" name="pais"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-9">
				<label
					class="block mb-2 text-sm font-medium text-gray-900" 
					for="multiple_files">Insira as fotos do seu local de trabalho:</label> <input
					class="block w-full text-sm text-gray-900 
					 border border-gray-300 rounded-lg cursor-pointer bg-gray-50
					  focus:outline-none" name="local-work"
					id="multiple_files" type="file" multiple>
			</div>

			<button type="submit"
				class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300
						 font-medium rounded-lg mr-8 text-sm px-5 py-2.5 mb-2 w-full">Cadastrar</button>
		</form>


	</div>
	<%@ include file="includes/footer.jsp"%>

</body>
</html>