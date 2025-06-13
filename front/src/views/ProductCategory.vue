<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-800 via-blue-700 to-blue-900">
      <!-- Header avec background pattern -->
      <div class="relative overflow-hidden bg-gradient-to-r from-blue-900 to-blue-800 shadow-2xl">
        <!-- Pattern décoratif -->
        <div class="absolute inset-0 opacity-10">
          <div class="absolute top-0 left-0 w-full h-full bg-repeat"></div>
        </div>
        
        <div class="relative container mx-auto px-6 py-12">
          <!-- Breadcrumb -->
          <nav class="mb-6">
            <ol class="flex items-center space-x-2 text-blue-200">
              <li>
                <router-link to="/" class="hover:text-white transition-colors">
                  <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"/>
                  </svg>
                </router-link>
              </li>
              <li class="text-blue-300">/</li>
              <li class="text-white font-medium capitalize">{{ categoryName }}</li>
            </ol>
          </nav>
  
          <!-- Title Section -->
          <div class="text-center">
            <h1 class="text-4xl md:text-6xl font-bold text-white mb-4 tracking-tight">
              {{ categoryTitle }}
            </h1>
            <p class="text-xl text-blue-200 max-w-2xl mx-auto">
              Découvrez notre sélection premium d'équipements de {{ categoryName }}
            </p>
            
            <!-- Stats -->
            <div class="flex justify-center items-center mt-8 space-x-8">
              <div class="text-center">
                <div class="text-3xl font-bold text-yellow-400">{{ products?.length || 0 }}</div>
                <div class="text-blue-200 text-sm">Produits</div>
              </div>
              <div class="w-px h-12 bg-blue-600"></div>
              <div class="text-center">
                <div class="text-3xl font-bold text-yellow-400">{{ avgPrice }}€</div>
                <div class="text-blue-200 text-sm">Prix moyen</div>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Loading State -->
      <div v-if="!products" class="container mx-auto px-6 py-12">
        <div class="flex justify-center items-center py-20">
          <div class="animate-spin rounded-full h-16 w-16 border-4 border-white border-t-transparent"></div>
        </div>
      </div>
  
      <!-- Empty State -->
      <div v-else-if="products.length === 0" class="container mx-auto px-6 py-12">
        <div class="text-center py-20">
          <div class="text-6xl mb-4">🏃‍♂️</div>
          <h2 class="text-2xl font-bold text-white mb-4">Aucun produit trouvé</h2>
          <p class="text-blue-200">Cette catégorie ne contient pas encore de produits.</p>
        </div>
      </div>
  
      <!-- Products Grid -->
      <div v-else class="container mx-auto px-6 py-12">
        <!-- Filters Bar -->
        <div class="mb-8 flex flex-wrap items-center justify-between gap-4 bg-white/10 backdrop-blur-sm rounded-xl p-4">
          <div class="flex items-center space-x-4">
            <label class="text-white font-medium">Trier par :</label>
            <select 
              v-model="sortBy" 
              @change="sortProducts"
              class="bg-blue-800 text-white px-4 py-2 rounded-lg border border-blue-600 focus:ring-2 focus:ring-yellow-400 focus:border-transparent"
            >
              <option value="name">Nom</option>
              <option value="price-asc">Prix croissant</option>
              <option value="price-desc">Prix décroissant</option>
              <option value="stock">Stock</option>
            </select>
          </div>
          
          <div class="flex items-center space-x-2 text-white">
            <span>{{ filteredProducts.length }} produit{{ filteredProducts.length > 1 ? 's' : '' }}</span>
          </div>
        </div>
  
        <!-- Products Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8">
          <div 
            v-for="product in filteredProducts" 
            :key="product.idAsString"
            class="group bg-white rounded-2xl overflow-hidden shadow-xl hover:shadow-2xl transition-all duration-300 transform hover:-translate-y-2 hover:scale-105"
          >
            <!-- Image Container -->
            <div class="relative overflow-hidden bg-gradient-to-br from-gray-100 to-gray-200 h-64">
              <img 
                :src="product.imageUrl" 
                :alt="product.name"
                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
                @error="handleImageError"
              >
              
              <!-- Stock Badge -->
              <div class="absolute top-4 left-4">
                <span 
                  class="px-3 py-1 rounded-full text-xs font-bold shadow-lg"
                  :class="product.stockQuantity > 10 ? 'bg-green-500 text-white' : product.stockQuantity > 0 ? 'bg-yellow-500 text-black' : 'bg-red-500 text-white'"
                >
                  {{ product.stockQuantity > 0 ? `${product.stockQuantity} en stock` : 'Rupture' }}
                </span>
              </div>
  
              <!-- Price Badge -->
              <div class="absolute top-4 right-4 bg-blue-900 text-yellow-400 px-3 py-1 rounded-full text-lg font-bold shadow-lg">
                {{ product.price.toFixed(2) }}€
              </div>
  
              <!-- Quick Actions -->
              <div class="absolute inset-0 bg-black/50 opacity-0 group-hover:opacity-100 transition-opacity duration-300 flex items-center justify-center">
                <div class="space-x-2">
                  <RouterLink :to="`/product/${product.idAsString}`" class="bg-white text-blue-900 px-4 py-2 rounded-lg font-bold hover:bg-yellow-400 transition-colors">
                    Voir détails
                  </RouterLink>
                  <button
                    @click="addOneToCart(product.idAsString)"
                    :disabled="product.stockQuantity === 0"
                    class="bg-yellow-400 text-blue-900 px-4 py-2 rounded-lg font-bold hover:bg-yellow-300 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                  >
                    Ajouter au panier
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Content -->
            <div class="p-6">
              <h3 class="font-bold text-xl text-gray-800 mb-2 group-hover:text-blue-700 transition-colors line-clamp-2">
                {{ product.name }}
              </h3>
              
              <p class="text-gray-600 text-sm mb-4 line-clamp-3">
                {{ product.description }}
              </p>
  
              <!-- Features -->
              <div class="flex items-center justify-between">
                <div class="flex items-center space-x-2">
                  <svg class="w-4 h-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"/>
                  </svg>
                  <span class="text-xs text-gray-500">Qualité premium</span>
                </div>
                
                <div class="flex items-center space-x-1">
                  <div class="flex text-yellow-400">
                    <svg v-for="i in 5" :key="i" class="w-3 h-3 fill-current" viewBox="0 0 20 20">
                      <path d="M10 15l-5.878 3.09 1.123-6.545L.489 6.91l6.572-.955L10 0l2.939 5.955 6.572.955-4.756 4.635 1.123 6.545z"/>
                    </svg>
                  </div>
                  <span class="text-xs text-gray-500 ml-1">(4.8)</span>
                </div>
              </div>
            </div>
          </div>
        </div>
  
        <!-- Load More Button -->
        <div v-if="showLoadMore" class="text-center mt-12">
          <button class="bg-white text-blue-900 px-8 py-3 rounded-xl font-bold hover:bg-yellow-400 transition-colors shadow-lg">
            Charger plus de produits
          </button>
        </div>
      </div>
    </main>
