<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-900 to-blue-700 text-white">
      <!-- Hero Section -->
      <section class="relative h-96 flex items-center justify-center overflow-hidden">
        <div class="absolute inset-0 bg-black opacity-40"></div>
        <div class="relative z-10 text-center px-4">
          <h1 class="text-5xl font-extrabold mb-4 tracking-tight">SPORTS ELITE</h1>
          <p class="text-xl text-gray-200 max-w-xl mx-auto mb-2.5">Dépassez vos limites et atteignez l'excellence dans votre discipline</p>
          <RouterLink to="/products" class="mt-8 bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-3 px-8 rounded-full transition duration-300 transform hover:scale-105">
            COMMENCER MAINTENANT
          </Routerlink>
        </div>
      </section>
  
      <!-- Featured Categories -->
      <section class="py-16 px-4 max-w-7xl mx-auto">
        <h2 class="text-3xl font-bold text-center mb-12">DISCIPLINES POPULAIRES</h2>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transform transition duration-300 hover:scale-105">
            <div class="h-48 bg-blue-600"></div>
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">Football</h3>
              <p class="text-blue-200 mb-4">Équipement, entraînement et conseils pour passionnés de football</p>
              <RouterLink to="/products/category/foot-ball" class="text-yellow-400 font-semibold hover:text-yellow-300">Découvrir →</RouterLink>
            </div>
          </div>
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transform transition duration-300 hover:scale-105">
            <div class="h-48 bg-blue-600"></div>
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">Basketball</h3>
              <p class="text-blue-200 mb-4">Tout pour améliorer votre jeu sur le terrain de basket</p>
              <RouterLink to="/products/category/basket-ball" class="text-yellow-400 font-semibold hover:text-yellow-300">Découvrir →</RouterLink>
            </div>
          </div>
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transform transition duration-300 hover:scale-105">
            <div class="h-48 bg-blue-600"></div>
            <div class="p-6">
              <h3 class="text-xl font-bold mb-2">Running</h3>
              <p class="text-blue-200 mb-4">Chaussures, vêtements et accessoires pour la course à pied</p>
              <RouterLink to="/products/category/running" class="text-yellow-400 font-semibold hover:text-yellow-300">Découvrir →</RouterLink>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Featured Products -->
      <section class="py-16 bg-blue-800 px-4">
        <div class="max-w-7xl mx-auto">
          <h2 class="text-3xl font-bold text-center mb-12">PRODUITS À LA UNE</h2>
          <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
            <div class="bg-blue-700 rounded-lg p-4 shadow-md transform transition duration-300 hover:shadow-xl">
              <div class="h-40 bg-blue-600 rounded mb-4"></div>
              <h3 class="font-bold mb-2">Ballon de football Pro</h3>
              <p class="text-yellow-400 font-bold mb-2">89,99 €</p>
              <button class="w-full bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 rounded">
                Ajouter au panier
              </button>
            </div>
            <div class="bg-blue-700 rounded-lg p-4 shadow-md transform transition duration-300 hover:shadow-xl">
              <div class="h-40 bg-blue-600 rounded mb-4"></div>
              <h3 class="font-bold mb-2">Maillot équipe nationale</h3>
              <p class="text-yellow-400 font-bold mb-2">120,00 €</p>
              <button class="w-full bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 rounded">
                Ajouter au panier
              </button>
            </div>
            <div class="bg-blue-700 rounded-lg p-4 shadow-md transform transition duration-300 hover:shadow-xl">
              <div class="h-40 bg-blue-600 rounded mb-4"></div>
              <h3 class="font-bold mb-2">Chaussures running</h3>
              <p class="text-yellow-400 font-bold mb-2">149,99 €</p>
              <button class="w-full bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 rounded">
                Ajouter au panier
              </button>
            </div>
            <div class="bg-blue-700 rounded-lg p-4 shadow-md transform transition duration-300 hover:shadow-xl">
              <div class="h-40 bg-blue-600 rounded mb-4"></div>
              <h3 class="font-bold mb-2">Raquette de tennis Pro</h3>
              <p class="text-yellow-400 font-bold mb-2">199,99 €</p>
              <button class="w-full bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 rounded">
                Ajouter au panier
              </button>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Testimonials -->
      <section class="py-16 px-4 max-w-7xl mx-auto">
        <h2 class="text-3xl font-bold text-center mb-12">CE QUE DISENT NOS CLIENTS</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
          <div class="bg-gradient-to-r from-blue-800 to-blue-700 p-6 rounded-lg shadow-lg">
            <div class="flex items-center mb-4">
              <div class="h-12 w-12 rounded-full bg-blue-600 mr-4"></div>
              <div>
                <p class="font-bold">Thomas D.</p>
                <div class="flex text-yellow-400">
                  <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                </div>
              </div>
            </div>
            <p class="text-blue-100">"Excellente qualité des produits et livraison rapide. Je recommande vivement pour tous les sportifs passionnés."</p>
          </div>
          <div class="bg-gradient-to-r from-blue-800 to-blue-700 p-6 rounded-lg shadow-lg">
            <div class="flex items-center mb-4">
              <div class="h-12 w-12 rounded-full bg-blue-600 mr-4"></div>
              <div>
                <p class="font-bold">Marie L.</p>
                <div class="flex text-yellow-400">
                  <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                </div>
              </div>
            </div>
            <p class="text-blue-100">"Le service client est impeccable et les produits sont conformes aux descriptions. Je suis cliente depuis 3 ans maintenant."</p>
          </div>
        </div>
      </section>
  
      <!-- Call to Action -->
      <section class="py-16 px-4 bg-gradient-to-r from-yellow-500 to-yellow-600 text-center">
        <div class="max-w-3xl mx-auto">
          <h2 class="text-3xl font-bold text-black mb-4">PRÊT À AMÉLIORER VOS PERFORMANCES?</h2>
          <p class="text-black text-lg mb-8">Inscrivez-vous à notre newsletter pour recevoir des conseils d'experts et des offres exclusives</p>
          <div class="flex flex-col sm:flex-row gap-4 justify-center">
            <input type="email" placeholder="Votre adresse email" class="px-4 py-3 rounded-lg w-full sm:w-64 text-black" />
            <button class="bg-blue-900 hover:bg-blue-800 text-white font-bold py-3 px-6 rounded-lg transition duration-300">
              S'INSCRIRE
            </button>
          </div>
        </div>
      </section>
    </main>
</template>
  
<script>
import { RouterLink } from 'vue-router';

  export default {
    name: "Home"
  }
</script>