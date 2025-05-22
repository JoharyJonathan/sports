<template>
    <main class="bg-blue-800 min-h-screen flex items-center justify-center text-white">
      <div class="text-center max-w-md mx-auto px-6">
        <!-- Animation de succès -->
        <div class="relative mb-8">
          <div class="w-24 h-24 mx-auto bg-green-500 rounded-full flex items-center justify-center animate-pulse">
            <svg class="w-12 h-12 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="3" d="M5 13l4 4L19 7"></path>
            </svg>
          </div>
          <!-- Confettis décoratifs -->
          <div class="absolute -top-2 -left-2 text-yellow-400 text-2xl animate-bounce">🎉</div>
          <div class="absolute -top-4 -right-4 text-yellow-400 text-xl animate-bounce" style="animation-delay: 0.2s">✨</div>
          <div class="absolute -bottom-2 -left-4 text-yellow-400 text-lg animate-bounce" style="animation-delay: 0.4s">🏆</div>
          <div class="absolute -bottom-2 -right-2 text-yellow-400 text-xl animate-bounce" style="animation-delay: 0.6s">⚽</div>
        </div>
  
        <!-- Message principal -->
        <h1 class="text-3xl font-bold mb-4 text-green-400">Paiement Réussi !</h1>
        <p class="text-lg mb-6 text-blue-200">
          Félicitations ! Votre paiement a été traité avec succès.
        </p>
  
        <!-- Détails du paiement -->
        <div class="bg-blue-900 rounded-lg p-6 mb-6 text-left">
          <h2 class="text-xl font-semibold mb-4 text-center">Détails de votre achat</h2>
          <div class="space-y-3">
            <div class="flex justify-between">
              <span class="text-blue-300">Abonnement :</span>
              <span class="font-medium">Premium Sport</span>
            </div>
            <div class="flex justify-between">
              <span class="text-blue-300">Montant :</span>
              <span class="font-medium text-green-400">29,99€</span>
            </div>
            <div class="flex justify-between">
              <span class="text-blue-300">Date :</span>
              <span class="font-medium">18 Mai 2025</span>
            </div>
            <div class="flex justify-between">
              <span class="text-blue-300">Référence :</span>
              <span class="font-medium text-sm">#SP-2025-0518</span>
            </div>
          </div>
        </div>
  
        <!-- Avantages débloqués -->
        <div class="bg-blue-700 rounded-lg p-6 mb-8">
          <h3 class="text-lg font-semibold mb-4">Avantages débloqués</h3>
          <div class="grid grid-cols-2 gap-3 text-sm">
            <div class="flex items-center">
              <span class="mr-2">🏋️</span>
              <span>Accès premium</span>
            </div>
            <div class="flex items-center">
              <span class="mr-2">📊</span>
              <span>Statistiques avancées</span>
            </div>
            <div class="flex items-center">
              <span class="mr-2">👥</span>
              <span>Coaching personnalisé</span>
            </div>
            <div class="flex items-center">
              <span class="mr-2">🏆</span>
              <span>Tournois exclusifs</span>
            </div>
          </div>
        </div>
  
        <!-- Boutons d'action -->
        <div class="space-y-3">
          <button class="w-full bg-green-600 hover:bg-green-700 text-white font-medium py-3 px-6 rounded-lg transition-colors duration-200">
            Commencer l'expérience Premium
          </button>
          <button class="w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-3 px-6 rounded-lg transition-colors duration-200">
            Télécharger le reçu
          </button>
          <button class="w-full border border-blue-400 text-blue-200 hover:bg-blue-700 font-medium py-3 px-6 rounded-lg transition-colors duration-200">
            Retour à l'accueil
          </button>
        </div>
  
        <!-- Message de remerciement -->
        <div class="mt-8 p-4 bg-blue-900 rounded-lg">
          <p class="text-sm text-blue-300">
            🙏 Merci de votre confiance ! Une confirmation a été envoyée à votre email.
          </p>
        </div>
      </div>
  
      <!-- Particules de fond -->
      <div class="absolute inset-0 overflow-hidden pointer-events-none">
        <div class="absolute top-10 left-10 w-4 h-4 bg-blue-400 rounded-full opacity-20 animate-ping" style="animation-delay: 1s"></div>
        <div class="absolute top-32 right-20 w-6 h-6 bg-blue-300 rounded-full opacity-30 animate-ping" style="animation-delay: 2s"></div>
        <div class="absolute bottom-40 left-20 w-3 h-3 bg-blue-500 rounded-full opacity-25 animate-ping" style="animation-delay: 3s"></div>
        <div class="absolute bottom-20 right-32 w-5 h-5 bg-blue-400 rounded-full opacity-20 animate-ping" style="animation-delay: 4s"></div>
      </div>
    </main>
</template>
  
<script>
  import axios from 'axios';

  export default {
    name: "Success",
    created() {
      const userId = this.$route.params.id;
      this.updateCartStatus(userId);
    },
    mounted() {
      console.log('Paiement réussi - Page Success chargée');
    },
    methods: {
      goToHome() {
        // Méthode pour retourner à l'accueil
        this.$router.push('/');
      },
      startPremium() {
        // Méthode pour commencer l'expérience premium
        this.$router.push('/dashboard');
      },
      downloadReceipt() {
        // Méthode pour télécharger le reçu
        console.log('Téléchargement du reçu...');
      },
      async updateCartStatus(userId) {
        try {
          const response = await axios.patch(`http://localhost:8080/api/carts/update-status/${userId}`);

          console.log(response.data);
        } catch (error) {
          console.error('Error updating Cart status : ', error);
        }
      }
    }
  }
</script>