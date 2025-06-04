<template>
    <div class="p-6">
      <h1 class="text-3xl font-bold mb-6">Gestion des Produits</h1>
  
      <!-- Formulaire produit -->
      <form @submit.prevent="handleSubmit" class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-8">
        <input v-model="form.name" type="text" placeholder="Nom du produit" class="input" required />
        <input v-model="form.category" type="text" placeholder="Catégorie" class="input" required />
        <input v-model="form.description" type="text" placeholder="Description" class="input" />
        <input v-model.number="form.stock_quantity" type="number" placeholder="Quantité en stock" class="input" required />
        <input v-model.number="form.price" type="number" step="0.01" placeholder="Prix" class="input" required />
        <input v-model="form.imageUrl" type="text" placeholder="URL de l'image" class="input" />
  
        <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded col-span-1 md:col-span-2">
          {{ form.id ? 'Mettre à jour' : 'Ajouter' }} le produit
        </button>
      </form>
  
      <!-- Liste des produits -->
      <div class="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
        <div v-for="product in products" :key="product.id" class="border p-4 rounded shadow">
          <img :src="product.imageUrl" alt="" class="w-full h-40 object-cover mb-2 rounded" v-if="product.imageUrl">
          <h2 class="text-xl font-semibold">{{ product.name }}</h2>
          <p class="text-sm">Catégorie : {{ product.category }}</p>
          <p class="text-sm">Prix : {{ product.price }} €</p>
          <p class="text-sm">Stock : {{ product.stock_quantity }}</p>
          <div class="mt-2 flex gap-2">
            <button @click="editProduct(product)" class="text-blue-600 hover:underline">Modifier</button>
            <button @click="deleteProduct(product.id)" class="text-red-600 hover:underline">Supprimer</button>
          </div>
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
          stock_quantity: 0,
          price: 0.0,
          imageUrl: '',
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
        if (this.form.id) {
          await axios.put(`http://localhost:8080/api/products/${this.form.id}`, this.form)
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
          stock_quantity: 0,
          price: 0.0,
          imageUrl: '',
        }
      },
    },
  }
</script>