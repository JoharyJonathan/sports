<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-900 to-blue-700 text-white">
      <!-- Breadcrumb Navigation -->
      <div class="max-w-7xl mx-auto px-4 py-4">
        <div class="flex items-center text-sm text-blue-200">
          <RouterLink to="/" class="hover:text-white">Accueil</RouterLink>
          <span class="mx-2">/</span>
          <RouterLink :to="`/products/category/${product.category}`" class="hover:text-white">{{ product.category }}</RouterLink>
          <span class="mx-2">/</span>
          <span class="text-yellow-400">{{ product.name }}</span>
        </div>
      </div>
  
      <!-- Product Detail Section -->
      <section class="max-w-7xl mx-auto px-4 py-8">
        <div class="bg-blue-800 rounded-lg shadow-xl overflow-hidden">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <!-- Product Images -->
            <div class="p-6">
              <!-- Main Image -->
              <template v-if="product.imageUrl">
                <div class="bg-blue-700 aspect-square rounded-lg mb-4 overflow-hidden">
                  <img 
                    :src="product.imageUrl" 
                    alt="" 
                    class="w-full h-full object-cover"
                  >
                </div>
              </template>
              <template v-else>
                <div class="bg-blue-700 aspect-square rounded-lg mb-4 flex items-center justify-center">
                  <span class="text-4xl text-blue-400">Image principale</span>
                </div>
              </template>
              
              <!-- Thumbnail Gallery -->
              <div class="grid grid-cols-4 gap-2">
                <div class="bg-blue-600 aspect-square rounded cursor-pointer border-2 border-yellow-400"></div>
                <div class="bg-blue-600 aspect-square rounded cursor-pointer hover:border-2 hover:border-yellow-400"></div>
                <div class="bg-blue-600 aspect-square rounded cursor-pointer hover:border-2 hover:border-yellow-400"></div>
                <div class="bg-blue-600 aspect-square rounded cursor-pointer hover:border-2 hover:border-yellow-400"></div>
              </div>
            </div>
  
            <!-- Product Info -->
            <div class="p-6">
              <div class="flex items-center gap-2 mb-2">
                <span class="bg-yellow-500 text-black text-xs font-bold px-2 py-1 rounded">NOUVEAU</span>
                <span class="bg-purple-500 text-white text-xs font-bold px-2 py-1 rounded">BEST-SELLER</span>
              </div>
              
              <h1 class="text-3xl font-bold mb-2">{{ product.name }}</h1>
              
              <div class="flex items-center gap-2 mb-4">
                <div class="text-yellow-400 flex">
                  <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                </div>
                <span class="text-blue-200">({{ rates.length }} avis)</span>
              </div>
              
              <div class="mb-6">
                <span class="text-3xl font-bold text-yellow-400">{{ product.price }} €</span>
                <p :class="product.stockQuantity > 10 ? 'text-green-400' : 'text-red-400'" class="mt-1">
                  {{ product.stockQuantity > 10 ? 'En stock - Livraison sous 24h' : 'En Rupture de stock' }}
                </p>
              </div>
              
              <p class="text-blue-100 mb-6">
                {{ product.description }}
              </p>
              
              <!-- Color Selection -->
              <div class="mb-6">
                <h3 class="font-semibold mb-2">Couleur:</h3>
                <div class="flex gap-2">
                  <button class="w-8 h-8 rounded-full bg-white border-2 border-yellow-400"></button>
                  <button class="w-8 h-8 rounded-full bg-blue-500 hover:border-2 hover:border-yellow-400"></button>
                  <button class="w-8 h-8 rounded-full bg-red-500 hover:border-2 hover:border-yellow-400"></button>
                  <button class="w-8 h-8 rounded-full bg-black hover:border-2 hover:border-yellow-400"></button>
                </div>
              </div>
              
              <!-- Size Selection -->
              <div class="mb-6">
                <h3 class="font-semibold mb-2">Taille:</h3>
                <div class="flex flex-wrap gap-2">
                  <button class="px-4 py-2 rounded-lg bg-blue-900 hover:bg-blue-700">Taille 3</button>
                  <button class="px-4 py-2 rounded-lg bg-yellow-500 text-black font-semibold">Taille 4</button>
                  <button class="px-4 py-2 rounded-lg bg-blue-900 hover:bg-blue-700">Taille 5</button>
                </div>
              </div>
              
              <!-- Quantity Selection -->
              <div class="mb-6">
                <h3 class="font-semibold mb-2">Quantité:</h3>
                <div class="flex items-center">
                  <button class="bg-blue-900 px-3 py-1 rounded-l-lg text-xl font-bold">-</button>
                  <input type="number" v-model="quantity" min="1" :max="product.stockQuantity" class="bg-blue-700 border-y border-blue-600 w-16 py-1 text-center" />
                  <button class="bg-blue-900 px-3 py-1 rounded-r-lg text-xl font-bold">+</button>
                </div>
              </div>
              
              <!-- Action Buttons -->
              <div class="flex flex-col sm:flex-row gap-4 mb-6">
                <button @click="addToCart" class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-3 px-6 rounded-lg flex-1 flex items-center justify-center gap-2 transition duration-300">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M3 1a1 1 0 000 2h1.22l.305 1.222a.997.997 0 00.01.042l1.358 5.43-.893.892C3.74 11.846 4.632 14 6.414 14H15a1 1 0 000-2H6.414l1-1H14a1 1 0 00.894-.553l3-6A1 1 0 0017 3H6.28l-.31-1.243A1 1 0 005 1H3zM16 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zM6.5 18a1.5 1.5 0 100-3 1.5 1.5 0 000 3z" />
                  </svg>
                  AJOUTER AU PANIER
                </button>
                <button @click="addToFavorites" class="border-2 border-white hover:bg-white hover:text-blue-900 font-bold py-3 px-6 rounded-lg flex items-center justify-center gap-2 transition duration-300">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path fill-rule="evenodd" d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z" clip-rule="evenodd" />
                  </svg>
                  FAVORIS
                </button>
              </div>
              
              <!-- Product Details Accordion -->
              <div class="border-t border-blue-700 pt-4">
                <div class="mb-3">
                  <button class="flex justify-between w-full py-2 font-semibold">
                    <span>Caractéristiques</span>
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                      <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
                    </svg>
                  </button>
                  <div class="pl-4 pt-2 text-blue-200">
                    <ul class="list-disc pl-4 space-y-1">
                      <li>Matériau extérieur: Cuir synthétique premium</li>
                      <li>Construction: 32 panneaux thermocollés</li>
                      <li>Certification: FIFA Quality Pro</li>
                      <li>Vessie: Latex haute performance</li>
                      <li>Idéal pour: Match et entraînement professionnel</li>
                    </ul>
                  </div>
                </div>
                
                <div class="border-t border-blue-700 py-3">
                  <button class="flex justify-between w-full py-2 font-semibold">
                    <span>Livraison et retours</span>
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                      <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
                    </svg>
                  </button>
                </div>
                
                <div class="border-t border-blue-700 py-3">
                  <button class="flex justify-between w-full py-2 font-semibold">
                    <span>Garantie</span>
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                      <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
                    </svg>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Product Reviews Section -->
      <section class="max-w-7xl mx-auto px-4 py-8">
        <div class="bg-blue-800 rounded-lg shadow-xl p-6">
          <div class="bg-gradient-to-br from-yellow-50 to-orange-50 rounded-2xl p-6 shadow-lg border border-yellow-100 mb-4">
          <!-- En-tête avec titre et bouton -->
          <div class="flex justify-between items-center mb-6">
            <div class="flex items-center">
              <div class="w-3 h-8 bg-gradient-to-b from-yellow-400 to-orange-400 rounded-full mr-3"></div>
              <h2 class="text-2xl font-bold text-gray-800 flex items-center">
                <svg class="w-6 h-6 text-yellow-500 mr-2" fill="currentColor" viewBox="0 0 24 24">
                  <path d="M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"/>
                </svg>
                Avis clients
              </h2>
            </div>
            
            <button 
              @click="toggleRate" 
              :class="showRate ? 'bg-red-500 hover:bg-red-600' : 'bg-gradient-to-r from-yellow-400 to-orange-400 hover:from-yellow-500 hover:to-orange-500'"
              class="text-white font-semibold px-6 py-3 rounded-xl transition-all duration-300 transform hover:scale-105 shadow-md hover:shadow-lg flex items-center"
            >
              <svg v-if="!showRate" class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 24 24">
                <path d="M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"/>
              </svg>
              <svg v-else class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"/>
              </svg>
              {{ showRate ? 'Annuler' : 'Noter ce produit' }}
            </button>
          </div>

          <!-- Formulaire de notation avec étoiles -->
          <div v-if="showRate" class="bg-white rounded-xl p-6 shadow-inner border border-yellow-200">
            <form @submit.prevent="addScore" class="space-y-6">
              <!-- Section étoiles -->
              <div class="text-center">
                <h3 class="text-lg font-semibold text-gray-700 mb-4">Quelle note donnez-vous à ce produit ?</h3>
                
                <!-- Affichage des étoiles -->
                <div class="flex justify-center items-center space-x-2 mb-4">
                  <button
                    v-for="star in 5"
                    :key="star"
                    type="button"
                    @click="setRating(star)"
                    @mouseover="hoverRating = star"
                    @mouseleave="hoverRating = 0"
                    class="transition-all duration-200 transform hover:scale-110 focus:outline-none"
                  >
                    <svg 
                      :class="[
                        'w-10 h-10 transition-colors duration-200',
                        (hoverRating >= star || score >= star) 
                          ? 'text-yellow-400 drop-shadow-lg' 
                          : 'text-gray-300 hover:text-yellow-200'
                      ]"
                      fill="currentColor" 
                      viewBox="0 0 24 24"
                    >
                      <path d="M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"/>
                    </svg>
                  </button>
                </div>

                <!-- Texte descriptif de la note -->
                <div class="mb-6">
                  <p class="text-sm text-gray-600 mb-2">Note sélectionnée :</p>
                  <div class="flex items-center justify-center">
                    <span class="text-2xl font-bold text-yellow-600 mr-2">{{ score.toFixed(1) }}</span>
                    <span class="text-lg text-gray-500">/ 5.0</span>
                  </div>
                  <p class="text-sm text-gray-500 mt-1">{{ getRatingText(score) }}</p>
                </div>

                <!-- Échelle de notation visuelle -->
                <div class="bg-gray-100 rounded-full h-3 w-full max-w-xs mx-auto mb-6 overflow-hidden">
                  <div 
                    :style="{ width: (score / 5) * 100 + '%' }"
                    class="h-full bg-gradient-to-r from-yellow-400 to-orange-400 rounded-full transition-all duration-300"
                  ></div>
                </div>
              </div>

              <!-- Bouton de soumission -->
              <div class="text-center">
                <button 
                  type="submit"
                  :disabled="score === 0"
                  :class="score === 0 ? 'bg-gray-300 cursor-not-allowed' : 'bg-gradient-to-r from-green-500 to-emerald-500 hover:from-green-600 hover:to-emerald-600 transform hover:scale-105'"
                  class="text-white font-bold py-3 px-8 rounded-xl transition-all duration-300 shadow-lg hover:shadow-xl flex items-center justify-center mx-auto"
                >
                  <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
                  </svg>
                  Soumettre la note
                </button>
                
                <p class="text-xs text-gray-500 mt-2">
                  Votre avis nous aide à améliorer nos produits
                </p>
              </div>
            </form>
          </div>
        </div>
          
          <!-- Reviews Summary -->
          <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
            <!-- Average Rating -->
            <div class="bg-blue-700 rounded-lg p-6 text-center">
              <div class="text-6xl font-bold text-yellow-400 mb-2">{{ averageRating }}</div>
              <div class="flex justify-center text-yellow-400 mb-2">
                <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
              </div>
              <p class="text-blue-200">Basé sur {{ rates.length }} avis</p>
            </div>
            
            <!-- Rating Distribution -->
            <div class="bg-blue-700 rounded-lg p-6">
              <h3 class="font-semibold mb-3">Distribution des notes</h3>
              <div class="space-y-2">
                <div class="flex items-center gap-2">
                  <div class="text-yellow-400">5★</div>
                  <div class="flex-1 bg-blue-900 rounded-full h-2.5">
                    <div class="bg-yellow-400 h-2.5 rounded-full" style="width: 80%"></div>
                  </div>
                  <div class="text-blue-200">80%</div>
                </div>
                <div class="flex items-center gap-2">
                  <div class="text-yellow-400">4★</div>
                  <div class="flex-1 bg-blue-900 rounded-full h-2.5">
                    <div class="bg-yellow-400 h-2.5 rounded-full" style="width: 15%"></div>
                  </div>
                  <div class="text-blue-200">15%</div>
                </div>
                <div class="flex items-center gap-2">
                  <div class="text-yellow-400">3★</div>
                  <div class="flex-1 bg-blue-900 rounded-full h-2.5">
                    <div class="bg-yellow-400 h-2.5 rounded-full" style="width: 3%"></div>
                  </div>
                  <div class="text-blue-200">3%</div>
                </div>
                <div class="flex items-center gap-2">
                  <div class="text-yellow-400">2★</div>
                  <div class="flex-1 bg-blue-900 rounded-full h-2.5">
                    <div class="bg-yellow-400 h-2.5 rounded-full" style="width: 1%"></div>
                  </div>
                  <div class="text-blue-200">1%</div>
                </div>
                <div class="flex items-center gap-2">
                  <div class="text-yellow-400">1★</div>
                  <div class="flex-1 bg-blue-900 rounded-full h-2.5">
                    <div class="bg-yellow-400 h-2.5 rounded-full" style="width: 1%"></div>
                  </div>
                  <div class="text-blue-200">1%</div>
                </div>
              </div>
            </div>
            
            <!-- Write Review CTA -->
            <div class="bg-blue-700 rounded-lg p-6 flex flex-col justify-center items-center">
              <p class="text-center mb-4">Partagez votre expérience avec ce produit</p>
              <button @click="toggleForm()" class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-6 rounded-lg transition duration-300">
                {{ showForm ? 'ANNULER' : 'ÉCRIRE UN AVIS' }}
              </button>
            </div>

              <!-- Formulaire d'ajout de commentaire -->
              <form v-if="showForm" @submit.prevent="submitComment" class="mt-6 w-full max-w-lg space-y-4">

                <textarea v-model="newComment" rows="4" placeholder="Votre avis..."
                  class="w-full px-4 py-2 rounded-lg bg-blue-800 text-white placeholder-blue-300 border border-blue-600" required></textarea>

                <button type="submit"
                  class="bg-green-500 hover:bg-green-600 text-black font-bold py-2 px-6 rounded-lg transition duration-300">
                  Soumettre l’avis
                </button>
              </form>
          </div>
          
          <div v-for="com in comments" :key="com.id">
            <!-- Individual Reviews -->
            <div class="space-y-6">
              <!-- Review 1 -->
              <div class="border-b border-blue-700 pb-6">
                <div class="flex justify-between mb-2">
                  <div class="font-semibold">{{ com.user.username }}</div>
                  <div class="text-blue-200 text-sm">Il y a 2 jours</div>
                </div>
                <div class="text-yellow-400 mb-2">★★★★★</div>
                <h4 class="font-semibold mb-2">Excellent ballon pour les matchs officiels</h4>
                <p class="text-blue-200 mb-4">
                  {{ com.content }}
                </p>
                <div class="flex gap-4">
                  <button class="text-blue-200 text-sm flex items-center gap-1 hover:text-white">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                      <path d="M2 10.5a1.5 1.5 0 113 0v6a1.5 1.5 0 01-3 0v-6zM6 10.333v5.43a2 2 0 001.106 1.79l.05.025A4 4 0 008.943 18h5.416a2 2 0 001.962-1.608l1.2-6A2 2 0 0015.56 8H12V4a2 2 0 00-2-2 1 1 0 00-1 1v.667a4 4 0 01-.8 2.4L6.8 7.933a4 4 0 00-.8 2.4z" />
                    </svg>
                    Utile (24)
                  </button>
                  <button class="text-blue-200 text-sm hover:text-white">
                    Signaler
                  </button>
                </div>
              </div>
            </div>
          </div>
            <!-- See All Reviews Button -->
            <div class="text-center">
              <button class="bg-blue-700 hover:bg-blue-600 py-2 px-6 rounded-lg transition duration-300">
                VOIR TOUS LES COMMENTAIRES ({{ comments.length }})
              </button>
            </div>
        </div>
      </section>
  
      <section class="max-w-7xl mx-auto px-4 py-8 mb-8">
        <h2 class="text-2xl font-bold mb-6">Produits similaires</h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
        <div
        v-for="sim in simrecoms"
        :key="sim._id"
        class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105"
        >
          <img :src="sim.image_url" alt="" class="w-full h-48 object-cover">
          <div class="p-4">
            <h3 class="font-bold text-lg mb-1">{{ sim.name }}</h3>
              <div class="flex text-yellow-400 mb-2">★★★★☆</div>
                <div class="flex justify-between items-center">
                <p class="text-yellow-400 font-bold">{{ sim.price.toFixed(2) }} €</p>
                <RouterLink :to="`/product/${sim._id}`" class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-1 px-3 rounded-lg transition duration-300">
                  Ajouter
                </RouterLink>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Recently Viewed -->
      <section class="max-w-7xl mx-auto px-4 py-8 mb-12">
        <h2 class="text-2xl font-bold mb-6">Récemment consultés</h2>
        
        <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-4">
          <div v-for="hy in products" :key="hy.idAsString" class="bg-blue-800 rounded-lg overflow-hidden shadow-lg">
            <RouterLink :to="`/product/${hy.idAsString}`">
              <img :src="hy.imageUrl" alt="" class="w-full h-32 object-cover">
              <div class="p-2">
                <h3 class="font-semibold text-sm truncate text-white">{{ hy.name }}</h3>
                <p class="text-yellow-400 font-bold text-sm">{{ hy.price }} €</p>
              </div>
            </RouterLink>
          </div>  
        </div>
      </section>
    </main>
