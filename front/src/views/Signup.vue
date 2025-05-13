<template>
    <main class="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-900 to-blue-700 py-8">
      <div class="bg-white rounded-lg shadow-xl p-8 max-w-md w-full">
        <!-- Logo et en-tête -->
        <div class="text-center mb-6">
          <div class="flex justify-center mb-4">
            <div class="p-3 bg-blue-100 rounded-full">
              <!-- Icône de sport (ballon stylisé) -->
              <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 text-blue-700" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <circle cx="12" cy="12" r="10" stroke-width="1.5" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M4.93 4.93l4.24 4.24M14.83 14.83l4.24 4.24M14.83 9.17l4.24-4.24M4.93 19.07l4.24-4.24" />
              </svg>
            </div>
          </div>
          <h1 class="text-3xl font-bold text-gray-800">SPORT<span class="text-blue-700">ZONE</span></h1>
          <p class="text-gray-600 mt-2">Créez votre compte</p>
        </div>
  
        <!-- Formulaire d'inscription -->
        <form class="space-y-4">
          <!-- Nom et prénom -->
          <div class="grid grid-cols-2 gap-4">
            <div>
              <label for="firstname" class="block text-sm font-medium text-gray-700 mb-1">Prénom</label>
              <input 
                type="text" 
                id="firstname" 
                v-model="firstname"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Jean"
              />
            </div>
            <div>
              <label for="lastname" class="block text-sm font-medium text-gray-700 mb-1">Nom</label>
              <input 
                type="text" 
                id="lastname" 
                v-model="lastname"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Dupont"
              />
            </div>
          </div>
          
          <!-- Email -->
          <div>
            <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input 
              type="email" 
              id="email" 
              v-model="email"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="votre@email.com"
            />
          </div>
          
          <!-- Mot de passe -->
          <div>
            <label for="password" class="block text-sm font-medium text-gray-700 mb-1">Mot de passe</label>
            <input 
              type="password" 
              id="password" 
              v-model="password"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="••••••••"
            />
            <p class="text-xs text-gray-500 mt-1">Minimum 8 caractères avec lettres et chiffres</p>
          </div>
          
          <!-- Confirmation mot de passe -->
          <div>
            <label for="confirmPassword" class="block text-sm font-medium text-gray-700 mb-1">Confirmez le mot de passe</label>
            <input 
              type="password" 
              id="confirmPassword" 
              v-model="confirmPassword"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="••••••••"
            />
          </div>
          
          <!-- Sport favori -->
          <div>
            <label for="favoriteSport" class="block text-sm font-medium text-gray-700 mb-1">Sport favori</label>
            <select 
              id="favoriteSport" 
              v-model="favoriteSport"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="" disabled selected>Sélectionnez un sport</option>
              <option value="football">Football</option>
              <option value="basketball">Basketball</option>
              <option value="tennis">Tennis</option>
              <option value="running">Course à pied</option>
              <option value="swimming">Natation</option>
              <option value="cycling">Cyclisme</option>
              <option value="other">Autre</option>
            </select>
          </div>
          
          <!-- Conditions générales -->
          <div class="flex items-start mt-4">
            <div class="flex items-center h-5">
              <input 
                id="terms" 
                type="checkbox" 
                v-model="acceptTerms"
                class="h-4 w-4 text-blue-700 focus:ring-blue-500 border-gray-300 rounded"
              />
            </div>
            <div class="ml-3 text-sm">
              <label for="terms" class="text-gray-600">
                J'accepte les <a href="#" class="text-blue-700 hover:underline">conditions générales</a> et la <a href="#" class="text-blue-700 hover:underline">politique de confidentialité</a>
              </label>
            </div>
          </div>
          
          <!-- Newsletter -->
          <div class="flex items-start">
            <div class="flex items-center h-5">
              <input 
                id="newsletter" 
                type="checkbox" 
                v-model="newsletter"
                class="h-4 w-4 text-blue-700 focus:ring-blue-500 border-gray-300 rounded"
              />
            </div>
            <div class="ml-3 text-sm">
              <label for="newsletter" class="text-gray-600">
                Je souhaite recevoir les actualités et offres sportives par email
              </label>
            </div>
          </div>
          
          <!-- Bouton d'inscription -->
          <button 
            type="submit" 
            class="w-full bg-blue-700 hover:bg-blue-800 text-white font-bold py-3 px-4 rounded-lg transition duration-200 flex items-center justify-center mt-6"
            @click.prevent="handleSignup"
          >
            Créer mon compte
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 ml-2" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M10.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L12.586 11H5a1 1 0 110-2h7.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd" />
            </svg>
          </button>
        </form>
        
        <!-- Séparateur -->
        <div class="my-6 flex items-center">
          <div class="flex-grow border-t border-gray-300"></div>
          <span class="px-4 text-sm text-gray-500">Ou inscrivez-vous avec</span>
          <div class="flex-grow border-t border-gray-300"></div>
        </div>
        
        <!-- Autres options d'inscription -->
        <div class="grid grid-cols-2 gap-4">
          <button class="flex items-center justify-center py-2 px-4 border border-gray-300 rounded-lg hover:bg-gray-50">
            <svg class="h-5 w-5 mr-2" viewBox="0 0 24 24" fill="#4285F4">
              <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" />
              <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853" />
              <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05" />
              <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335" />
            </svg>
            Google
          </button>
          <button class="flex items-center justify-center py-2 px-4 border border-gray-300 rounded-lg hover:bg-gray-50">
            <svg class="h-5 w-5 mr-2" viewBox="0 0 24 24" fill="#1877F2">
              <path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z" />
            </svg>
            Facebook
          </button>
        </div>
        
        <!-- Lien de connexion -->
        <div class="text-center mt-6">
          <p class="text-sm text-gray-600">
            Déjà membre? 
            <a href="#" class="font-medium text-blue-700 hover:text-blue-800">Connectez-vous</a>
          </p>
        </div>
      </div>
    </main>
</template>
  
<script>
  export default {
    name: "Signup",
    data() {
      return {
        firstname: '',
        lastname: '',
        email: '',
        password: '',
        confirmPassword: '',
        favoriteSport: '',
        acceptTerms: false,
        newsletter: false
      }
    },
    methods: {
      handleSignup() {
        // Vérification que les mots de passe correspondent
        if (this.password !== this.confirmPassword) {
          alert('Les mots de passe ne correspondent pas');
          return;
        }
        
        // Vérification que les conditions sont acceptées
        if (!this.acceptTerms) {
          alert('Vous devez accepter les conditions générales');
          return;
        }
        
        // Logique d'inscription ici
        console.log('Création de compte pour:', {
          nom: this.lastname,
          prenom: this.firstname,
          email: this.email,
          sportFavori: this.favoriteSport,
          newsletter: this.newsletter
        });
      }
    }
  }
</script>