<template>
    <div class="p-6">
      <h1 class="text-2xl font-bold mb-6">Gestion des Paniers</h1>
  
      <div v-if="loading" class="text-center text-gray-600">Chargement...</div>
  
      <table v-else class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
        <thead class="bg-gray-100">
          <tr>
            <th class="py-3 px-4 text-left text-black">ID</th>
            <th class="py-3 px-4 text-left text-black">Utilisateur</th>
            <th class="py-3 px-4 text-left text-black">Status</th>
            <th class="py-3 px-4 text-left text-black">Créé le</th>
            <th class="py-3 px-4 text-center text-black">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cart in carts" :key="cart.id" class="border-t">
            <td class="py-2 px-4 text-black">{{ cart.id }}</td>
            <td class="py-2 px-4 text-black">{{ cart.userId }}</td>
            <td class="py-2 px-4 text-black">
              <span :class="cart.status === 'pending' ? 'text-yellow-500' : 'text-green-600'">
                {{ cart.status }}
              </span>
            </td>
            <td class="py-2 px-4 text-black">{{ formatDate(cart.createdAt) }}</td>
            <td class="py-2 px-4 text-center">
              <button @click="viewCart(cart.userId)"
                class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 mr-2">
                Voir
              </button>
              <button @click="markAsPaid(cart.userId)"
                class="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600 mr-2">
                Marquer payé
              </button>
              <button @click="deleteCart(cart.id)"
                class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600">
                Supprimer
              </button>
            </td>
          </tr>
        </tbody>
      </table>
  
      <!-- Détails du panier -->
      <div v-if="selectedCart" class="mt-8">
        <h2 class="text-xl font-semibold mb-2">Détails du panier</h2>
        <ul class="bg-gray-50 rounded p-4 shadow">
          <li v-for="item in selectedCart.items" :key="item.productId" class="mb-2 text-black">
            Produit : <strong>{{ item.productId }}</strong> — Quantité : <strong>{{ item.quantity }}</strong>
          </li>
        </ul>
      </div>
    </div>
</template>
  
<script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const carts = ref([]);
  const loading = ref(true);
  const selectedCart = ref(null);
  
  const fetchCarts = async () => {
    loading.value = true;
    try {
      const res = await axios.get('http://localhost:8080/api/carts');
      carts.value = res.data;
      console.log('Carts List ',carts.value);
    } catch (err) {
      console.error('Erreur de chargement:', err);
    } finally {
      loading.value = false;
    }
  };
  
  const formatDate = (dateStr) => {
    const cleanDateStr = dateStr.replace(/\[UTC\]$/, '');
    const date = new Date(cleanDateStr);
    return date.toLocaleString();
  };
  
  const viewCart = async (userId) => {
    console.log(userId);
    try {
      const res = await axios.get(`http://localhost:8080/api/carts/cart/${userId}`);
      selectedCart.value = res.data;
    } catch (err) {
      console.error('Erreur récupération panier :', err);
    }
  };
  
  const markAsPaid = async (userId) => {
    try {
      await axios.patch(`http://localhost:8080/api/carts/update-status/${userId}`);
      await fetchCarts();
    } catch (err) {
      console.error('Erreur de mise à jour :', err);
    }
  };
  
  const deleteCart = async (cartId) => {
    try {
      await axios.delete(`http://localhost:8080/api/carts/delete-cart/${cartId}`);
      console.log(cartId);
      await fetchCarts();
    } catch (err) {
      console.error('Erreur de suppression :', err);
    }
  };
  
  onMounted(() => {
    fetchCarts();
  });
</script>  