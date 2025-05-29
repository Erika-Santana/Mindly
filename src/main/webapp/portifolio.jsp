<%@page import="model.entities.Professional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<title>Portifólio</title>
</head>
<body>

	<% Professional professional = null;

    if (session != null && session.getAttribute("professional") != null) {
        professional = (Professional) session.getAttribute("professional");
    } else if (request.getAttribute("professional") != null) {
        professional = (Professional) request.getAttribute("professional");
    }
		%>
	<script src="https://cdn.tailwindcss.com"></script>
	<%@ include file="includes/menu_patient.jsp"%>
	<div class="mt-8 flex justify-center items-center">
		<figure
			class="relative max-w-sm transition-all duration-300 cursor-pointer filter grayscale hover:grayscale-0">
			<a href=""> <img class="rounded-full w-96 h-96"
				src="images/ImagemHome.png" alt="image description">
			</a>
			<figcaption class="absolute px-4 text-lg text-black bottom-6">
				<p><% professional.getName(); %></p>
			</figcaption>
		</figure>
		<div class="p-8 w-1/3">
			<h1 class="font-bold text-x1 text-center p-4">Sobre mim:</h1>
			<p><%professional.getAbout_me();%></p>
		</div>
	</div>

	<div
		class="flex flex-col gap-4 justify-center items-center bg-purple-100 p-8 mt-8">
		<h1 class="font-semibold text-lg text-center p-4">Minha
			metodologia e abordagem:</h1>
		<p><%professional.getAbout_my_job(); %></p>

	</div>

	<div class="bg-pink-100 p-8">
		<h1 class="font-bold font-semibold text-lg p-8 items-center justify-center flex">Meu
					local de trabalho:</h1>
		<div class="pb-12 pt-8 flex justify-center">
		<div class="flex justify-center text-left gap-8 w-3/4 bg-white rounded-2xl shadow-lg p-6">
			<div id="gallery" class="relative w-3/4 " data-carousel="slide">
			
				<div class="relative h-56 overflow-hidden rounded-lg md:h-96">
			
					<div class="hidden duration-700 ease-in-out" data-carousel-item>
						<img
							src="https://flowbite.s3.amazonaws.com/docs/gallery/square/image-1.jpg"
							class="absolute block max-w-full h-auto -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
							alt="">
					</div>
					<!-- Item 2 -->
					<div class="hidden duration-700 ease-in-out"
						data-carousel-item="active">
						<img
							src="https://flowbite.s3.amazonaws.com/docs/gallery/square/image-2.jpg"
							class="absolute block max-w-full h-auto -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
							alt="">
					</div>
					<!-- Item 3 -->
					<div class="hidden duration-700 ease-in-out" data-carousel-item>
						<img
							src="https://flowbite.s3.amazonaws.com/docs/gallery/square/image-3.jpg"
							class="absolute block max-w-full h-auto -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
							alt="">
					</div>
					<!-- Item 4 -->
					<div class="hidden duration-700 ease-in-out" data-carousel-item>
						<img
							src="https://flowbite.s3.amazonaws.com/docs/gallery/square/image-4.jpg"
							class="absolute block max-w-full h-auto -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
							alt="">
					</div>
					<!-- Item 5 -->
					<div class="hidden duration-700 ease-in-out" data-carousel-item>
						<img
							src="https://flowbite.s3.amazonaws.com/docs/gallery/square/image-5.jpg"
							class="absolute block max-w-full h-auto -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
							alt="">
					</div>
				</div>
		
				<button type="button"
					class="absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
					data-carousel-prev>
					<span
						class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
						<svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180"
							aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
							viewBox="0 0 6 10">
		                <path stroke="currentColor" stroke-linecap="round"
								stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4" />
		            </svg> <span class="sr-only">Previous</span>
					</span>
				</button>
				<button type="button"
					class="absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
					data-carousel-next>
					<span
						class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
						<svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180"
							aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
							viewBox="0 0 6 10">
		                <path stroke="currentColor" stroke-linecap="round"
								stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4" />
		            </svg> <span class="sr-only">Next</span>
					</span>
				</button>
			</div>
		</div>
	</div>
	
	</div>
	
	<%@ include file="includes/footer.jsp"%>


</body>
</html>