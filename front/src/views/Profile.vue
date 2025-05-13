<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-800 to-blue-900 text-white p-8">
      <div class="max-w-4xl mx-auto">
        <!-- En-tête du profil -->
        <div class="bg-blue-700 rounded-t-lg shadow-lg p-6 mb-4 flex items-center space-x-6">
          <div class="w-24 h-24 rounded-full bg-blue-500 flex items-center justify-center border-4 border-white">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
            </svg>
          </div>
          <div>
            <h1 class="text-3xl font-bold">Profil Sportif</h1>
            <p class="text-blue-200">Athlète • Passionné • Compétiteur</p>
          </div>
        </div>
        
        <!-- Statistiques -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 mb-4">
          <h2 class="text-xl font-bold mb-4 border-b border-blue-600 pb-2">Statistiques</h2>
          <div class="grid grid-cols-3 gap-4 text-center">
            <div class="bg-blue-800 rounded-lg p-4">
              <p class="text-sm text-blue-300">Séances</p>
              <p class="text-3xl font-bold">124</p>
            </div>
            <div class="bg-blue-800 rounded-lg p-4">
              <p class="text-sm text-blue-300">Points</p>
              <p class="text-3xl font-bold">1,546</p>
            </div>
            <div class="bg-blue-800 rounded-lg p-4">
              <p class="text-sm text-blue-300">Niveau</p>
              <p class="text-3xl font-bold">8</p>
            </div>
          </div>
        </div>
        
        <!-- Progrès -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 mb-4">
          <h2 class="text-xl font-bold mb-4 border-b border-blue-600 pb-2">Progrès</h2>
          <div class="space-y-4">
            <div>
              <div class="flex justify-between mb-1">
                <span>Endurance</span>
                <span>75%</span>
              </div>
              <div class="w-full bg-blue-800 rounded-full h-2.5">
                <div class="bg-green-500 h-2.5 rounded-full" style="width: 75%"></div>
              </div>
            </div>
            <div>
              <div class="flex justify-between mb-1">
                <span>Force</span>
                <span>60%</span>
              </div>
              <div class="w-full bg-blue-800 rounded-full h-2.5">
                <div class="bg-yellow-500 h-2.5 rounded-full" style="width: 60%"></div>
              </div>
            </div>
            <div>
              <div class="flex justify-between mb-1">
                <span>Agilité</span>
                <span>85%</span>
              </div>
              <div class="w-full bg-blue-800 rounded-full h-2.5">
                <div class="bg-red-500 h-2.5 rounded-full" style="width: 85%"></div>
              </div>
            </div>
          </div>
        </div>
        
        <!-- Activités récentes -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6">
          <h2 class="text-xl font-bold mb-4 border-b border-blue-600 pb-2">Activités récentes</h2>
          <ul class="space-y-3">
            <li class="bg-blue-800 bg-opacity-50 p-3 rounded flex justify-between">
              <span>Course à pied</span>
              <span>5,2 km</span>
            </li>
            <li class="bg-blue-800 bg-opacity-50 p-3 rounded flex justify-between">
              <span>Musculation</span>
              <span>45 min</span>
            </li>
            <li class="bg-blue-800 bg-opacity-50 p-3 rounded flex justify-between">
              <span>Natation</span>
              <span>1000 m</span>
            </li>
          </ul>
        </div>
      </div>
    </main>
</template>
  
<script>
  export default {
    name: "profile"
  }
</script>