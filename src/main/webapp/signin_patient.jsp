<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css"
	rel="stylesheet">
<title>Cadastro Paciente</title>
</head>
<body>
	<script src="https://cdn.tailwindcss.com"></script>
	<%@ include file="includes/menu.jsp"%>


	<div class="flex items-center justify-center min-h-screen m-8">
		<form class="w-1/3 bg-white p-8 rounded-lg shadow-lg" action="controller.do?action=register_paciente" method="post" enctype="multipart/form-data">
			<div class="text-center m-4 text-xl p-4">
				<h1 class="font-bold">Cadastre-se</h1>
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
				    <span class="font-medium">Successo!</span> <%=request.getAttribute("message") %>
				  </div>
				</div>
					
				<% }; %>
			</div>
			<div class="mb-5">
			
				<label for="login"
					class="block text-sm font-medium mb-3 text-gray-700">Login:</label>
				<input type="email" id="login" name="login"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
					placeholder="name@email.com" required />
			</div>
			<div class="mb-5">
				<label for="password"
					class="block text-sm font-medium mb-2 text-gray-700">Senha:</label>
				<input type="password" id="password" name="password"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
					required />
			</div>
			<div class="mb-5">
				<label for="password-check"
					class="block text-sm font-medium mb-3 text-gray-700">Confirmar
					senha: </label> <input type="password" id="password-check"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
					required />
			</div>

			<div class="mb-5">
				<label for="nome"
					class="block text-sm font-medium mb-3 text-gray-700">Nome
					completo:</label> <input type="text" id="nome-completo" name="nome"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-5">
				<label for="cpf"
					class="block text-sm font-medium mb-3 text-gray-700">CPF:</label> <input
					type="text" id="cpf" name="cpf"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-5">
				<label for="rua"
					class="block text-sm font-medium mb-3 text-gray-700">Rua:</label> <input
					type="text" id="rua" name="rua"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 "
					required />
			</div>
			<div class="mb-5">
				<label for="cidade"
					class="block text-sm font-medium mb-3 text-gray-700">Cidade:</label> <input
					type="text" id="cidade" name="cidade"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-5">
				<label for="numero-casa"
				
					class="block text-sm font-medium mb-3 text-gray-700">Número da casa:</label> <input
					type="text" id="numero-casa" name="numero-casa"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
			<div class="mb-5">
				<label for="estado"
					class="block text-sm font-medium mb-3 text-gray-700">Estado:</label> <input
					type="text" id="estado" name="estado"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
					required />
			</div>
				<div class="mb-5">
				<label for="pais"
					class="block text-sm font-medium mb-3 text-gray-700">País:</label> <input
					type="text" id="pais" name="pais"
					class="bg-white-50 border border-white-300 text-white-900 text-sm rounded-lg 
						focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 
						"
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

			<button type="submit" value="cadastrar"
				class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300
						 font-medium rounded-lg mr-8 text-sm px-5 py-2.5 mb-2 
						   w-full">Cadastrar</button>
		</form>


	</div>
	<%@ include file="includes/footer.jsp"%>

</body>
</html>