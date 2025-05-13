<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-900 to-blue-700 text-white">
      <!-- Page Header -->
      <section class="py-12 px-4 bg-blue-800">
        <div class="max-w-7xl mx-auto">
          <h1 class="text-4xl font-bold mb-4">NOS PRODUITS SPORTIFS</h1>
          <p class="text-blue-200 max-w-3xl">Découvrez notre sélection d'équipements et d'accessoires de haute qualité pour tous les sportifs, du débutant au professionnel.</p>
        </div>
      </section>
  
      <!-- Filter & Sort Section -->
      <section class="bg-blue-800 py-4 shadow-md sticky top-0 z-10">
        <div class="max-w-7xl mx-auto px-4 flex flex-col md:flex-row justify-between items-center gap-4">
          <div class="flex flex-wrap gap-2">
            <button class="bg-blue-700 hover:bg-blue-600 px-4 py-2 rounded-lg text-sm font-medium">
              Tous les produits
            </button>
            <button class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
              Football
            </button>
            <button class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
              Basketball
            </button>
            <button class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
              Running
            </button>
            <button class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
              Tennis
            </button>
          </div>
          <div class="flex items-center gap-2 w-full md:w-auto">
            <label class="text-sm whitespace-nowrap">Trier par:</label>
            <select class="bg-blue-900 border border-blue-700 rounded-lg px-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-yellow-500 w-full md:w-auto">
              <option>Popularité</option>
              <option>Prix croissant</option>
              <option>Prix décroissant</option>
              <option>Nouveautés</option>
            </select>
          </div>
        </div>
      </section>
  
      <!-- Products Grid -->  
      <section class="py-12 px-4">
        <div class="max-w-7xl mx-auto">
          <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            <!-- Product 1 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="bg-yellow-500 text-black text-xs font-bold px-2 py-1 rounded">NOUVEAU</span>
                  <div class="text-yellow-400">★★★★★</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Ballon de football Pro</h3>
                <p class="text-blue-200 text-sm mb-3">Ballon officiel qualité match, conçu pour les performances professionnelles</p>
                <div class="flex justify-between items-center">
                  <p class="text-yellow-400 font-bold text-xl">89,99 €</p>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Product 2 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="bg-red-500 text-white text-xs font-bold px-2 py-1 rounded">-20%</span>
                  <div class="text-yellow-400">★★★★☆</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Maillot équipe nationale</h3>
                <p class="text-blue-200 text-sm mb-3">Maillot officiel respirant avec technologie anti-transpiration</p>
                <div class="flex justify-between items-center">
                  <div>
                    <p class="text-red-400 line-through text-sm">150,00 €</p>
                    <p class="text-yellow-400 font-bold text-xl">120,00 €</p>
                  </div>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Product 3 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="invisible"></span>
                  <div class="text-yellow-400">★★★★★</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Chaussures running performance</h3>
                <p class="text-blue-200 text-sm mb-3">Conçues pour offrir un soutien optimal et un maximum de confort</p>
                <div class="flex justify-between items-center">
                  <p class="text-yellow-400 font-bold text-xl">149,99 €</p>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Product 4 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="invisible"></span>
                  <div class="text-yellow-400">★★★★☆</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Raquette de tennis Pro</h3>
                <p class="text-blue-200 text-sm mb-3">Raquette professionnelle légère avec équilibre parfait</p>
                <div class="flex justify-between items-center">
                  <p class="text-yellow-400 font-bold text-xl">199,99 €</p>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Product 5 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="bg-green-500 text-white text-xs font-bold px-2 py-1 rounded">ÉCORESPONSABLE</span>
                  <div class="text-yellow-400">★★★★☆</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Gourde isotherme 1L</h3>
                <p class="text-blue-200 text-sm mb-3">Gourde en acier inoxydable, maintient les boissons froides pendant 24h</p>
                <div class="flex justify-between items-center">
                  <p class="text-yellow-400 font-bold text-xl">29,99 €</p>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Product 6 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="invisible"></span>
                  <div class="text-yellow-400">★★★★★</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Short de basketball</h3>
                <p class="text-blue-200 text-sm mb-3">Short léger et respirant pour une liberté de mouvement maximale</p>
                <div class="flex justify-between items-center">
                  <p class="text-yellow-400 font-bold text-xl">44,99 €</p>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Product 7 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="bg-purple-500 text-white text-xs font-bold px-2 py-1 rounded">BEST-SELLER</span>
                  <div class="text-yellow-400">★★★★★</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Montre GPS multisport</h3>
                <p class="text-blue-200 text-sm mb-3">Suivi avancé de la performance avec 20 profils sportifs</p>
                <div class="flex justify-between items-center">
                  <p class="text-yellow-400 font-bold text-xl">249,99 €</p>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Product 8 -->
            <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
              <div class="aspect-square bg-blue-700"></div>
              <div class="p-4">
                <div class="flex justify-between items-start mb-2">
                  <span class="bg-red-500 text-white text-xs font-bold px-2 py-1 rounded">-15%</span>
                  <div class="text-yellow-400">★★★★☆</div>
                </div>
                <h3 class="font-bold text-lg mb-1">Tapis de yoga premium</h3>
                <p class="text-blue-200 text-sm mb-3">Tapis antidérapant écologique avec marquage d'alignement</p>
                <div class="flex justify-between items-center">
                  <div>
                    <p class="text-red-400 line-through text-sm">59,99 €</p>
                    <p class="text-yellow-400 font-bold text-xl">50,99 €</p>
                  </div>
                  <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                    Ajouter
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Pagination -->
      <section class="pb-16 px-4">
        <div class="max-w-7xl mx-auto flex justify-center">
          <div class="flex items-center gap-2">
            <button class="w-10 h-10 flex items-center justify-center rounded-lg bg-blue-800 hover:bg-blue-700">
              &laquo;
            </button>
            <button class="w-10 h-10 flex items-center justify-center rounded-lg bg-yellow-500 text-black font-bold">
              1
            </button>
            <button class="w-10 h-10 flex items-center justify-center rounded-lg bg-blue-800 hover:bg-blue-700">
              2
            </button>
            <button class="w-10 h-10 flex items-center justify-center rounded-lg bg-blue-800 hover:bg-blue-700">
              3
            </button>
            <button class="w-10 h-10 flex items-center justify-center rounded-lg bg-blue-800 hover:bg-blue-700">
              &raquo;
            </button>
          </div>
        </div>
      </section>
  
      <!-- Newsletter -->
      <section class="py-12 px-4 bg-gradient-to-r from-yellow-500 to-yellow-600 text-center">
        <div class="max-w-3xl mx-auto">
          <h2 class="text-2xl font-bold text-black mb-4">RECEVEZ NOS OFFRES EXCLUSIVES</h2>
          <p class="text-black mb-6">Inscrivez-vous pour être informé des nouveautés et promotions</p>
          <div class="flex flex-col sm:flex-row gap-4 justify-center">
            <input type="email" placeholder="Votre adresse email" class="px-4 py-2 rounded-lg w-full sm:w-64 text-black" />
            <button class="bg-blue-900 hover:bg-blue-800 text-white font-bold py-2 px-6 rounded-lg transition duration-300">
              S'INSCRIRE
            </button>
          </div>
        </div>
      </section>
    </main>
</template>
  
<script>
  import axios from 'axios';

  export default {
    name: "Products",
    data() {
      return {
        products: null,
      };
    },
    created() {
      this.fetchProducts();
    },
    methods: {
      async fetchProducts() {
        try {
          const response = await axios.get('http://localhost:8080/api/products');
          this.products = response.data;
          console.log(this.products);
        } catch (error) {
          console.error('Error fetching products : ', error);
        }
      }
    }
  }
</script>