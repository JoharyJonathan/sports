<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-6">
    <div class="max-w-7xl mx-auto">
      <!-- En-tête avec icône -->
      <div class="text-center mb-10">
        <div class="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-r from-blue-600 to-indigo-600 rounded-full mb-4 shadow-lg">
          <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"/>
          </svg>
        </div>
        <h1 class="text-4xl font-bold text-gray-800 mb-2">Gestion des Produits</h1>
        <p class="text-gray-600">Gérez facilement votre inventaire de produits</p>
      </div>

      <!-- Formulaire produit avec design moderne -->
      <div class="bg-white rounded-2xl shadow-xl p-8 mb-10 border border-gray-100">
        <h2 class="text-2xl font-semibold text-gray-800 mb-6 flex items-center">
          <div class="w-2 h-8 bg-gradient-to-b from-blue-500 to-indigo-500 rounded-full mr-3"></div>
          {{ form.id ? 'Modifier le produit' : 'Ajouter un nouveau produit' }}
        </h2>
        
        <form @submit.prevent="handleSubmit" class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <!-- Champs de saisie stylisés -->
          <div class="relative">
            <input 
              v-model="form.name" 
              type="text" 
              placeholder="Nom du produit" 
              class="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-500 focus:outline-none transition-colors duration-200 bg-white text-black"
              required 
            />
            <div class="absolute inset-y-0 right-0 flex items-center pr-3">
              <svg class="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z"/>
              </svg>
            </div>
          </div>

          <div class="relative">
            <input 
              v-model="form.category" 
              type="text" 
              placeholder="Catégorie" 
              class="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-500 focus:outline-none transition-colors duration-200 bg-white text-black" 
              required 
            />
            <div class="absolute inset-y-0 right-0 flex items-center pr-3">
              <svg class="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"/>
              </svg>
            </div>
          </div>

          <div class="md:col-span-2">
            <textarea 
              v-model="form.description" 
              placeholder="Description du produit" 
              rows="3"
              class="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-500 focus:outline-none transition-colors duration-200 bg-white resize-none text-black"
            ></textarea>
          </div>

          <div class="relative">
            <input 
              v-model.number="form.stockQuantity" 
              type="number" 
              placeholder="Quantité en stock" 
              class="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-500 focus:outline-none transition-colors duration-200 bg-white text-black" 
              required 
            />
            <div class="absolute inset-y-0 right-0 flex items-center pr-3">
              <svg class="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4"/>
              </svg>
            </div>
          </div>

          <div class="relative">
            <input 
              v-model.number="form.price" 
              type="number" 
              step="0.01" 
              placeholder="Prix en €" 
              class="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-500 focus:outline-none transition-colors duration-200 bg-white text-black" 
              required 
            />
            <div class="absolute inset-y-0 right-0 flex items-center pr-3">
              <span class="text-gray-400 font-medium">€</span>
            </div>
          </div>

          <div class="md:col-span-2">
            <input 
              v-model="form.imageUrl" 
              type="text" 
              placeholder="URL de l'image du produit" 
              class="w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:border-blue-500 focus:outline-none transition-colors duration-200 bg-white text-black" 
            />
          </div>

          <button 
            type="submit" 
            class="md:col-span-2 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-700 hover:to-indigo-700 text-white font-semibold px-8 py-4 rounded-xl transition-all duration-200 transform hover:scale-105 shadow-lg hover:shadow-xl"
          >
            <span class="flex items-center justify-center">
              <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"/>
              </svg>
              {{ form.id ? 'Mettre à jour' : 'Ajouter' }} le produit
            </span>
          </button>
        </form>
      </div>

      <!-- Section des produits -->
      <div class="mb-6 flex justify-between">
        <h2 class="text-2xl font-semibold text-gray-800 mb-6 flex items-center">
          <div class="w-2 h-8 bg-gradient-to-b from-green-500 to-emerald-500 rounded-full mr-3"></div>
          Catalogue des Produits ({{ products.length }})
        </h2>
        <button @click="exportProducts" class="text-white px-4 py-1 mr-3 bg-blue-400 rounded-2xl hover:bg-blue-500">export csv</button>
      </div>

      <!-- Grille des produits avec animations -->
      <div class="grid gap-6 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
        <div 
          v-for="product in products" 
          :key="product.id" 
          class="bg-white rounded-2xl shadow-lg hover:shadow-2xl transition-all duration-300 transform hover:-translate-y-2 border border-gray-100 overflow-hidden group"
        >
          <!-- Image du produit -->
          <div class="relative h-48 overflow-hidden bg-gray-100">
            <img 
              v-if="product.imageUrl" 
              :src="product.imageUrl" 
              :alt="product.name"
              class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300" 
            />
            <div v-else class="w-full h-full flex items-center justify-center bg-gradient-to-br from-gray-100 to-gray-200">
              <svg class="w-12 h-12 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"/>
              </svg>
            </div>
            <!-- Badge de stock -->
            <div class="absolute top-3 right-3">
              <span 
                :class="product.stockQuantity > 10 ? 'bg-green-500' : product.stockQuantity > 0 ? 'bg-yellow-500' : 'bg-red-500'"
                class="px-2 py-1 text-xs font-semibold text-white rounded-full"
              >
                {{ product.stockQuantity > 0 ? `${product.stockQuantity} en stock` : 'Rupture' }}
              </span>
            </div>
          </div>
          
          <!-- Contenu de la carte -->
          <div class="p-6">
            <h3 class="text-xl font-bold text-gray-800 mb-2 truncate" :title="product.name">
              {{ product.name }}
            </h3>
            
            <div class="space-y-2 mb-4">
              <div class="flex items-center text-sm text-gray-600">
                <svg class="w-4 h-4 mr-2 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z"/>
                </svg>
                {{ product.category }}
              </div>
              
              <div class="flex items-center text-lg font-bold text-blue-600">
                <svg class="w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1"/>
                </svg>
                {{ product.price.toFixed(2) }} €
              </div>
            </div>

            <!-- Description tronquée -->
            <p v-if="product.description" class="text-sm text-gray-600 mb-4 line-clamp-2">
              {{ product.description }}
            </p>
            
            <!-- Boutons d'action -->
            <div class="flex gap-2">
              <button 
                @click="editProduct(product)" 
                class="flex-1 bg-blue-50 hover:bg-blue-100 text-blue-600 font-medium px-4 py-2 rounded-lg transition-colors duration-200 flex items-center justify-center"
              >
                <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"/>
                </svg>
                Modifier
              </button>
              <button 
                @click="deleteProduct(product.idAsString)" 
                class="flex-1 bg-red-50 hover:bg-red-100 text-red-600 font-medium px-4 py-2 rounded-lg transition-colors duration-200 flex items-center justify-center"
              >
                <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
                </svg>
                Supprimer
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Message si aucun produit -->
      <div v-if="products.length === 0" class="text-center py-12">
        <div class="inline-flex items-center justify-center w-16 h-16 bg-gray-100 rounded-full mb-4">
          <svg class="w-8 h-8 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2 2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"/>
          </svg>
        </div>
        <h3 class="text-lg font-medium text-gray-600 mb-2">Aucun produit disponible</h3>
        <p class="text-gray-500">Commencez par ajouter votre premier produit using le formulaire ci-dessus.</p>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "Products",
    data() {
      return {
        products: [],
        form: {
          id: null,
          name: '',
          category: '',
          description: '',
          stockQuantity: 0,
          price: 0.0,
          imageUrl: '',
          idAsString: '',
        },
      }
    },
    mounted() {
      this.fetchProducts()
    },
    methods: {
      async fetchProducts() {
        const res = await axios.get('http://localhost:8080/api/products')
        this.products = res.data
      },
      async handleSubmit() {
        console.log('id : ', this.form.idAsString);
        if (this.form.id) {
          await axios.put(`http://localhost:8080/api/products/${this.form.idAsString}`, this.form)
        } else {
          await axios.post('http://localhost:8080/api/products', this.form)
        }
        this.resetForm()
        this.fetchProducts()
      },
      editProduct(product) {
        this.form = { ...product, id: product.id }
      },
      async deleteProduct(id) {
        if (confirm('Voulez-vous vraiment supprimer ce produit ?')) {
          await axios.delete(`http://localhost:8080/api/products/${id}`)
          this.fetchProducts()
        }
      },
      resetForm() {
        this.form = {
          id: null,
          name: '',
          category: '',
          description: '',
          stockQuantity: 0,
          price: 0.0,
          imageUrl: '',
          idAsString: '',
        }
      },
      async exportProducts() {
        try {
          const response = await axios.get('http://localhost:8000/products/export/csv', {
            responseType: 'blob'
          });

          const blob = new Blob([response.data], { type: 'text/csv' });
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', 'products.csv');
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          window.URL.revokeObjectURL(url);

          console.log(response.data);
        } catch (error) {
          console.error('Error exporting products ', error);
        }
      }
    },
  }
</script>

<style scoped>
  .line-clamp-2 {
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
</style>