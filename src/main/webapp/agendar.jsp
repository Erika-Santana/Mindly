<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<title>Contato</title>
</head>
<body>
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="../path/to/flowbite/dist/flowbite.min.js"></script>
	
	<%@ include file="includes/menu_patient.jsp"%>
	
	<div class="flex items-center justify-center min-h-screen m-8">
		<form class="w-1/3 bg-white p-8 rounded-lg shadow-lg" action="login">
			<div class="text-center m-4 text-xl p-4">
				<h1 class="font-bold">Contato</h1>
			</div>
			
			<div class="max-w-sm mx-auto">
			  <label for="countries" class="block mb-2 text-sm font-medium text-gray-900 ">Selecione uma modalidade</label>
			  <select id="countries" class="bg-gray-50 border border-gray-300 text-gray-900 
			  text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block 
			  w-full p-2.5 ">
			    <option selected value="psicologo">Psicólogo</option>
			    <option value="psiquiatra">Psiquiatra</option>
			    <option value="terapeuta">Terapeuta</option>
			    <option value="psicanalise">Psicanálise</option>
			    
			  </select>
			</div>
			
			<!-- Listagem do que já tem -->
			<div class="max-w-sm mx-auto mt-4">
			  <label for="countries" class="block mb-2 text-sm font-medium text-gray-900 ">Selecione o profissional</label>
			  <select id="countries" class="bg-gray-50 border border-gray-300 text-gray-900 
			  text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block 
			  w-full p-2.5 ">
			    <option selected value="psicologo">Psicólogo</option>
			    <option value="psiquiatra">Psiquiatra</option>
			    <option value="terapeuta">Terapeuta</option>
			    <option value="psicanalise">Psicanálise</option>
			    
			  </select>
			</div>
			
			<div class="mt-4">
				<h1 class="block mb-2 text-sm font-medium text-gray-900">Selecione o dia e o horário do agendamento</h1>
				<button type="button" data-modal-target="timepicker-modal" data-modal-toggle="timepicker-modal" class="text-gray-900 bg-white hover:bg-gray-100 border border-gray-200 focus:ring-4 focus:outline-none focus:ring-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center">
				<svg class="w4 h-4 me-1" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" viewBox="0 0 24 24">
				  <path fill-rule="evenodd" d="M2 12C2 6.477 6.477 2 12 2s10 4.477 10 10-4.477 10-10 10S2 17.523 2 12Zm11-4a1 1 0 1 0-2 0v4a1 1 0 0 0 .293.707l3 3a1 1 0 0 0 1.414-1.414L13 11.586V8Z" clip-rule="evenodd"/>
				</svg>
				Agendamento
				</button>
				
				<!-- Main modal -->
				<div id="timepicker-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto 
				overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center 
				w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
				    <div class="relative p-4 w-full max-w-[23rem] max-h-full">
				        <!-- Modal content -->
				        <div class="relative bg-purple-200 rounded-lg shadow-sm 
				        ">
				            <!-- Modal header -->
				            <div class="flex items-center justify-between p-4 border-b rounded-t 
				            border-gray-200">
				                <h3 class="text-lg font-semibold text-gray-900">
				                    Selecione uma data
				                </h3>
				                <button type="button" class="text-gray-400 bg-transparent
				                 hover:bg-gray-200 hover:text-gray-900 
				                 rounded-lg text-sm h-8 w-8 ms-auto inline-flex justify-center items-center" data-modal-toggle="timepicker-modal">
				                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="#000000" viewBox="0 0 14 14">
				                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
				                    </svg>
				                    <span class="sr-only">Fechar</span>
				                </button>
				            </div>
				           
				            <div class="p-4 pt-0">
				                <div inline-datepicker datepicker-autoselect-today class="mx-auto sm:mx-0 flex 
				                justify-center my-5 [&>div>div]:shadow-none
				                 [&>div>div]:bg-gray-300 [&_div>button]:bg-gray-500"></div>
				                <label class="text-sm font-medium text-gray-900 mb-2 block">
				                Selecione o horário
				                </label>
				                <ul id="timetable" class="grid w-full grid-cols-3 gap-2 mb-5">
				                    <li>
				                        <input type="radio" id="10-am" value="" class="hidden peer" name="timetable">
				                        <label for="10-am"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium text-center 
				                        hover:text-gray-900	bg-white border rounded-lg cursor-pointer text-gray-500 border-gray-200  
				                         peer-checked:border-blue-700 peer-checked:bg-blue-50 peer-checked:text-blue-700 hover:bg-gray-50">
				                        10:00 AM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="10-30-am" value="" class="hidden peer" name="timetable">
				                        <label for="10-30-am"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm 
				                        font-medium text-center hover:text-gray-900 bg-white border rounded-lg cursor-pointer text-gray-500 
				                         border-gray-200 peer-checked:border-blue-700 
				                           peer-checked:bg-blue-50 
				                          peer-checked:text-blue-700 hover:bg-gray-50">
				                        10:30 AM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="11-am" value="" class="hidden peer" name="timetable">
				                        <label for="11-am"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        11:00 AM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="11-30-am" value="" class="hidden peer" name="timetable">
				                        <label for="11-30-am"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-500 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        11:30 AM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="12-am" value="" class="hidden peer" name="timetable" checked>
				                        <label for="12-am"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        12:00 AM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="12-30-pm" value="" class="hidden peer" name="timetable">
				                        <label for="12-30-pm"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        12:30 PM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="1-pm" value="" class="hidden peer" name="timetable">
				                        <label for="1-pm"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        01:00 PM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="1-30-pm" value="" class="hidden peer" name="timetable">
				                        <label for="1-30-pm"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 peer-checked:bg-blue-50 peer-checked:text-blue-700 hover:bg-gray-50">
				                        01:30 PM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="2-pm" value="" class="hidden peer" name="timetable">
				                        <label for="2-pm"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        02:00 PM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="2-30-pm" value="" class="hidden peer" name="timetable">
				                        <label for="2-30-pm"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        02:30 PM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="3-pm" value="" class="hidden peer" name="timetable">
				                        <label for="3-pm"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        03:00 PM
				                        </label>
				                    </li>
				                    <li>
				                        <input type="radio" id="3-30-pm" value="" class="hidden peer" name="timetable">
				                        <label for="3-30-pm"
				                        class="inline-flex items-center justify-center w-full px-2 py-1 text-sm font-medium 
				                        text-center hover:text-gray-900 bg-white rounded-lg 
				                        cursor-pointer text-gray-900 border-gray-200 
				                         peer-checked:border-blue-700 
				                        peer-checked:bg-blue-50 
				                        peer-checked:text-blue-700 hover:bg-gray-50">
				                        03:30 PM
				                        </label>
				                    </li>
				                </ul>
				                <div class="grid grid-cols-2 gap-2">
				                    <button type="button" class="text-white bg-blue-78 00 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 focus:outline-none">Save</button>
				                    <button type="button" data-modal-hide="timepicker-modal" class="py-2.5 px-5 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100">Discard</button>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>
			</div>

			
			<button type="submit"
				class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300
						 font-medium rounded-lg mr-4 mt-4 text-sm px-5 py-2.5 mb-2 
						   w-full">Cadastrar</button>
		</form>
	</div>
	<%@ include file="includes/footer.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/flowbite@3.1.2/dist/flowbite.min.js"></script>

</body>
</html>