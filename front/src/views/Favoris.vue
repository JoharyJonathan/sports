<template>
    <main class="bg-blue-800 min-h-screen text-white">
      <div class="container mx-auto px-4 py-6">
        <!-- En-tête -->
        <header class="mb-8">
          <div class="flex justify-between items-center">
            <div>
              <h1 class="text-3xl font-bold flex items-center">
                <span class="mr-3">❤️</span>
                Mes Favoris
              </h1>
              <p class="text-blue-300 mt-2">{{ favoriteProducts.length }} produit(s) dans vos favoris</p>
            </div>
            <div class="flex space-x-3">
              <button class="bg-blue-700 hover:bg-blue-600 px-4 py-2 rounded-lg flex items-center">
                <span class="mr-2">🔍</span>
                Rechercher
              </button>
              <button class="bg-blue-700 hover:bg-blue-600 px-4 py-2 rounded-lg flex items-center">
                <span class="mr-2">📁</span>
                Filtrer
              </button>
            </div>
          </div>
        </header>
  
        <!-- Filtres rapides -->
        <div class="mb-6">
          <div class="flex flex-wrap gap-3">
            <button 
              v-for="category in categories" 
              :key="category.name"
              @click="selectedCategory = category.name"
              :class="[
                'px-4 py-2 rounded-full text-sm font-medium transition-colors',
                selectedCategory === category.name
                  ? 'bg-green-600 text-white'
                  : 'bg-blue-700 hover:bg-blue-600 text-blue-200'
              ]"
            >
              <span class="mr-1">{{ category.icon }}</span>
              {{ category.name }}
            </button>
          </div>
        </div>
  
        <!-- Liste des favoris -->
        <div v-if="filteredProducts.length > 0" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          <div 
            v-for="product in filteredProducts" 
            :key="product.id"
            class="bg-blue-900 rounded-lg shadow-lg overflow-hidden hover:shadow-xl transition-shadow duration-300"
          >
            <!-- Image du produit -->
            <div class="relative">
              <img 
                :src="product.image" 
                :alt="product.name"
                class="w-full h-48 object-cover"
              >
              <button 
                @click="removeFromFavorites(product.idAsString)"
                class="absolute top-3 right-3 w-8 h-8 bg-red-500 hover:bg-red-600 rounded-full flex items-center justify-center transition-colors"
              >
                <span class="text-white">❤️</span>
              </button>
              <div class="absolute top-3 left-3 bg-black bg-opacity-50 px-2 py-1 rounded-lg">
                <span class="text-xs text-white">{{ product.category }}</span>
              </div>
            </div>
  
            <!-- Informations du produit -->
            <div class="p-4">
              <h3 class="text-lg font-semibold mb-2">{{ product.name }}</h3>
              <p class="text-blue-300 text-sm mb-3">{{ product.description }}</p>
              
              <!-- Prix et note -->
              <div class="flex justify-between items-center mb-4">
                <div>
                  <span class="text-2xl font-bold text-green-400">{{ product.price }}€</span>
                  <span v-if="product.oldPrice" class="text-sm text-gray-400 line-through ml-2">{{ product.oldPrice }}€</span>
                </div>
                <div class="flex items-center">
                  <span class="text-yellow-400 mr-1">⭐</span>
                  <span class="text-sm">{{ product.rating }}</span>
                </div>
              </div>
  
              <!-- Badges -->
              <div class="flex flex-wrap gap-2 mb-4">
                <span 
                  v-if="product.isNew" 
                  class="bg-green-500 text-xs px-2 py-1 rounded-full"
                >
                  Nouveau
                </span>
                <span 
                  v-if="product.discount" 
                  class="bg-red-500 text-xs px-2 py-1 rounded-full"
                >
                  -{{ product.discount }}%
                </span>
                <span 
                  v-if="product.inStock" 
                  class="bg-blue-500 text-xs px-2 py-1 rounded-full"
                >
                  En stock
                </span>
              </div>
  
              <!-- Actions -->
              <div class="flex space-x-2">
                <button 
                  @click="addToCart(product.idAsString)"
                  class="flex-1 bg-green-600 hover:bg-green-700 text-white py-2 px-4 rounded-lg font-medium transition-colors"
                >
                  🛒 Ajouter au panier
                </button>
                <button 
                  @click="viewProduct(product.idAsString)"
                  class="bg-blue-600 hover:bg-blue-700 text-white p-2 rounded-lg transition-colors"
                >
                  👁️
                </button>
              </div>
            </div>
          </div>
        </div>
  
        <!-- État vide -->
        <div v-else class="text-center py-16">
          <div class="mb-6">
            <span class="text-6xl">💔</span>
          </div>
          <h2 class="text-2xl font-bold mb-4">Aucun favori pour le moment</h2>
          <p class="text-blue-300 mb-8">
            {{ selectedCategory === 'Tous' ? 'Vous n\'avez pas encore ajouté de produits à vos favoris' : `Aucun produit dans la catégorie "${selectedCategory}"` }}
          </p>
          <button 
            @click="goToShop"
            class="bg-green-600 hover:bg-green-700 text-white font-medium py-3 px-6 rounded-lg transition-colors"
          >
            🏪 Découvrir nos produits
          </button>
        </div>
  
        <!-- Actions en bas -->
        <div class="mt-12 flex justify-center space-x-4">
          <button 
            v-if="favoriteProducts.length > 0"
            @click="clearAllFavorites"
            class="bg-red-600 hover:bg-red-700 text-white font-medium py-3 px-6 rounded-lg transition-colors"
          >
            🗑️ Vider les favoris
          </button>
          <button 
            @click="exportFavorites"
            class="bg-blue-600 hover:bg-blue-700 text-white font-medium py-3 px-6 rounded-lg transition-colors"
          >
            📤 Exporter la liste
          </button>
        </div>
      </div>
    </main>
