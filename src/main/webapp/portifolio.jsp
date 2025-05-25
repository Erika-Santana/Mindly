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
	<script src="https://cdn.tailwindcss.com"></script>
	<%@ include file="includes/menu.jsp"%>
	<div class="mt-8 flex justify-center items-center">
		<figure
			class="relative max-w-sm transition-all duration-300 cursor-pointer filter grayscale hover:grayscale-0">
			<a href=""> <img class="rounded-full w-96 h-96"
				src="images/ImagemHome.png" alt="image description">
			</a>
			<figcaption class="absolute px-4 text-lg text-black bottom-6">
				<p>Nome do profissional</p>
			</figcaption>
		</figure>
		<div class="p-8 w-1/3">
			<h1 class="font-bold text-x1 text-center p-4">Sobre mim:</h1>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
				euismod, nisl a convallis sollicitudin, justo nisi tincidunt eros,
				non dictum enim elit vitae augue. Donec nec mauris vitae justo
				tincidunt imperdiet. Proin venenatis tincidunt nunc, eget hendrerit
				erat pulvinar et. Integer vestibulum, s em vel ultrices vehicula,
				justo purus sagittis nisl, vitae dictum ex orci at magna.</p>
		</div>
	</div>

	<div
		class="flex flex-col gap-4 justify-center items-center bg-purple-100 p-8 mt-8">
		<h1 class="font-semibold text-lg text-center p-4">Minha
			metodologia e abordagem:</h1>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
			euismod, nisl a convallis sollicitudin, justo nisi tincidunt eros,
			non dictum enim elit vitae augue. Donec nec mauris vitae justo
			tincidunt imperdiet. Proin venenatis tincidunt nunc, eget hendrerit
			erat pulvinar et. Integer vestibulum, s em vel ultrices vehicula,
			justo purus sagittis nisl, vitae dictum ex orci at magna.</p>

	</div>
	<div class="bg-pink-100 pb-8 ">
		<h1
			class="font-bold font-semibold text-lg p-8 items-center justify-center flex">Conheça
			os pacientes e comentários que a respeito do meu trabalho:</h1>
		<div
			class="grid border border-gray-200 rounded-lg m-4 shadow-xs md:mb-12 md:grid-cols-2 bg-white">

			<figure
				class="flex flex-col items-center justify-center p-8 text-center
			     bg-white border-b border-gray-200 rounded-t-lg md:rounded-t-none md:rounded-ss-lg 
			    md:border-e  ">
				<blockquote class="max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8">
					<h3 class="text-lg font-semibold text-gray-900 ">Very easy
						this was to integrate</h3>
					<p class="my-4">If you care for your time, I hands down would
						go with this."</p>
				</blockquote>
				<figcaption class="flex items-center justify-center ">
					<img class="rounded-full w-9 h-9"
						src="https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/karen-nelson.png"
						alt="profile picture">
					<div class="space-y-0.5 font-medium text-left rtl:text-right ms-3">
						<div>Bonnie Green</div>
						<div class="text-sm text-gray-500  ">Developer at Open AI</div>
					</div>
				</figcaption>
			</figure>
			<figure
				class="flex flex-col items-center justify-center p-8 text-center bg-white border-b border-gray-200
			     md:rounded-se-lg ">
				<blockquote class="max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8 ">
					<h3 class="text-lg font-semibold text-gray-900 ">Solid
						foundation for any project</h3>
					<p class="my-4">Designing with Figma components that can be
						easily translated to the utility classes of Tailwind CSS is a huge
						timesaver!"</p>
				</blockquote>
				<figcaption class="flex items-center justify-center ">
					<img class="rounded-full w-9 h-9"
						src="https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/roberta-casas.png"
						alt="profile picture">
					<div class="space-y-0.5 font-medium  text-left rtl:text-right ms-3">
						<div>Roberta Casas</div>
						<div class="text-sm text-gray-500">Lead designer at Dropbox</div>
					</div>
				</figcaption>
			</figure>
			<figure
				class="flex flex-col items-center justify-center p-8 text-center bg-white
			     border-b border-gray-200 md:rounded-es-lg md:border-b-0 md:border-e ">
				<blockquote class="max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8 ">
					<h3 class="text-lg font-semibold text-gray-900">Mindblowing
						workflow</h3>
					<p class="my-4">Aesthetically, the well designed components are
						beautiful and will undoubtedly level up your next application."</p>
				</blockquote>
				<figcaption class="flex items-center justify-center ">
					<img class="rounded-full w-9 h-9"
						src="https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/jese-leos.png"
						alt="profile picture">
					<div
						class="space-y-0.5 font-medium dark:text-white text-left rtl:text-right ms-3">
						<div>Jese Leos</div>
						<div class="text-sm text-gray-500 ">Software Engineer at
							Facebook</div>
					</div>
				</figcaption>
			</figure>
			<figure
				class="flex flex-col items-center justify-center p-8 text-center bg-white border-gray-200 
			    rounded-b-lg md:rounded-se-lg ">
				<blockquote class="max-w-2xl mx-auto mb-4 text-gray-500 lg:mb-8 ">
					<h3 class="text-lg font-semibold text-gray-900 ">Efficient
						Collaborating</h3>
					<p class="my-4">You have many examples that can be used to
						create a fast prototype for your team."</p>
				</blockquote>
				<figcaption class="flex items-center justify-center ">
					<img class="rounded-full w-9 h-9"
						src="https://flowbite.s3.amazonaws.com/blocks/marketing-ui/avatars/joseph-mcfall.png"
						alt="profile picture">
					<div class="space-y-0.5 font-medium text-left rtl:text-right ms-3">
						<div>Joseph McFall</div>
						<div class="text-sm text-gray-500 ">CTO at Google</div>
					</div>
				</figcaption>
			</figure>
		</div>

	</div>

	<div class="pb-12 pt-8 bg-pink-100 flex justify-center">
		<div class="flex justify-center gap-8 w-3/4 bg-white rounded-2xl shadow-lg p-6">
				
			<div class="mt-4">
				<h1
					class="font-bold font-semibold text-lg p-8 items-center justify-center flex">Meu
					local de trabalho:</h1>
				<ul>
					<li>Rua:</li>
					<li>Número:</li>
					<li>Bairro:</li>
					<li>Cidade:</li>
					<li>Estado:</li>
				</ul>
			</div>


			<div id="gallery" class="relative w-2/4 " data-carousel="slide">
				<!-- Carousel wrapper -->
				<div class="relative h-56 overflow-hidden rounded-lg md:h-96">
					<!-- Item 1 -->
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
				<!-- Slider controls -->
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
	<%@ include file="includes/footer.jsp"%>


</body>
</html>