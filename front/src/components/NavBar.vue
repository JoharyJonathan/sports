<template>
  <nav class="bg-blue-600 shadow-lg p-4 sticky top-0 z-50">
    <div class="container mx-auto flex flex-col md:flex-row items-center justify-between">
      <!-- Logo/Brand -->
      <div class="flex items-center mb-4 md:mb-0">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 mr-2 text-white" viewBox="0 0 20 20" fill="currentColor">
          <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM9.555 7.168A1 1 0 008 8v4a1 1 0 001.555.832l3-2a1 1 0 000-1.664l-3-2z" clip-rule="evenodd" />
        </svg>
        <span class="text-xl font-bold tracking-tight text-white">SPORT ZONE</span>
      </div>

      <!-- Barre de recherche -->
      <div class="relative w-50 max-w-md mx-auto">
        <input
          type="text"
          v-model="searchQuery"
          @input="handleInput"
          @focus="showResults = true"
          @blur="handleBlur"
          placeholder="Rechercher un produit..."
          class="w-full px-4 py-2 text-gray-700 bg-white border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition duration-200"
        />
        
        <!-- Indicateur de chargement -->
        <div v-if="isLoading" class="absolute right-3 top-3">
          <svg class="animate-spin h-5 w-5 text-blue-500" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
            <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
            <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8v8h8a8 8 0 01-8 8 8 8 0 01-8-8z"></path>
          </svg>
        </div>

        <!-- Liste déroulante des résultats -->
        <div
          v-if="showResults && searchResults.length > 0"
          class="absolute z-10 w-full mt-1 bg-white border border-gray-200 rounded-lg shadow-lg max-h-80 overflow-y-auto transition-all duration-200"
        >
          <ul>
            <li
              v-for="product in searchResults"
              :key="product.id"
              @mousedown.prevent="selectProduct(product)"
              class="px-4 py-2 hover:bg-blue-50 cursor-pointer transition duration-150"
            >
              <div class="flex items-center">
                <img
                  v-if="product.imageUrl"
                  :src="product.imageUrl"
                  alt="Product"
                  class="w-10 h-10 object-cover rounded mr-3"
                />
                <div>
                  <p class="text-sm font-medium text-gray-900">{{ product.name }}</p>
                  <p class="text-xs text-gray-500 truncate">{{ product.description }}</p>
                </div>
              </div>
            </li>
          </ul>
        </div>

        <!-- Message si aucun résultat -->
        <div
          v-if="showResults && !isLoading && searchQuery && searchResults.length === 0"
          class="absolute z-10 w-full mt-1 bg-white border border-gray-200 rounded-lg shadow-lg p-4 text-sm text-gray-500"
        >
          Aucun produit trouvé pour "{{ searchQuery }}".
        </div>

        <!-- Message d'erreur -->
        <div
          v-if="errorMessage"
          class="absolute z-10 w-full mt-1 bg-red-50 border border-red-200 rounded-lg shadow-lg p-4 text-sm text-red-600"
        >
          {{ errorMessage }}
        </div>
      </div>

      <!-- Navigation Links -->
      <div class="flex flex-wrap justify-center space-x-1 md:space-x-4">
        <RouterLink to="/" class="nav-link group" active-class="nav-active">
          <span class="group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">Home</span>
        </RouterLink>
        
        <RouterLink to="/products" class="nav-link group" active-class="nav-active">
          <span class="group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">Products</span>
        </RouterLink>
        
        <template v-if="isAuthenticated">
          <RouterLink :to="`/cart/${this.userId}`" class="nav-link group" active-class="nav-active">
            <span class="flex items-center group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" viewBox="0 0 20 20" fill="currentColor">
                <path d="M3 1a1 1 0 000 2h1.22l.305 1.222a.997.997 0 00.01.042l1.358 5.43-.893.892C3.74 11.846 4.632 14 6.414 14H15a1 1 0 000-2H6.414l1-1H14a1 1 0 00.894-.553l3-6A1 1 0 0017 3H6.28l-.31-1.243A1 1 0 005 1H3z" />
              </svg>
              Cart
            </span>
          </RouterLink>
          <RouterLink :to="`/profile/${this.userId}`" class="nav-link group" active-class="nav-active">
            <span class="flex items-center group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
              </svg>
              Profile
            </span>
          </RouterLink>
          <RouterLink :to="`/favoris/${this.userId}`" class="nav-link group" active-class="nav-active">
            <span class="flex items-center group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" viewBox="0 0 20 20" fill="currentColor">
                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.122 3.446a1 1 0 00.95.69h3.631c.969 0 1.371 1.24.588 1.81l-2.94 2.137a1 1 0 00-.364 1.118l1.122 3.446c.3.921-.755 1.688-1.54 1.118l-2.94-2.137a1 1 0 00-1.175 0l-2.94 2.137c-.785.57-1.84-.197-1.54-1.118l1.122-3.446a1 1 0 00-.364-1.118L2.36 8.873c-.783-.57-.38-1.81.588-1.81h3.631a1 1 0 00.95-.69l1.122-3.446z" />
              </svg>
              Favoris
            </span>
          </RouterLink>
        </template>
        
        <RouterLink to="/admin" class="nav-link group" active-class="nav-active">
          <span class="group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">Admin</span>
        </RouterLink>

        <RouterLink to="/about" class="nav-link group" active-class="nav-active">
          <span class="group-hover:border-b-2 group-hover:border-yellow-400 pb-1 px-1 transition duration-200">About</span>
        </RouterLink>
      </div>

      <!-- Authentication Buttons -->
      <template v-if="!isAuthenticated">
        <div class="flex mt-4 md:mt-0 space-x-2">
          <RouterLink to="/login" class="bg-gray-100 font-bold py-2 px-4 rounded-full transition duration-200 text-sm flex items-center">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-1" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M3 3a1 1 0 011 1v12a1 1 0 11-2 0V4a1 1 0 011-1zm7.707 3.293a1 1 0 010 1.414L9.414 9H17a1 1 0 110 2H9.414l1.293 1.293a1 1 0 01-1.414 1.414l-3-3a1 1 0 010-1.414l3-3a1 1 0 011.414 0z" clip-rule="evenodd" />
            </svg>
            Log In
          </RouterLink>
          
          <RouterLink to="/signup" class="bg-yellow-400 font-bold py-2 px-4 rounded-full transition duration-200 text-sm">
            Sign Up
          </RouterLink>
        </div>
      </template>
      <template v-else>
        <button @click="logout" class="bg-red-500 text-white font-bold py-2 px-4 rounded-full transition duration-200 text-sm">
          Logout
        </button>
      </template>
    </div>
  </nav>