</template>
  
<script>
  import axios from 'axios';
  import { jwtDecode } from 'jwt-decode';

  export default {
    name: "ProductDetail",
    props: ['product'],
    watch: {
      '$route.params.id': {
        handler() {
          this.getProduct();
        },
        immediate: true,
      }
    },
    data() {
      return {
        product: [],
        quantity: 1,
        userId: null,
        comments: [],
        showForm: false,
        showRate: false,
        newComment: null,
        score: 0,
        hoverRating: 0,
        rates: [],
        averageRating: 0,
        history: {},
        products: [],
        simrecoms: null,
        image: null,
        prods: null
      };
    },
    created() {
      this.getProduct();
      this.fetchProductsComments();
      this.getHistory();
      this.getSimiliarityRecommendations();
    },
    mounted() {
      this.getAllScores();
    },
    methods: {
      async getProduct() {
        const productId = this.$route.params.id;

        try {
          const response = await axios.get(`http://localhost:8080/api/products/${productId}`);

          this.product = response.data;
          console.log(this.product);
        } catch (error) {
          console.error('Error Product not found : ', error);
        }
      },
      async getAllScores() {
        const productId = this.$route.params.id;

        try {
          const response = await axios.get(`http://localhost:8080/api/ratings/product/${productId}`);

          this.rates = response.data;
          console.log('Scores ', this.rates[0].score);
          console.log(this.rates.length);

          let sum = 0;
          for (let i = 0; i < this.rates.length; i++) {
            sum += this.rates[i].score;
          }

          const moy = sum / this.rates.length;
          console.log(moy);
          this.averageRating = moy;
        } catch (error) {
          console.error('Error fetching scores ', error);
        }
      },
      async getHistory() {
        try {
          const response = await axios.get("http://localhost:8000/history");
          this.history = response.data;

          this.products = this.history.global_history.map(item => JSON.parse(item.data));

          console.log('Produits :', this.products);
        } catch (error) {
          console.error('Erreur lors du fetch de l’historique', error);
        }
      },
      async addToCart() {
        const productId = this.$route.params.id;

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
            quantity: this.quantity
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
      async addToFavorites() {
        const productId = this.$route.params.id;

        const token = localStorage.getItem('token');
        const decoded = jwtDecode(token);
        console.log(decoded.sub);
        this.userId = decoded.sub;

        try {
          const response = await axios.post(`http://localhost:8080/api/favorites/add?userId=${decoded.sub}&productId=${productId}`);

          console.log(response.data);
          this.$router.push(`/favoris/${this.userId}`);
        } catch (error) {
          console.error('Error adding this product to your favorites', error);
        }
      },
      async fetchProductsComments() {
        const productId = this.$route.params.id;

        try {
          const response = await axios.get(`http://localhost:8080/api/comments/product/${productId}`);

          this.comments = response.data;
          console.log(this.comments);
        } catch (error) {
          console.error('Error fetching products comments ', error);
        }
      },
      toggleForm() {
        this.showForm = !this.showForm;
      },
      async submitComment() {
        try {
          const token = localStorage.getItem('token');
          
          if (!token) {
            this.$router.push('/login');
            throw new Error('Veuillez vous connecter');
          }
  
          const decoded = jwtDecode(token);
            
          if (decoded.exp * 1000 < Date.now()) {
            localStorage.removeItem('token');
            this.$router.push('/login');
            throw new Error('Session expirée');
          }
  
          this.userId = decoded.sub;
          const productId = this.$route.params.id;

          const commentData = this.newComment;

          const response = await axios.post(`http://localhost:8080/api/comments/add?userId=${this.userId}&productId=${productId}`, commentData, 
          {
            headers: {
              'Content-Type': 'application/json'
            }
          }
          );

          console.log(response.data);
          this.showForm = false;
          this.fetchProductsComments();
        } catch (error) {
          console.error("Erreur lors de l'ajout du commentaire :", error);
        }
      },
      toggleRate() {
      this.showRate = !this.showRate;
      // Réinitialiser le score quand on annule
      if (!this.showRate) {
        this.score = 0;
        this.hoverRating = 0;
      }
    },
    setRating(rating) {
      this.score = rating;
    },
    updateStarsFromInput() {
      // Valider que la valeur est dans la plage 0-5
      if (this.score > 5) this.score = 5;
      if (this.score < 0) this.score = 0;
    },
    getStarClass(star) {
      const displayRating = this.hoverRating || this.score;
      
      if (displayRating >= star) {
        // Étoile complètement remplie
        return 'text-yellow-400 drop-shadow-lg';
      } else if (displayRating > star - 1 && displayRating < star) {
        // Étoile partiellement remplie (entre deux étoiles)
        return 'text-yellow-300 drop-shadow-md';
      } else {
        // Étoile vide
        return 'text-gray-300 hover:text-yellow-200';
      }
    },
    getRatingText(score) {
      if (score === 0) return "Aucune note sélectionnée";
      if (score <= 1) return "Très décevant";
      if (score <= 2) return "Décevant";
      if (score <= 3) return "Correct";
      if (score <= 4) return "Bien";
      if (score < 5) return "Très bien";
      return "Excellent";
    },
    async addScore() {
      try {
        const token = localStorage.getItem('token');
        
        if (!token) {
          this.$router.push('/login');
          throw new Error('Veuillez vous connecter');
        }

        const decoded = jwtDecode(token);
          
        if (decoded.exp * 1000 < Date.now()) {
          localStorage.removeItem('token');
          this.$router.push('/login');
          throw new Error('Session expirée');
        }

        this.userId = decoded.sub;
        const productId = this.$route.params.id;

        const ratingdata = {
          user: this.userId,
          product: productId,
          score: this.score
        }

        const response = await axios.post(`http://localhost:8080/api/ratings/add?userId=${ratingdata.user}&productId=${ratingdata.product}&score=${ratingdata.score}`, ratingdata, 
        {
          headers: {
                'Content-Type': 'application/json'
          }
        }
        );
        
        console.log(response.data);
        
        // Feedback utilisateur
        alert('Votre note a été enregistrée avec succès !');
        
        // Réinitialiser le formulaire
        this.showRate = false;
        this.score = 0;
        this.hoverRating = 0;
        
      } catch (error) {
        console.error('Error adding score ', error);
        alert('Erreur lors de l\'enregistrement de votre note');
      }
    },
    async getSimiliarityRecommendations() {
      const productId = this.$route.params.id;

      try {
        const product = await axios.get(`http://localhost:8080/api/products/${productId}`);

        const productName = product.data.name;

        const response = await axios.get(`http://localhost:8000/recommend/${productName}`);

        this.simrecoms = response.data;
        console.log('Similiar products ', this.simrecoms);
      } catch (error) {
        console.error('Error fetching products ', error);
      }
    }
    }
  }
</script>

<style scoped>
  /* Animation pour les étoiles */
  .star-hover {
    filter: drop-shadow(0 0 8px rgba(251, 191, 36, 0.6));
  }

  /* Effet de pulsation pour les étoiles actives */
  @keyframes pulse-star {
    0%, 100% { transform: scale(1); }
    50% { transform: scale(1.05); }
  }

  .star-active {
    animation: pulse-star 0.6s ease-in-out;
  }
</style>