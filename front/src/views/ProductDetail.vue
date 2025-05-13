<template>
    <main class="min-h-screen bg-gradient-to-br from-blue-900 to-blue-700 text-white">
      <!-- Breadcrumb Navigation -->
      <div class="max-w-7xl mx-auto px-4 py-4">
        <div class="flex items-center text-sm text-blue-200">
          <a href="#" class="hover:text-white">Accueil</a>
          <span class="mx-2">/</span>
          <a href="#" class="hover:text-white">Football</a>
          <span class="mx-2">/</span>
          <span class="text-yellow-400">Ballon de football Pro</span>
        </div>
      </div>
  
      <!-- Product Detail Section -->
      <section class="max-w-7xl mx-auto px-4 py-8">
        <div class="bg-blue-800 rounded-lg shadow-xl overflow-hidden">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <!-- Product Images -->
            <div class="p-6">
              <!-- Main Image -->
              <div class="bg-blue-700 aspect-square rounded-lg mb-4 flex items-center justify-center">
                <span class="text-4xl text-blue-400">Image principale</span>
              </div>
              
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
              
              <h1 class="text-3xl font-bold mb-2">Ballon de football Pro</h1>
              
              <div class="flex items-center gap-2 mb-4">
                <div class="text-yellow-400 flex">
                  <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                </div>
                <span class="text-blue-200">(127 avis)</span>
              </div>
              
              <div class="mb-6">
                <span class="text-3xl font-bold text-yellow-400">89,99 €</span>
                <p class="text-green-400 mt-1">En stock - Livraison sous 24h</p>
              </div>
              
              <p class="text-blue-100 mb-6">
                Ballon officiel qualité match, conçu pour les performances professionnelles. 
                Fabriqué avec une technologie avancée qui assure une trajectoire précise, 
                un toucher exceptionnel et une durabilité supérieure même sur les terrains 
                les plus exigeants.
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
                  <input type="number" value="1" min="1" class="bg-blue-700 border-y border-blue-600 w-16 py-1 text-center" />
                  <button class="bg-blue-900 px-3 py-1 rounded-r-lg text-xl font-bold">+</button>
                </div>
              </div>
              
              <!-- Action Buttons -->
              <div class="flex flex-col sm:flex-row gap-4 mb-6">
                <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-3 px-6 rounded-lg flex-1 flex items-center justify-center gap-2 transition duration-300">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M3 1a1 1 0 000 2h1.22l.305 1.222a.997.997 0 00.01.042l1.358 5.43-.893.892C3.74 11.846 4.632 14 6.414 14H15a1 1 0 000-2H6.414l1-1H14a1 1 0 00.894-.553l3-6A1 1 0 0017 3H6.28l-.31-1.243A1 1 0 005 1H3zM16 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zM6.5 18a1.5 1.5 0 100-3 1.5 1.5 0 000 3z" />
                  </svg>
                  AJOUTER AU PANIER
                </button>
                <button class="border-2 border-white hover:bg-white hover:text-blue-900 font-bold py-3 px-6 rounded-lg flex items-center justify-center gap-2 transition duration-300">
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
          <h2 class="text-2xl font-bold mb-6">Avis clients</h2>
          
          <!-- Reviews Summary -->
          <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
            <!-- Average Rating -->
            <div class="bg-blue-700 rounded-lg p-6 text-center">
              <div class="text-6xl font-bold text-yellow-400 mb-2">4.9</div>
              <div class="flex justify-center text-yellow-400 mb-2">
                <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
              </div>
              <p class="text-blue-200">Basé sur 127 avis</p>
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
              <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-2 px-6 rounded-lg transition duration-300">
                ÉCRIRE UN AVIS
              </button>
            </div>
          </div>
          
          <!-- Individual Reviews -->
          <div class="space-y-6">
            <!-- Review 1 -->
            <div class="border-b border-blue-700 pb-6">
              <div class="flex justify-between mb-2">
                <div class="font-semibold">Thomas D.</div>
                <div class="text-blue-200 text-sm">Il y a 2 jours</div>
              </div>
              <div class="text-yellow-400 mb-2">★★★★★</div>
              <h4 class="font-semibold mb-2">Excellent ballon pour les matchs officiels</h4>
              <p class="text-blue-200 mb-4">
                J'utilise ce ballon depuis plusieurs mois maintenant pour nos matchs de championnat, 
                et je dois dire que la qualité est impressionnante. Le toucher est parfait, le rebond 
                est régulier et la durabilité est au rendez-vous malgré une utilisation intensive.
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
            
            <!-- Review 2 -->
            <div class="border-b border-blue-700 pb-6">
              <div class="flex justify-between mb-2">
                <div class="font-semibold">Sophie M.</div>
                <div class="text-blue-200 text-sm">Il y a 5 jours</div>
              </div>
              <div class="text-yellow-400 mb-2">★★★★★</div>
              <h4 class="font-semibold mb-2">Bon investissement pour notre club</h4>
              <p class="text-blue-200 mb-4">
                Notre club amateur a investi dans plusieurs ballons de ce modèle et nous sommes 
                très satisfaits. Ils conservent bien leur forme et leur pression même après plusieurs 
                matchs. De plus, la prise en main est excellente même sous la pluie. Je recommande vivement.
              </p>
              <div class="flex gap-4">
                <button class="text-blue-200 text-sm flex items-center gap-1 hover:text-white">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M2 10.5a1.5 1.5 0 113 0v6a1.5 1.5 0 01-3 0v-6zM6 10.333v5.43a2 2 0 001.106 1.79l.05.025A4 4 0 008.943 18h5.416a2 2 0 001.962-1.608l1.2-6A2 2 0 0015.56 8H12V4a2 2 0 00-2-2 1 1 0 00-1 1v.667a4 4 0 01-.8 2.4L6.8 7.933a4 4 0 00-.8 2.4z" />
                  </svg>
                  Utile (18)
                </button>
                <button class="text-blue-200 text-sm hover:text-white">
                  Signaler
                </button>
              </div>
            </div>
            
            <!-- See All Reviews Button -->
            <div class="text-center">
              <button class="bg-blue-700 hover:bg-blue-600 py-2 px-6 rounded-lg transition duration-300">
                VOIR TOUS LES AVIS (127)
              </button>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Related Products Section -->
      <section class="max-w-7xl mx-auto px-4 py-8 mb-8">
        <h2 class="text-2xl font-bold mb-6">Produits similaires</h2>
        
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <!-- Related Product 1 -->
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-4">
              <h3 class="font-bold text-lg mb-1">Ballon d'entraînement</h3>
              <div class="flex text-yellow-400 mb-2">★★★★☆</div>
              <div class="flex justify-between items-center">
                <p class="text-yellow-400 font-bold">49,99 €</p>
                <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-1 px-3 rounded-lg transition duration-300">
                  Ajouter
                </button>
              </div>
            </div>
          </div>
          <!-- Related Product 2 -->
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-4">
              <h3 class="font-bold text-lg mb-1">Pompe à ballon</h3>
              <div class="flex text-yellow-400 mb-2">★★★★★</div>
              <div class="flex justify-between items-center">
                <p class="text-yellow-400 font-bold">15,99 €</p>
                <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-1 px-3 rounded-lg transition duration-300">
                  Ajouter
                </button>
              </div>
            </div>
          </div>
          <!-- Related Product 3 -->
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-4">
              <h3 class="font-bold text-lg mb-1">Gants de gardien Pro</h3>
              <div class="flex text-yellow-400 mb-2">★★★★★</div>
              <div class="flex justify-between items-center">
                <p class="text-yellow-400 font-bold">69,99 €</p>
                <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-1 px-3 rounded-lg transition duration-300">
                  Ajouter
                </button>
              </div>
            </div>
          </div>
          <!-- Related Product 4 -->
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg transition-transform duration-300 hover:shadow-xl hover:scale-105">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-4">
              <h3 class="font-bold text-lg mb-1">But d'entraînement</h3>
              <div class="flex text-yellow-400 mb-2">★★★★☆</div>
              <div class="flex justify-between items-center">
                <p class="text-yellow-400 font-bold">129,99 €</p>
                <button class="bg-yellow-500 hover:bg-yellow-600 text-black font-bold py-1 px-3 rounded-lg transition duration-300">
                  Ajouter
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Recently Viewed -->
      <section class="max-w-7xl mx-auto px-4 py-8 mb-12">
        <h2 class="text-2xl font-bold mb-6">Récemment consultés</h2>
        
        <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-4">
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-2">
              <h3 class="font-semibold text-sm truncate">Chaussures de football</h3>
              <p class="text-yellow-400 font-bold text-sm">159,99 €</p>
            </div>
          </div>
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-2">
              <h3 class="font-semibold text-sm truncate">Short d'entraînement</h3>
              <p class="text-yellow-400 font-bold text-sm">34,99 €</p>
            </div>
          </div>
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-2">
              <h3 class="font-semibold text-sm truncate">Protège-tibias</h3>
              <p class="text-yellow-400 font-bold text-sm">24,99 €</p>
            </div>
          </div>
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg md:block hidden">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-2">
              <h3 class="font-semibold text-sm truncate">Kit d'entraînement</h3>
              <p class="text-yellow-400 font-bold text-sm">79,99 €</p>
            </div>
          </div>
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg lg:block hidden">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-2">
              <h3 class="font-semibold text-sm truncate">Sac de sport</h3>
              <p class="text-yellow-400 font-bold text-sm">49,99 €</p>
            </div>
          </div>
          <div class="bg-blue-800 rounded-lg overflow-hidden shadow-lg lg:block hidden">
            <div class="aspect-square bg-blue-700"></div>
            <div class="p-2">
              <h3 class="font-semibold text-sm truncate">Bouteille isotherme</h3>
              <p class="text-yellow-400 font-bold text-sm">19,99 €</p>
            </div>
          </div>
        </div>
      </section>
    </main>
</template>
  
<script>
  export default {
    name: "ProductDetail"
  }
</script>