</template>

<script>
  import { RouterLink } from 'vue-router';
  import { jwtDecode } from 'jwt-decode';
  import axios from 'axios';

  export default {
  name: "NavBar",
  components: {
    RouterLink
  },
  data() {
    return {
      user: null,
      isAuthenticated: false,
      userId: null,
      searchQuery: '',
      searchResults: [],
      isLoading: false,
      showResults: false,
      errorMessage: ''
    };
  },
  created() {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decoded = jwtDecode(token);
        console.log(decoded);
        this.user = decoded;
        this.isAuthenticated = true;
        this.userId = decoded.sub;
        console.log('User Id : ', this.userId);
      } catch (error) {
        console.error("Invalid token:", error);
        localStorage.removeItem('token');
      }
    } else {
      this.isAuthenticated = false;
    }
  },
  mounted() {
    this.isAuthenticated = !!localStorage.getItem("token");
  },
  methods: {
    async logout() {
      localStorage.removeItem("token");
      this.isAuthenticated = false;
      this.$router.push("/login");
    },
    async searchProducts(query) {
      try {
        this.isLoading = true;
        this.errorMessage = '';
        const response = await axios.get(`http://localhost:8080/api/products/search?q=${encodeURIComponent(query)}`);
        this.searchResults = response.data;
      } catch (error) {
        this.searchResults = [];
        if (error.response) {
          if (error.response.status === 204) {
            this.searchResults = []; // Aucun résultat
          } else if (error.response.status === 400) {
            this.errorMessage = 'Veuillez entrer un terme de recherche valide.';
          } else {
            this.errorMessage = 'Une erreur est survenue lors de la recherche.';
          }
        } else {
          this.errorMessage = 'Impossible de se connecter au serveur.';
        }
        console.error('Error searching products:', error);
      } finally {
        this.isLoading = false;
      }
    },
    handleInput() {
      clearTimeout(this.debounceTimeout);
      this.errorMessage = '';
      if (!this.searchQuery.trim()) {
        this.searchResults = [];
        this.showResults = false;
        return;
      }
      this.debounceTimeout = setTimeout(() => {
        this.searchProducts(this.searchQuery);
      }, 300); // Délai de 300ms
    },
    handleBlur() {
      setTimeout(() => {
        this.showResults = false;
      }, 200); // Délai pour permettre le clic sur un résultat
    },
    selectProduct(product) {
      console.log('Produit sélectionné:', product);
      this.searchQuery = product.name;
      this.searchResults = [];
      this.showResults = false;
      this.$router.push(`/product/${product.idAsString}`);
    }
  },
  watch: {
    searchQuery(newQuery) {
      if (!newQuery) {
        this.searchResults = [];
        this.showResults = false;
        this.errorMessage = '';
      }
    }
  }
  }
</script>

<style>
  .nav-link {
  color: #f8f9fa;
  font-weight: 500;
  transition: color 0.2s;
  }

  .nav-link:hover {
  color: #fcd34d;
  }

  .nav-active span {
  border-bottom: 2px solid #fcd34d;
  }
</style>