<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-800 to-blue-900 text-white p-8">
      <div class="max-w-4xl mx-auto">
        <!-- En-tête du panier -->
        <div class="bg-blue-700 rounded-t-lg shadow-lg p-6 mb-4">
          <div class="flex items-center justify-between">
            <h1 class="text-3xl font-bold">Mon Panier</h1>
            <div class="bg-blue-600 rounded-full px-4 py-2 flex items-center">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
              <span class="font-bold">{{ cart.items.length }} articles</span>
              <template v-if="cart.items.length !== 0">
                <button @click="clearCart()" class="bg-red-500 hover:bg-red-400 text-white py-1 px-4 rounded-2xl ml-6">
                  supprimer mon panier
                </button>
              </template>
            </div>
          </div>
        </div>
        
        <!-- Articles du panier -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg mb-4">
          <ul class="divide-y divide-blue-600">
            <!-- Article 1 -->
            <li class="p-6 flex items-center" v-for="product in cart.items">
              <div class="w-20 h-20 rounded-lg bg-blue-800 flex items-center justify-center mr-4 flex-shrink-0">
                <img :src="products[product.productId]?.imageUrl" alt="">
              </div>
              <div class="flex-grow">
                <div class="flex justify-between">
                  <h3 class="font-bold text-lg"><RouterLink :to="`/product/${product.productId}`">{{ products[product.productId]?.name || 'Chargement...' }}</RouterLink></h3>
                  <p class="text-blue-200 mb-2">
                    Catégorie: {{ products[product.productId]?.category }}<br>
                    Description: {{ products[product.productId]?.description }}
                  </p>
                  <p class="font-bold text-xl">{{ products[product.productId]?.price }} €</p>
                </div>
                <div class="flex justify-between items-center">
                  <div class="flex items-center bg-blue-800 rounded-lg">
                    <button @click="decreaseQuantity(product)" class="px-3 py-1 text-blue-300 hover:text-white">-</button>
                    <input class="w-10 h-10" :value=product.quantity disabled>
                    <button @click="increaseQuantity(product)" class="px-3 py-1 text-blue-300 hover:text-white">+</button>
                  </div>
                  <button @click="removeProduct(product.productId)" class="text-red-400 hover:text-red-300 flex items-center">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                    </svg>
                    Supprimer
                  </button>
                </div>
              </div>
            </li>
          </ul>
        </div>
        
        <!-- Résumé de la commande -->
        <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 mb-4">
          <h2 class="text-xl font-bold mb-4 border-b border-blue-600 pb-2">Résumé de la commande</h2>
          <div class="space-y-3">
            <div class="flex justify-between">
              <span>Sous-total</span>
              <span>{{ subTotal.toFixed(2) }} €</span>
            </div>
            <div class="flex justify-between">
              <span>Frais de livraison</span>
              <span>Gratuit</span>
            </div>
            <div class="flex justify-between">
              <span>TVA (20%)</span>
              <span>{{ tax.toFixed(2) }} €</span>
            </div>
            <div class="flex justify-between font-bold text-xl pt-3 border-t border-blue-600">
              <span>Total</span>
              <span>{{ total.toFixed(2) }} €</span>
            </div>
          </div>
        </div>
        
        <!-- Code promo et boutons d'action -->
        <div class="flex flex-col md:flex-row gap-4">
          <div class="bg-blue-700 bg-opacity-70 rounded-lg shadow-lg p-6 flex-grow md:w-1/2">
            <h2 class="text-lg font-bold mb-2">Code promo</h2>
            <div class="flex">
              <input type="text" placeholder="Entrez votre code" class="rounded-l-lg px-4 py-2 w-full bg-blue-800 border-none focus:ring-2 focus:ring-blue-500 text-white placeholder-blue-300 focus:outline-none" />
              <button class="bg-blue-600 hover:bg-blue-500 text-white font-bold px-4 py-2 rounded-r-lg">
                Appliquer
              </button>
            </div>
          </div>
          
          <div class="flex gap-4 md:w-1/2">
            <RouterLink to="/products" class="bg-transparent hover:bg-blue-600 border border-blue-500 text-white font-bold px-4 py-2 rounded-lg flex-1 flex items-center justify-center">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 17l-5-5m0 0l5-5m-5 5h12" />
              </svg>
              Continuer
            </RouterLink>
            <button class="bg-green-600 hover:bg-green-500 text-white font-bold px-4 py-2 rounded-lg flex-1 flex items-center justify-center"
              @click="checkout()">
              Commander
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 ml-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6" />
              </svg>
            </button>
          </div>
        </div>
      </div>
    </main>