</template>
  
<script>
  import axios from 'axios';
  import { jwtDecode } from 'jwt-decode';
  
  export default {
    name: 'category-view',
    data() {
      return {
        userId: null,
        products: null,
        sortBy: 'name',
        showLoadMore: false
      }
    },
    computed: {
      categoryName() {
        return this.$route.params.category || 'produits';
      },
      categoryTitle() {
        const titles = {
          tennis: '🎾 Tennis',
          football: '⚽ Football', 
          basketball: '🏀 Basketball',
          running: '🏃‍♂️ Running',
          fitness: '💪 Fitness',
          natation: '🏊‍♂️ Natation'
        };
        return titles[this.categoryName] || `🏃‍♂️ ${this.categoryName.charAt(0).toUpperCase() + this.categoryName.slice(1)}`;
      },
      avgPrice() {
        if (!this.products || this.products.length === 0) return 0;
        const total = this.products.reduce((sum, product) => sum + product.price, 0);
        return (total / this.products.length).toFixed(0);
      },
      filteredProducts() {
        if (!this.products) return [];
        return [...this.products].sort(this.getSortFunction());
      }
    },
    created() {
      this.getProducts();
    },
    watch: {
      '$route.params.category'() {
        this.getProducts();
      }
    },
    methods: {
      async getProducts() {
        const category = this.$route.params.category;
        try {
          this.products = null; // Reset pour afficher le loading
          const response = await axios.get(`http://localhost:8080/api/products/category/${category}`);
          this.products = response.data;
          console.log(category);
          console.log(this.products);
        } catch (error) {
          console.error('Error fetching products ', error);
          this.products = []; // Set empty array en cas d'erreur
        }
      },
      getSortFunction() {
        switch(this.sortBy) {
          case 'price-asc':
            return (a, b) => a.price - b.price;
          case 'price-desc':
            return (a, b) => b.price - a.price;
          case 'stock':
            return (a, b) => b.stockQuantity - a.stockQuantity;
          case 'name':
          default:
            return (a, b) => a.name.localeCompare(b.name);
        }
      },
      sortProducts() {
        // Le computed filteredProducts se met automatiquement à jour
      },
      handleImageError(event) {
        // Image de fallback si l'image ne charge pas
        event.target.src = 'data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" width="400" height="300" viewBox="0 0 400 300"><rect width="400" height="300" fill="%23f3f4f6"/><text x="200" y="150" text-anchor="middle" fill="%236b7280" font-family="Arial, sans-serif" font-size="16">Image non disponible</text></svg>';
      },
      async addOneToCart(productId) {
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
                product: productId,
                quantity: 1
            };

            const response = await axios.post(`http://localhost:8080/api/carts/${this.userId}`,
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
            console.error('Error adding to Cart');
        }
      }
    }
  }
</script>
  
<style scoped>
  .line-clamp-2 {
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  
  .line-clamp-3 {
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  
  /* Animation pour les cartes */
  @keyframes fadeInUp {
    from {
      opacity: 0;
      transform: translateY(30px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
  
  .grid > div {
    animation: fadeInUp 0.6s ease-out forwards;
  }
  
  .grid > div:nth-child(even) {
    animation-delay: 0.1s;
  }
</style>