</template>
  
<script>
  import axios from 'axios';
  import { jwtDecode } from 'jwt-decode';

  export default {
    name: "Favoris",
    data() {
      return {
        selectedCategory: 'Tous',
        categories: [
          { name: 'Tous', icon: '🏆' },
          { name: 'Équipements', icon: '🏋️' },
          { name: 'Vêtements', icon: '👕' },
          { name: 'Chaussures', icon: '👟' },
          { name: 'Accessoires', icon: '🎯' },
          { name: 'Nutrition', icon: '🥤' }
        ],
        favoriteProducts: []
      }
    },
    created() {
      this.fetchFavorites();
    },
    computed: {
      filteredProducts() {
        if (this.selectedCategory === 'Tous') {
          return this.favoriteProducts;
        }
        return this.favoriteProducts.filter(product => product.category === this.selectedCategory);
      }
    },
    methods: {
      async removeFromFavorites(productId) {
        const token = localStorage.getItem('token');
          
        if (!token) {
          this.$router.push('/login');
          throw new Error('Veuillez vous connecter');
        }

        const decoded = jwtDecode(token);
        console.log('Token décodé :', decoded);
          
        // Vérification de l'expiration du token
        if (decoded.exp * 1000 < Date.now()) {
          localStorage.removeItem('token');
          this.$router.push('/login');
          throw new Error('Session expirée');
        }

        const userId = decoded.sub;

        this.favoriteProducts = this.favoriteProducts.filter(product => product.idAsString !== productId);
        try {
          const response = await axios.delete(`http://localhost:8080/api/favorites/remove?userId=${userId}&productId=${productId}`);

          console.log(response.data);
        } catch (error) {
          console.error('Error removing item from favorite : ', error);
        }
        console.log(`Produit ${productId} retiré des favoris`);
      },
      async addToCart(productId) {
        try {
          const token = localStorage.getItem('token');
          
          if (!token) {
            this.$router.push('/login');
            throw new Error('Veuillez vous connecter');
          }

          const decoded = jwtDecode(token);
          console.log('Token décodé :', decoded);
          
          // Vérification de l'expiration du token
          if (decoded.exp * 1000 < Date.now()) {
            localStorage.removeItem('token');
            this.$router.push('/login');
            throw new Error('Session expirée');
          }

          this.userId = decoded.sub;

          const payload = {
            productId: productId,
            quantity: 1
          };

          const response = await axios.post(
            `http://localhost:8080/api/carts/${this.userId}`,
            payload,
            {
              headers: {
                Authorization: `Bearer ${token}`
              }
            }
          );

          alert('Produit ajouté au panier !');
          console.log('Réponse du panier :', response.data);

          this.$router.push(`/cart/${this.userId}`);
          
        } catch (error) {
          console.error('Erreur panier :', error.response?.data || error.message);
          alert(error.response?.data?.message || error.message);
        }
      },
      viewProduct(productId) {
        this.$router.push(`/product/${productId}`);
      },
      goToShop() {
        this.$router.push('/products');
      },
      clearAllFavorites() {
        if (confirm('Êtes-vous sûr de vouloir vider tous vos favoris ?')) {
          this.favoriteProducts = [];
          console.log('Tous les favoris ont été supprimés');
        }
      },
      exportFavorites() {
        // Logique pour exporter la liste
        console.log('Export de la liste des favoris...');
      },
      async fetchFavorites() {
        const userId = this.$route.params.id;

        try {
          const response = await axios.get(`http://localhost:8080/api/favorites/user/${userId}`);


          const favorites = response.data;

          const products = favorites.map(fav => fav.product);

          this.favoriteProducts = products;
          console.log(this.favoriteProducts);

          // Récupérer tous les IDs
          const productIds = products.map(p => p.idAsString);
          console.log("IDs des produits favoris :", productIds);
        } catch (error) {
          console.error('Error fetching favorites products ! ', error);
        }
      },
      async getProduct(productId) {
        try {
          const response = await axios.get(`http://localhost:8080/api/products/${productId}`);

          console.log(response.data);
        } catch (error) {
          console.error('Error getting product : ', error);
        }
      }
    }
  }
</script>