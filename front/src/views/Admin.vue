<template>
    <main class="bg-blue-800 min-h-screen text-white">
      <div class="container mx-auto px-4 py-6">
        <!-- Entête -->
        <header class="flex justify-between items-center mb-8">
          <div class="flex items-center">
            <h1 class="text-2xl font-bold">Dashboard Admin Sport</h1>
          </div>
          <div class="flex items-center space-x-4">
            <button class="bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded-lg flex items-center">
              <span>Notifications</span>
              <span class="ml-2 bg-red-500 text-xs rounded-full px-2 py-1">3</span>
            </button>
            <div class="flex items-center">
              <div class="w-8 h-8 rounded-full bg-gray-300 mr-2"></div>
              <span>Admin</span>
            </div>
          </div>
        </header>
  
        <!-- Statistiques -->
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
          <div class="bg-blue-700 rounded-lg p-4 shadow-lg">
            <h3 class="text-lg font-medium mb-2">Membres</h3>
            <p class="text-3xl font-bold">1,245</p>
            <p class="text-blue-300 text-sm">+12% cette semaine</p>
          </div>
          <div class="bg-blue-700 rounded-lg p-4 shadow-lg">
            <h3 class="text-lg font-medium mb-2">Événements</h3>
            <p class="text-3xl font-bold">24</p>
            <p class="text-blue-300 text-sm">8 à venir</p>
          </div>
          <div class="bg-blue-700 rounded-lg p-4 shadow-lg">
            <h3 class="text-lg font-medium mb-2">Revenus</h3>
            <p class="text-3xl font-bold">9,854€</p>
            <p class="text-blue-300 text-sm">+5% ce mois</p>
          </div>
        </div>
  
        <!-- Contenu principal -->
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
          <!-- Liste des équipes -->
          <div class="lg:col-span-2 bg-blue-900 rounded-lg shadow-lg p-6">
            <div class="flex justify-between items-center mb-4">
              <h2 class="text-xl font-bold">Équipes</h2>
              <button class="bg-green-600 hover:bg-green-700 px-3 py-1 rounded-lg text-sm">Ajouter</button>
            </div>
            <div class="overflow-x-auto">
              <table class="w-full">
                <thead>
                  <tr class="border-b border-blue-700">
                    <th class="py-2 text-left">Équipe</th>
                    <th class="py-2 text-left">Sport</th>
                    <th class="py-2 text-left">Membres</th>
                    <th class="py-2 text-left">Statut</th>
                    <th class="py-2 text-left">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <tr class="border-b border-blue-700">
                    <td class="py-3">FC Thunder</td>
                    <td class="py-3">Football</td>
                    <td class="py-3">22</td>
                    <td class="py-3"><span class="bg-green-500 text-xs px-2 py-1 rounded-full">Actif</span></td>
                    <td class="py-3 flex space-x-2">
                      <button class="text-blue-300 hover:text-blue-100">Éditer</button>
                      <button class="text-red-400 hover:text-red-300">Supprimer</button>
                    </td>
                  </tr>
                  <tr class="border-b border-blue-700">
                    <td class="py-3">Sharks Basket</td>
                    <td class="py-3">Basketball</td>
                    <td class="py-3">15</td>
                    <td class="py-3"><span class="bg-green-500 text-xs px-2 py-1 rounded-full">Actif</span></td>
                    <td class="py-3 flex space-x-2">
                      <button class="text-blue-300 hover:text-blue-100">Éditer</button>
                      <button class="text-red-400 hover:text-red-300">Supprimer</button>
                    </td>
                  </tr>
                  <tr class="border-b border-blue-700">
                    <td class="py-3">Royal Tennis</td>
                    <td class="py-3">Tennis</td>
                    <td class="py-3">8</td>
                    <td class="py-3"><span class="bg-yellow-500 text-xs px-2 py-1 rounded-full">En pause</span></td>
                    <td class="py-3 flex space-x-2">
                      <button class="text-blue-300 hover:text-blue-100">Éditer</button>
                      <button class="text-red-400 hover:text-red-300">Supprimer</button>
                    </td>
                  </tr>
                  <tr>
                    <td class="py-3">Cyclone Natation</td>
                    <td class="py-3">Natation</td>
                    <td class="py-3">12</td>
                    <td class="py-3"><span class="bg-red-500 text-xs px-2 py-1 rounded-full">Inactif</span></td>
                    <td class="py-3 flex space-x-2">
                      <button class="text-blue-300 hover:text-blue-100">Éditer</button>
                      <button class="text-red-400 hover:text-red-300">Supprimer</button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
  
          <!-- Calendrier -->
          <div class="bg-blue-900 rounded-lg shadow-lg p-6">
            <h2 class="text-xl font-bold mb-4">Événements à venir</h2>
            <div class="space-y-4">
              <div class="bg-blue-800 p-3 rounded-lg">
                <div class="flex justify-between mb-1">
                  <p class="font-medium">Tournoi régional</p>
                  <span class="text-blue-300 text-sm">18 Mai</span>
                </div>
                <p class="text-sm text-blue-300">FC Thunder vs Real Stars</p>
              </div>
              <div class="bg-blue-800 p-3 rounded-lg">
                <div class="flex justify-between mb-1">
                  <p class="font-medium">Session d'entraînement</p>
                  <span class="text-blue-300 text-sm">20 Mai</span>
                </div>
                <p class="text-sm text-blue-300">Sharks Basket</p>
              </div>
              <div class="bg-blue-800 p-3 rounded-lg">
                <div class="flex justify-between mb-1">
                  <p class="font-medium">Compétition locale</p>
                  <span class="text-blue-300 text-sm">23 Mai</span>
                </div>
                <p class="text-sm text-blue-300">Cyclone Natation</p>
              </div>
              <button class="w-full mt-4 bg-blue-700 hover:bg-blue-600 py-2 rounded-lg text-sm">Voir tous les événements</button>
            </div>
          </div>
        </div>
  
        <!-- Menu latéral -->
        <div class="fixed left-0 top-0 h-full bg-blue-900 w-16 flex flex-col items-center py-8 hidden lg:flex">
          <div class="w-10 h-10 bg-white rounded-full mb-10 flex items-center justify-center text-blue-800 font-bold">S</div>
          <div class="space-y-8">
            <div class="w-10 h-10 bg-blue-700 rounded-lg flex items-center justify-center cursor-pointer">
              <span>📊</span>
            </div>
            <div class="w-10 h-10 hover:bg-blue-700 rounded-lg flex items-center justify-center cursor-pointer">
              <span>👥</span>
            </div>
            <div class="w-10 h-10 hover:bg-blue-700 rounded-lg flex items-center justify-center cursor-pointer">
              <span>🏆</span>
            </div>
            <div class="w-10 h-10 hover:bg-blue-700 rounded-lg flex items-center justify-center cursor-pointer">
              <span>📅</span>
            </div>
            <div class="w-10 h-10 hover:bg-blue-700 rounded-lg flex items-center justify-center cursor-pointer">
              <span>⚙️</span>
            </div>
          </div>
        </div>
      </div>
    </main>
</template>
  
<script>
  export default {
    name: "admin",
    data() {
      return {
        // Vous pouvez ajouter des données ici selon vos besoins
      }
    },
    methods: {
      // Vous pouvez ajouter des méthodes ici selon vos besoins
    }
  }
</script>