<template>
  <main class="min-h-screen bg-gradient-to-br from-blue-900 to-blue-700 text-white">
    <!-- Page Header -->
    <section class="py-12 px-4 bg-blue-800">
      <div class="max-w-7xl mx-auto">
        <h1 class="text-4xl font-bold mb-4">NOS PRODUITS SPORTIFS</h1>
        <p class="text-blue-200 max-w-3xl">
          Découvrez notre sélection d'équipements et d'accessoires de haute qualité pour tous les sportifs, du débutant au professionnel.
        </p>
      </div>
    </section>
    
    <!-- Filter & Sort Section -->
    <section class="bg-blue-800 py-4 shadow-md sticky top-0 z-10">
      <div class="max-w-7xl mx-auto px-4 flex flex-col md:flex-row justify-between items-center gap-4">
        <div class="flex flex-wrap gap-2">
          <button @click="fetchProducts()" class="bg-blue-700 hover:bg-blue-600 px-4 py-2 rounded-lg text-sm font-medium">
            Tous les produits
          </button>
          <button @click="filterProductsByCategory('football')" class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
            Football
          </button>
          <button @click="filterProductsByCategory('basket-ball')" class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
            Basketball
          </button>
          <button @click="filterProductsByCategory('running')" class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
            Running
          </button>
          <button @click="filterProductsByCategory('tennis')" class="bg-blue-900 hover:bg-blue-700 px-4 py-2 rounded-lg text-sm font-medium">
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
          <div v-for="product in products" :key="product.id" class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
            <!-- Si une image est définie, on l'affiche en fond -->
            <div v-if="product.imageUrl" class="aspect-square bg-cover bg-center" :style="{ backgroundImage: 'url(' + product.imageUrl + ')' }"></div>
            <!-- Sinon, on affiche une zone grise -->
            <div v-else class="aspect-square bg-blue-700"></div>
            <div class="p-4">
              <div class="flex justify-between items-start mb-2">
                <!-- Exemple d'étiquette ; à adapter si tes données contiennent un flag "new" -->
                <span v-if="product.new" class="bg-yellow-500 text-black text-xs font-bold px-2 py-1 rounded">NOUVEAU</span>
                <div class="text-yellow-400">★★★★★</div>
              </div>
              <h3 class="font-bold text-lg mb-1">{{ product.name }}</h3>
              <!-- Si le produit comporte une description, on l'affiche -->
              <p v-if="product.description" class="text-blue-200 text-sm mb-3">{{ product.description }}</p>
              <div class="flex justify-between items-center">
                <p class="text-yellow-400 font-bold text-xl">{{ formatPrice(product.price) }}</p>
                <RouterLink :to="`/product/${product.idAsString}`" class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-4 rounded-lg transition duration-300">
                  Ajouter
                </RouterLink>
              </div>
            </div>
          </div>
          <!-- Affiche un message si aucun produit n'est trouvé -->
          <div v-if="products.length === 0" class="col-span-full text-center text-lg">
            Aucun produit trouvé.
          </div>
        </div>
      </div>
    </section>
    
    <!-- Pagination (statique pour l'exemple) -->
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
      products: []
    };
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await axios.get('http://localhost:8080/api/products');
        const rawData = response.data;

        this.products = rawData;

        console.log("Produits récupérés :", this.products);
      } catch (error) {
        console.error('Erreur lors de la récupération des produits:', error);
      }
    },

    async filterProductsByCategory(category) {
      try {
        const response = await axios.get(`http://localhost:8080/api/products/category/${category}`);
        const rawData = response.data;

        this.products = Array.isArray(rawData)
          ? rawData.map(item => typeof item === 'string' ? this.parseRedisProduct(item) : item)
          : [];

        console.log("Produits filtrés par catégorie :", this.products);
      } catch (error) {
        console.error('Erreur lors du filtrage par catégorie:', error);
      }
    },

    formatPrice(price) {
      return new Intl.NumberFormat('fr-FR', { style: 'currency', currency: 'EUR' }).format(price);
    }
  }
}
</script>