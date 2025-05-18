<template>
    <main class="bg-blue-800 min-h-screen flex items-center justify-center text-white">
      <div class="text-center max-w-md mx-auto px-6">
        <!-- Animation d'échec -->
        <div class="relative mb-8">
          <div class="w-24 h-24 mx-auto bg-red-500 rounded-full flex items-center justify-center animate-pulse">
            <svg class="w-12 h-12 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="3" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </div>
          <!-- Emojis décoratifs -->
          <div class="absolute -top-2 -left-2 text-gray-400 text-2xl animate-bounce">😔</div>
          <div class="absolute -top-4 -right-4 text-gray-400 text-xl animate-bounce" style="animation-delay: 0.2s">💳</div>
          <div class="absolute -bottom-2 -left-4 text-gray-400 text-lg animate-bounce" style="animation-delay: 0.4s">⚠️</div>
          <div class="absolute -bottom-2 -right-2 text-gray-400 text-xl animate-bounce" style="animation-delay: 0.6s">🔄</div>
        </div>
  
        <!-- Message principal -->
        <h1 class="text-3xl font-bold mb-4 text-red-400">Paiement Annulé</h1>
        <p class="text-lg mb-6 text-blue-200">
          Pas de problème ! Votre paiement a été annulé et aucun montant n'a été débité.
        </p>
  
        <!-- Raisons possibles -->
        <div class="bg-blue-900 rounded-lg p-6 mb-6 text-left">
          <h2 class="text-xl font-semibold mb-4 text-center text-yellow-400">Que s'est-il passé ?</h2>
          <div class="space-y-3 text-sm">
            <div class="flex items-start">
              <span class="mr-2 text-blue-300">•</span>
              <span>Vous avez choisi d'annuler la transaction</span>
            </div>
            <div class="flex items-start">
              <span class="mr-2 text-blue-300">•</span>
              <span>La session de paiement a expiré</span>
            </div>
            <div class="flex items-start">
              <span class="mr-2 text-blue-300">•</span>
              <span>Problème technique temporaire</span>
            </div>
            <div class="flex items-start">
              <span class="mr-2 text-blue-300">•</span>
              <span>Informations de carte incorrectes</span>
            </div>
          </div>
        </div>
  
        <!-- Offre alternative -->
        <div class="bg-blue-700 rounded-lg p-6 mb-8">
          <h3 class="text-lg font-semibold mb-4 text-green-400">Ne ratez pas nos avantages !</h3>
          <div class="grid grid-cols-2 gap-3 text-sm mb-4">
            <div class="flex items-center">
              <span class="mr-2">🏋️</span>
              <span>Accès premium</span>
            </div>
            <div class="flex items-center">
              <span class="mr-2">📊</span>
              <span>Suivi performance</span>
            </div>
            <div class="flex items-center">
              <span class="mr-2">👥</span>
              <span>Coaching expert</span>
            </div>
            <div class="flex items-center">
              <span class="mr-2">🏆</span>
              <span>Compétitions VIP</span>
            </div>
          </div>
          <div class="bg-blue-800 p-3 rounded-lg">
            <p class="text-xs text-blue-300">
              💡 Offre spéciale : <span class="text-yellow-400 font-semibold">-20%</span> sur votre premier mois !
            </p>
          </div>
        </div>
  
        <!-- Boutons d'action -->
        <div class="space-y-3">
          <button class="w-full bg-green-600 hover:bg-green-700 text-white font-medium py-3 px-6 rounded-lg transition-colors duration-200">
            Réessayer le paiement
          </button>
          <button class="w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-3 px-6 rounded-lg transition-colors duration-200">
            Choisir un autre plan
          </button>
          <button class="w-full border border-blue-400 text-blue-200 hover:bg-blue-700 font-medium py-3 px-6 rounded-lg transition-colors duration-200">
            Contacter le support
          </button>
          <button class="w-full border border-gray-500 text-gray-300 hover:bg-blue-700 font-medium py-3 px-6 rounded-lg transition-colors duration-200">
            Retour à l'accueil
          </button>
        </div>
  
        <!-- Message d'encouragement -->
        <div class="mt-8 p-4 bg-blue-900 rounded-lg">
          <p class="text-sm text-blue-300">
            🤝 Notre équipe support est là pour vous aider ! Contactez-nous si vous rencontrez des difficultés.
          </p>
        </div>
  
        <!-- Méthodes de paiement acceptées -->
        <div class="mt-6 p-4 bg-blue-700 rounded-lg">
          <p class="text-xs text-blue-300 mb-2">Méthodes de paiement sécurisées :</p>
          <div class="flex justify-center space-x-3 text-lg">
            <span>💳</span>
            <span>🏦</span>
            <span>📱</span>
            <span>💰</span>
          </div>
        </div>
      </div>
  
      <!-- Particules de fond (plus douces) -->
      <div class="absolute inset-0 overflow-hidden pointer-events-none">
        <div class="absolute top-10 left-10 w-3 h-3 bg-blue-500 rounded-full opacity-15 animate-ping" style="animation-delay: 1s"></div>
        <div class="absolute top-32 right-20 w-4 h-4 bg-blue-400 rounded-full opacity-20 animate-ping" style="animation-delay: 2s"></div>
        <div class="absolute bottom-40 left-20 w-2 h-2 bg-blue-600 rounded-full opacity-15 animate-ping" style="animation-delay: 3s"></div>
        <div class="absolute bottom-20 right-32 w-3 h-3 bg-blue-500 rounded-full opacity-15 animate-ping" style="animation-delay: 4s"></div>
      </div>
    </main>
</template>
  
<script>
  export default {
    name: "Cancel",
    mounted() {
      // Log de l'annulation pour suivi
      console.log('Paiement annulé - Page Cancel chargée');
    },
    methods: {
      retryPayment() {
        // Méthode pour relancer le processus de paiement
        this.$router.push('/payment');
      },
      choosePlan() {
        // Méthode pour choisir un autre plan
        this.$router.push('/plans');
      },
      contactSupport() {
        // Méthode pour contacter le support
        console.log('Redirection vers le support...');
        // Peut ouvrir un chat ou rediriger vers une page de contact
      },
      goToHome() {
        // Méthode pour retourner à l'accueil
        this.$router.push('/');
      }
    }
  }
</script>