</template>
  
<script>
  import axios from 'axios';

  export default {
    name: "cart",
    data() {
      return {
        cart: {
          items: [],
        },
        products: {},
        total : null,
      };
    },
    created() {
      this.getUserCart();
      const user = this.$route.params.id;
      this.getUser(user);
    },
    computed: {
      subTotal() {
        return this.cart.items.reduce((sum, item) => {
          const product = this.products[item.productId];
          return sum + (product ? product.price * item.quantity : 0);
        }, 0);
      },
      tax() {
        return this.subTotal * 0.2;
      },
      total() {
        this.total = this.subTotal + this.tax;
        console.log('Total ', this.total);
        return this.subTotal + this.tax;
      }
    },
    methods: {
      async getUserCart() {
        const userId = this.$route.params.id;

        try {
          const response = await axios.get(`http://localhost:8080/api/carts/cart/${userId}`);

          this.cart = response.data;
          console.log(this.cart.items);

          for (const item of this.cart.items) {
            await this.getProduct(item.productId);
          }
        } catch (error) {
          console.error('Error fetching user Cart : ', error);
        }
      },
      async getProduct(productId) {
        if (this.products[productId]) {
          return this.products[productId];
        }

        try {
          const response = await axios.get(`http://localhost:8080/api/products/${productId}`);
          const product = response.data;
          this.products[productId] = product;
          return product;
        } catch (error) {
          console.error('Error fetching product ', error);
        }
      },
      increaseQuantity(product) {
        product.quantity++;
        this.updateProductQuantity(product.productId, product.quantity);
      },
      decreaseQuantity(product) {
        if (product.quantity > 1) {
          product.quantity--;
          this.updateProductQuantity(product.productId, product.quantity);
        }
      },
      async updateProductQuantity(productId, quantity) {
        const userId = this.$route.params.id;

        try {
          const response = await axios.put(`http://localhost:8080/api/carts/${userId}`, {
            "productId": productId,
            "quantity": quantity
          });
          console.log(response.data);
        } catch (error) {
          console.error('Error updating product : ', error);
        }
      },
      async removeProduct(productId) {
        const userId = this.$route.params.id;

        try {
          const response = await axios.delete(`http://localhost:8080/api/carts/${userId}/${productId}`);

          console.log(response);
          alert('Produit supprime du panier');
          window.location.reload();
        } catch (error) {
          console.error('Error removing product : ', error);
        }
      },
      async clearCart() {
        const userId = this.$route.params.id;

        try {
          const response = await axios.delete(`http://localhost:8080/api/carts/${userId}`);

          console.log(response.data);
          alert('Panier re-initialise !');
          window.location.reload();
        } catch (error) {
          console.error('Error clear Cart', error);
        }
      },
      async checkout() {
        const user = this.$route.params.id;

        const body = {
          amount: Math.round(this.total * 100),
          currency: "eur",
          successUrl: `http://localhost:5173/success/${user}`,
          cancelUrl: "http://localhost:5173/cancel",
          customerEmail: this.getUser(user),
          metadata: {
            userId: user,
            orderRef: `CMD-${new Date().getFullYear()}-${Math.floor(Math.random() * 1000)}`
          }
        };

        try {
          const response = await axios.post('http://localhost:8080/api/payment', body);
          window.location.href = response.data.url;
        } catch (error) {
          console.error("Erreur lors de la création de la session de paiement :", error);
          alert("Une erreur est survenue lors du paiement.");
        }
      },
      async getUser(userId) {
        try {
          const response = await axios.get(`http://localhost:8080/api/users/${userId}`);

          console.log(response.data.email);
        } catch (error) {
          console.error('Error getting user ', error);
        }
      }
    }
  }
</script>