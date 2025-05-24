<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 py-8">
      <div class="max-w-lg mx-auto p-6">
        <!-- Card Container -->
        <div class="bg-white rounded-2xl shadow-xl overflow-hidden">
          <!-- Header -->
          <div class="bg-gradient-to-r from-blue-600 to-indigo-600 px-6 py-4">
            <h2 class="text-2xl font-bold text-white flex items-center">
              <svg class="w-6 h-6 mr-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
              </svg>
              Éditer le Profil
            </h2>
            <p class="text-blue-100 text-sm mt-1">Modifiez vos informations personnelles</p>
          </div>
  
          <!-- Form -->
          <form @submit.prevent="updateProfile" class="p-6 space-y-6">
            <!-- Username -->
            <div class="space-y-2">
              <label class="block text-sm font-semibold text-gray-700 flex items-center">
                <svg class="w-4 h-4 mr-2 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                </svg>
                Nom d'utilisateur
              </label>
              <div class="relative">
                <input
                  type="text"
                  v-model="user.username"
                  class="w-full bg-gray-50 border border-gray-200 rounded-lg px-4 py-3 pl-10 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 hover:bg-gray-100"
                  :class="{'border-red-300 bg-red-50': !user.username && attempted}"
                  placeholder=""
                  required
                />
                <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                  <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                  </svg>
                </div>
              </div>
            </div>
  
            <!-- Email -->
            <div class="space-y-2">
              <label class="block text-sm font-semibold text-gray-700 flex items-center">
                <svg class="w-4 h-4 mr-2 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"></path>
                </svg>
                Adresse email
              </label>
              <div class="relative">
                <input
                  type="email"
                  v-model="user.email"
                  class="w-full bg-gray-50 border border-gray-200 rounded-lg px-4 py-3 pl-10 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 hover:bg-gray-100"
                  :class="{'border-red-300 bg-red-50': !user.email && attempted}"
                  placeholder=""
                  required
                />
                <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                  <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"></path>
                  </svg>
                </div>
              </div>
            </div>
  
            <!-- Password -->
            <div class="space-y-2">
              <label class="block text-sm font-semibold text-gray-700 flex items-center">
                <svg class="w-4 h-4 mr-2 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"></path>
                </svg>
                Mot de passe
                <span class="text-xs text-gray-500 ml-2">(Laisser vide pour ne pas changer)</span>
              </label>
              <div class="relative">
                <input
                  :type="showPassword ? 'text' : 'password'"
                  v-model="user.password"
                  class="w-full bg-gray-50 border border-gray-200 rounded-lg px-4 py-3 pl-10 pr-10 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 hover:bg-gray-100"
                  placeholder="Nouveau mot de passe (optionnel)"
                />
                <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                  <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"></path>
                  </svg>
                </div>
                <button
                  type="button"
                  @click="showPassword = !showPassword"
                  class="absolute inset-y-0 right-0 flex items-center pr-3 text-gray-400 hover:text-gray-600"
                >
                  <svg v-if="!showPassword" class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path>
                  </svg>
                  <svg v-else class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L3 3m6.878 6.878L21 21"></path>
                  </svg>
                </button>
              </div>
            </div>
  
            <!-- Category -->
            <div class="space-y-2">
              <label class="block text-sm font-semibold text-gray-700 flex items-center">
                <svg class="w-4 h-4 mr-2 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z"></path>
                </svg>
                Catégorie
              </label>
              <select
                v-model="user.category"
                class="w-full bg-gray-50 border border-gray-200 rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 hover:bg-gray-100"
                :class="{'border-red-300 bg-red-50': !user.category && attempted}"
                required
              >
                <option disabled value="">Sélectionnez une catégorie</option>
                <option value="BasketBall">BasketBall</option>
                <option value="FootBall">FootBall</option>
                <option value="VolleyBall">VolleyBall</option>
                <option value="Running">Running</option>
                <option value="Tennis">Tennis</option>
              </select>
            </div>

  
            <!-- Action Buttons -->
            <div class="flex space-x-4 pt-4">
              <!-- Cancel Button -->
              <button
                type="button"
                @click="cancelEdit"
                class="flex-1 bg-gray-100 text-gray-700 py-3 px-6 rounded-lg font-semibold hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2 transition-all duration-200"
              >
                <span class="flex items-center justify-center">
                  <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                  </svg>
                  Annuler
                </span>
              </button>
  
              <!-- Save Button -->
              <button
                type="submit"
                :disabled="isLoading"
                class="flex-1 bg-gradient-to-r from-blue-600 to-indigo-600 text-white py-3 px-6 rounded-lg font-semibold shadow-lg hover:from-blue-700 hover:to-indigo-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transform transition-all duration-200 hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none"
              >
                <span v-if="!isLoading" class="flex items-center justify-center">
                  <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                  </svg>
                  Sauvegarder
                </span>
                <span v-else class="flex items-center justify-center">
                  <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  Sauvegarde...
                </span>
              </button>
            </div>
          </form>
        </div>
  
        <!-- Success Message -->
        <div v-if="showSuccess" class="mt-4 bg-green-50 border border-green-200 rounded-lg p-4 animate-fade-in">
          <div class="flex items-center">
            <svg class="w-5 h-5 text-green-600 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <span class="text-green-800 font-medium">Profil mis à jour avec succès !</span>
          </div>
        </div>
  
        <!-- Error Message -->
        <div v-if="showError" class="mt-4 bg-red-50 border border-red-200 rounded-lg p-4 animate-fade-in">
          <div class="flex items-center">
            <svg class="w-5 h-5 text-red-600 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            <span class="text-red-800 font-medium">{{ errorMessage }}</span>
          </div>
        </div>
      </div>
    </main>
</template>
  
<script>
  import axios from 'axios'

  export default {
    name: 'edit-profile',
    data() {
      return {
        user: [],
      }
    },
    created() {
      this.getProfile();
    },
    methods: {
      async getProfile() {
        const userId = this.$route.params.id;

        try {
          const response = await axios.get(`http://localhost:8080/api/users/${userId}`);

          this.user = response.data;
          console.log(this.user);
        } catch (error) {
          console.error('Error fetching user profile !', error);
        }
      },
      async updateProfile() {
        const userId = this.$route.params.id;

        const payload = {
          "username": this.user.username,
          "email": this.user.email,
          "password": this.user.password || undefined,
          "category": this.user.category
        }

        try {
          const response = await axios.put(`http://localhost:8080/api/users/${userId}`, payload);

          console.log(response.data);
          alert('Profil modifie !');
          this.$router.push(`/profile/${userId}`);
        } catch (error) {
          console.error('Error updating user profile ', error);
        }
      }
    }
  }
</script>
  
<style scoped>
  @keyframes fade-in {
    from { opacity: 0; transform: translateY(10px); }
    to { opacity: 1; transform: translateY(0); }
  }
  
  .animate-fade-in {
    animation: fade-in 0.3s ease-out;
  }
  
  /* Custom scrollbar for selects */
  select::-webkit-scrollbar {
    width: 6px;
  }
  
  select::-webkit-scrollbar-track {
    background: #f1f1f1;
    border-radius: 3px;
  }
  
  select::-webkit-scrollbar-thumb {
    background: #c1c1c1;
    border-radius: 3px;
  }
  
  select::-webkit-scrollbar-thumb:hover {
    background: #a8a8a8;
  }
</style>