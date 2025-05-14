<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-800 to-blue-900 text-white p-8">
      <div class="max-w-4xl mx-auto">
        <!-- En-tête de la page About -->
        <div class="bg-blue-700 rounded-t-lg shadow-lg p-6 mb-4">
          <h1 class="text-3xl font-bold text-center">À propos de SportElite</h1>
          <div class="w-24 h-1 bg-blue-400 mx-auto my-4"></div>
          <p class="text-center text-blue-200">Votre partenaire d'équipement sportif premium depuis 2010</p>
        </div>
        
        <!-- Notre histoire -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 mb-4">
          <div class="flex items-center mb-6">
            <div class="bg-blue-600 rounded-full p-3 mr-4">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <h2 class="text-2xl font-bold">Notre Histoire</h2>
          </div>
          <div class="flex flex-col md:flex-row gap-6">
            <div class="md:w-1/2">
              <p class="mb-4">Fondée en 2010 par deux athlètes passionnés, SportElite est née d'une vision simple : proposer des équipements sportifs de haute qualité qui répondent aux exigences des athlètes professionnels tout en restant accessibles aux amateurs enthousiastes.</p>
              <p>Au fil des années, nous avons évolué d'une petite boutique locale à une référence nationale dans le domaine de l'équipement sportif technique. Notre engagement envers l'excellence et l'innovation nous a permis de créer une communauté fidèle d'athlètes qui nous font confiance pour leurs performances.</p>
            </div>
            <div class="md:w-1/2 bg-blue-800 rounded-lg p-4">
              <div class="grid grid-cols-2 gap-2">
                <div class="flex flex-col items-center p-2 bg-blue-700 rounded-lg">
                  <p class="text-3xl font-bold text-blue-300">2010</p>
                  <p class="text-sm">Fondation</p>
                </div>
                <div class="flex flex-col items-center p-2 bg-blue-700 rounded-lg">
                  <p class="text-3xl font-bold text-blue-300">2015</p>
                  <p class="text-sm">Expansion nationale</p>
                </div>
                <div class="flex flex-col items-center p-2 bg-blue-700 rounded-lg">
                  <p class="text-3xl font-bold text-blue-300">2018</p>
                  <p class="text-sm">Lancement en ligne</p>
                </div>
                <div class="flex flex-col items-center p-2 bg-blue-700 rounded-lg">
                  <p class="text-3xl font-bold text-blue-300">2023</p>
                  <p class="text-sm">Renouveau digital</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <!-- Notre mission -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 mb-4">
          <div class="flex items-center mb-6">
            <div class="bg-blue-600 rounded-full p-3 mr-4">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
            <h2 class="text-2xl font-bold">Notre Mission</h2>
          </div>
          <p class="mb-4">Chez SportElite, notre mission est d'équiper les athlètes avec le meilleur matériel pour qu'ils puissent atteindre leur plein potentiel. Nous croyons fermement que l'équipement adéquat peut faire la différence entre la bonne performance et l'excellence.</p>
          
          <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mt-6">
            <div class="bg-blue-800 p-4 rounded-lg text-center">
              <div class="w-16 h-16 bg-blue-600 rounded-full flex items-center justify-center mx-auto mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                </svg>
              </div>
              <h3 class="font-bold text-lg mb-2">Qualité</h3>
              <p class="text-blue-200">Nous sélectionnons rigoureusement nos produits pour garantir durabilité et performance.</p>
            </div>
            <div class="bg-blue-800 p-4 rounded-lg text-center">
              <div class="w-16 h-16 bg-blue-600 rounded-full flex items-center justify-center mx-auto mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                </svg>
              </div>
              <h3 class="font-bold text-lg mb-2">Performance</h3>
              <p class="text-blue-200">Nos équipements sont conçus pour aider les athlètes à se dépasser.</p>
            </div>
            <div class="bg-blue-800 p-4 rounded-lg text-center">
              <div class="w-16 h-16 bg-blue-600 rounded-full flex items-center justify-center mx-auto mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 015.36 19h-.773a2 2 0 01-1.789-2.894l3.5-7A2 2 0 018.11 7H12" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17" />
                </svg>
              </div>
              <h3 class="font-bold text-lg mb-2">Innovation</h3>
              <p class="text-blue-200">Nous intégrons les dernières technologies pour rester à la pointe du marché.</p>
            </div>
          </div>
        </div>
        
        <!-- Notre équipe -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 mb-4">
          <div class="flex items-center mb-6">
            <div class="bg-blue-600 rounded-full p-3 mr-4">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857M15 6a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 9a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
            </div>
            <h2 class="text-2xl font-bold">Notre Équipe</h2>
          </div>
          <p class="mb-4">Notre équipe est composée d'experts passionnés par le sport et la technologie. Beaucoup d'entre nous sont des athlètes, ce qui nous permet de comprendre intimement les besoins et les défis auxquels nos clients font face.</p>
          
          <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-4 mt-6">
            <div class="bg-blue-800 rounded-lg overflow-hidden text-center">
              <div class="h-32 bg-blue-600 flex items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 text-blue-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
              <div class="p-4">
                <h3 class="font-bold">Thomas Durand</h3>
                <p class="text-blue-300 text-sm">Fondateur & CEO</p>
              </div>
            </div>
            <div class="bg-blue-800 rounded-lg overflow-hidden text-center">
              <div class="h-32 bg-blue-600 flex items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 text-blue-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
              <div class="p-4">
                <h3 class="font-bold">Sophie Martin</h3>
                <p class="text-blue-300 text-sm">Dir. Marketing</p>
              </div>
            </div>
            <div class="bg-blue-800 rounded-lg overflow-hidden text-center">
              <div class="h-32 bg-blue-600 flex items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 text-blue-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
              <div class="p-4">
                <h3 class="font-bold">Lucas Bernard</h3>
                <p class="text-blue-300 text-sm">Dir. Technique</p>
              </div>
            </div>
            <div class="bg-blue-800 rounded-lg overflow-hidden text-center">
              <div class="h-32 bg-blue-600 flex items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 text-blue-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
              <div class="p-4">
                <h3 class="font-bold">Emma Petit</h3>
                <p class="text-blue-300 text-sm">Dir. Clientèle</p>
              </div>
            </div>
          </div>
        </div>
        
        <!-- Nos valeurs et engagements -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 mb-4">
          <div class="flex items-center mb-6">
            <div class="bg-blue-600 rounded-full p-3 mr-4">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <h2 class="text-2xl font-bold">Nos Engagements</h2>
          </div>
          <div class="bg-blue-800 p-4 rounded-lg mb-6">
            <div class="flex items-center space-x-3 mb-3">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-green-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              <h3 class="font-bold">Développement durable</h3>
            </div>
            <p class="pl-9 text-blue-200">Nous nous engageons à réduire notre empreinte environnementale à travers nos choix de fournisseurs et nos processus internes.</p>
          </div>
          <div class="bg-blue-800 p-4 rounded-lg mb-6">
            <div class="flex items-center space-x-3 mb-3">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-green-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              <h3 class="font-bold">Soutien aux sportifs locaux</h3>
            </div>
            <p class="pl-9 text-blue-200">Nous sponsorisons plusieurs athlètes et clubs locaux pour encourager le développement du sport à tous les niveaux.</p>
          </div>
          <div class="bg-blue-800 p-4 rounded-lg">
            <div class="flex items-center space-x-3 mb-3">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-green-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              <h3 class="font-bold">Commerce équitable</h3>
            </div>
            <p class="pl-9 text-blue-200">Nous travaillons uniquement avec des fabricants qui respectent les conditions de travail et la juste rémunération de leurs employés.</p>
          </div>
        </div>
        
        <!-- Contactez-nous -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6">
          <div class="text-center mb-6">
            <h2 class="text-2xl font-bold">Contactez-nous</h2>
            <p class="text-blue-200">Vous avez des questions? Notre équipe est à votre disposition</p>
          </div>
          <div class="flex flex-col md:flex-row justify-center gap-6">
            <div class="flex items-center bg-blue-800 p-4 rounded-lg">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
              </svg>
              <span>contact@sportelite.com</span>
            </div>
            <div class="flex items-center bg-blue-800 p-4 rounded-lg">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
              </svg>
              <span>+33 (0)1 23 45 67 89</span>
            </div>
            <div class="flex items-center bg-blue-800 p-4 rounded-lg">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
              </svg>
              <span>10 Rue du Sport, 75001 Paris</span>
            </div>
          </div>
        </div>
      </div>
    </main>
</template>
  
<script>
  export default {
    name: 'about'
  }
</script>