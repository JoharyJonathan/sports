<template>
  <main class="bg-blue-800 min-h-screen text-white">
    <div class="container mx-auto px-4 py-6">
      <header class="flex justify-between items-center mb-8">
        <div class="flex items-center">
          <h1 class="text-3xl font-extrabold text-blue-200">Gestion des Utilisateurs</h1>
        </div>
        <div class="flex items-center space-x-4">
          <button
            @click="openCreateModal"
            class="bg-green-600 hover:bg-green-700 transition duration-300 px-5 py-2.5 rounded-lg flex items-center text-lg shadow-md"
          >
            <span class="mr-2 text-xl">➕</span>
            Nouvel utilisateur
          </button>
          <div class="flex items-center">
            <div class="w-10 h-10 rounded-full bg-gray-300 mr-2 flex items-center justify-center text-blue-900 font-bold text-xl">
              A
            </div>
            <span>Admin</span>
          </div>
        </div>
      </header>

      <div class="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
        <div class="bg-blue-700 rounded-xl p-5 shadow-lg transform hover:scale-105 transition-transform duration-300">
          <h3 class="text-xl font-semibold mb-2 text-blue-200">Total Utilisateurs</h3>
          <p class="text-4xl font-bold">{{ users.length }}</p>
          <p class="text-blue-300 text-sm mt-1">Tous profils confondus</p>
        </div>
        <div class="bg-blue-700 rounded-xl p-5 shadow-lg transform hover:scale-105 transition-transform duration-300">
          <h3 class="text-xl font-semibold mb-2 text-blue-200">BasketBall</h3>
          <p class="text-4xl font-bold">{{ getUsersByCategory('BasketBall').length }}</p>
          <p class="text-blue-300 text-sm mt-1">Joueurs de basketball</p>
        </div>
        <div class="bg-blue-700 rounded-xl p-5 shadow-lg transform hover:scale-105 transition-transform duration-300">
          <h3 class="text-xl font-semibold mb-2 text-blue-200">FootBall</h3>
          <p class="text-4xl font-bold">{{ getUsersByCategory('FootBall').length }}</p>
          <p class="text-blue-300 text-sm mt-1">Joueurs de football</p>
        </div>
        <div class="bg-blue-700 rounded-xl p-5 shadow-lg transform hover:scale-105 transition-transform duration-300">
          <h3 class="text-xl font-semibold mb-2 text-blue-200">VolleyBall</h3>
          <p class="text-4xl font-bold">{{ getUsersByCategory('VolleyBall').length }}</p>
          <p class="text-blue-300 text-sm mt-1">Joueurs de volleyball</p>
        </div>
      </div>

      <div class="bg-blue-900 rounded-xl shadow-lg p-6 mb-8">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-5">
          <div>
            <label for="search" class="block text-sm font-medium mb-2 text-blue-200">Rechercher</label>
            <input
              id="search"
              v-model="searchTerm"
              type="text"
              placeholder="Nom, email..."
              class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white placeholder-blue-300 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
            />
          </div>
          <div>
            <label for="categoryFilter" class="block text-sm font-medium mb-2 text-blue-200">Filtrer par catégorie</label>
            <select
              id="categoryFilter"
              v-model="filterCategory"
              class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
            >
              <option value="">Toutes les catégories</option>
              <option v-for="cat in categoryOptions" :key="cat" :value="cat">{{ cat }}</option>
            </select>
          </div>
          <div>
            <label for="statusFilter" class="block text-sm font-medium mb-2 text-blue-200">Statut</label>
            <select
              id="statusFilter"
              v-model="filterStatus"
              class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
            >
              <option value="">Tous les statuts</option>
              <option value="active">Actif</option>
              <option value="inactive">Inactif</option>
              <option value="suspended">Suspendu</option>
            </select>
          </div>
        </div>
      </div>

      <div class="bg-blue-900 rounded-xl shadow-lg p-6">
        <div class="flex justify-between items-center mb-5">
          <h2 class="text-2xl font-bold text-blue-200">Liste des Utilisateurs</h2>
          <div class="text-md text-blue-300">
            {{ filteredUsers.length }} utilisateur(s) trouvé(s)
          </div>
        </div>

        <div class="overflow-x-auto rounded-lg border border-blue-700">
          <table class="w-full min-w-[700px]">
            <thead>
              <tr class="bg-blue-700 text-blue-100 uppercase text-sm leading-normal">
                <th class="py-3 px-6 text-left">Avatar</th>
                <th class="py-3 px-6 text-left cursor-pointer hover:text-blue-300 transition-colors" @click="sortBy('username')">
                  Username
                  <span v-if="sortField === 'username'">{{ sortDirection === 'asc' ? ' ↑' : ' ↓' }}</span>
                </th>
                <th class="py-3 px-6 text-left cursor-pointer hover:text-blue-300 transition-colors" @click="sortBy('email')">
                  Email
                  <span v-if="sortField === 'email'">{{ sortDirection === 'asc' ? ' ↑' : ' ↓' }}</span>
                </th>
                <th class="py-3 px-6 text-left">Catégorie</th>
                <th class="py-3 px-6 text-left">Actions</th>
              </tr>
            </thead>
            <tbody class="text-gray-200 text-sm font-light">
              <tr
                v-for="user in paginatedUsers"
                :key="user.id"
                class="border-b border-blue-700 hover:bg-blue-800 transition-colors duration-200"
              >
                <td class="py-3 px-6">
                  <div class="w-10 h-10 rounded-full bg-gradient-to-r from-blue-400 to-purple-500 flex items-center justify-center text-white font-bold text-lg shadow-md">
                    {{ user.username.charAt(0).toUpperCase() }}
                  </div>
                </td>
                <td class="py-3 px-6 font-medium">{{ user.username }}</td>
                <td class="py-3 px-6 text-blue-300">{{ user.email }}</td>
                <td class="py-3 px-6">
                  <span :class="getCategoryBadgeClass(user.category)" class="text-xs px-3 py-1 rounded-full font-semibold shadow-sm">
                    {{ getCategoryLabel(user.category) }}
                  </span>
                </td>
                <td class="py-3 px-6">
                  <div class="flex space-x-2">
                    <button
                      @click="viewUser(user)"
                      class="text-blue-300 hover:text-blue-100 text-lg p-1 rounded-full hover:bg-blue-700 transition duration-200"
                      title="Voir les détails"
                    >
                      <i class="fas fa-eye">👁️</i>
                    </button>
                    <button
                      @click="editUser(user)"
                      class="text-green-400 hover:text-green-300 text-lg p-1 rounded-full hover:bg-blue-700 transition duration-200"
                      title="Éditer l'utilisateur"
                    >
                      <i class="fas fa-edit">✏️</i>
                    </button>
                    <button
                      @click="confirmDeleteUser(user)"
                      class="text-red-400 hover:text-red-300 text-lg p-1 rounded-full hover:bg-blue-700 transition duration-200"
                      title="Supprimer l'utilisateur"
                    >
                      <i class="fas fa-trash">🗑️</i>
                    </button>
                  </div>
                </td>
              </tr>
              <tr v-if="paginatedUsers.length === 0">
                <td colspan="5" class="py-6 text-center text-gray-400">Aucun utilisateur trouvé.</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="flex justify-between items-center mt-7">
          <div class="text-sm text-blue-300">
            Affichage {{ (currentPage - 1) * itemsPerPage + 1 }} à {{ Math.min(currentPage * itemsPerPage, filteredUsers.length) }}
            sur {{ filteredUsers.length }} utilisateurs
          </div>
          <div class="flex space-x-3">
            <button
              @click="currentPage--"
              :disabled="currentPage === 1"
              class="px-4 py-2 rounded-lg bg-blue-700 hover:bg-blue-600 disabled:opacity-50 disabled:cursor-not-allowed transition duration-200"
            >
              Précédent
            </button>
            <span class="px-4 py-2 font-medium text-blue-100">Page {{ currentPage }} sur {{ totalPages }}</span>
            <button
              @click="currentPage++"
              :disabled="currentPage === totalPages"
              class="px-4 py-2 rounded-lg bg-blue-700 hover:bg-blue-600 disabled:opacity-50 disabled:cursor-not-allowed transition duration-200"
            >
              Suivant
            </button>
          </div>
        </div>
      </div>

      <div v-if="showCreateModal || showEditModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50 p-4 animate-fade-in">
        <div class="bg-blue-900 rounded-xl p-8 w-full max-w-md mx-auto shadow-2xl transform scale-95 animate-zoom-in">
          <div class="flex justify-between items-center mb-6">
            <h3 class="text-2xl font-bold text-blue-200">
              {{ showCreateModal ? 'Nouvel Utilisateur' : 'Modifier Utilisateur' }}
            </h3>
            <button @click="closeModal" class="text-gray-400 hover:text-white text-3xl transition duration-200">×</button>
          </div>

          <form @submit.prevent="showCreateModal ? createUser() : updateUser()">
            <div class="space-y-5">
              <div>
                <label for="username" class="block text-sm font-medium mb-2 text-blue-200">Username</label>
                <input
                  id="username"
                  v-model="formData.username"
                  type="text"
                  required
                  class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
                  placeholder="Nom d'utilisateur"
                />
              </div>

              <div>
                <label for="email" class="block text-sm font-medium mb-2 text-blue-200">Email</label>
                <input
                  id="email"
                  v-model="formData.email"
                  type="email"
                  required
                  class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
                  placeholder="Adresse email"
                />
              </div>

              <div v-if="showCreateModal || (showEditModal && formData.password)">
                <label for="password" class="block text-sm font-medium mb-2 text-blue-200">
                  {{ showCreateModal ? 'Mot de passe' : 'Nouveau mot de passe (laisser vide pour ne pas changer)' }}
                </label>
                <input
                  id="password"
                  v-model="formData.password"
                  type="password"
                  :required="showCreateModal"
                  class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
                  :placeholder="showCreateModal ? 'Mot de passe' : 'Laisser vide pour ne pas changer'"
                />
              </div>

              <div>
                <label for="category" class="block text-sm font-medium mb-2 text-blue-200">Catégorie</label>
                <select
                  id="category"
                  v-model="formData.category"
                  required
                  class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
                >
                  <option value="">Sélectionner une catégorie</option>
                  <option v-for="cat in categoryOptions" :key="cat" :value="cat">{{ cat }}</option>
                </select>
              </div>

              <div>
                <label for="status" class="block text-sm font-medium mb-2 text-blue-200">Statut</label>
                <select
                  id="status"
                  v-model="formData.status"
                  required
                  class="w-full bg-blue-800 border border-blue-600 rounded-lg px-4 py-2 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
                >
                  <option value="active">Actif</option>
                  <option value="inactive">Inactif</option>
                  <option value="suspended">Suspendu</option>
                </select>
              </div>
            </div>

            <div class="flex space-x-4 mt-8">
              <button
                type="button"
                @click="closeModal"
                class="flex-1 bg-gray-600 hover:bg-gray-700 py-3 px-4 rounded-lg text-lg transition duration-200 shadow-md"
              >
                Annuler
              </button>
              <button
                type="submit"
                class="flex-1 bg-green-600 hover:bg-green-700 py-3 px-4 rounded-lg text-lg transition duration-200 shadow-md"
              >
                {{ showCreateModal ? 'Créer' : 'Modifier' }}
              </button>
            </div>
          </form>
        </div>
      </div>

      <div v-if="showViewModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50 p-4 animate-fade-in">
        <div class="bg-blue-900 rounded-xl p-8 w-full max-w-md mx-auto shadow-2xl transform scale-95 animate-zoom-in">
          <div class="flex justify-between items-center mb-6">
            <h3 class="text-2xl font-bold text-blue-200">Détails de l'utilisateur</h3>
            <button @click="showViewModal = false" class="text-gray-400 hover:text-white text-3xl transition duration-200">×</button>
          </div>

          <div v-if="selectedUser" class="space-y-5 text-center">
            <div class="w-24 h-24 rounded-full bg-gradient-to-r from-blue-400 to-purple-500 flex items-center justify-center text-white font-bold text-4xl mx-auto mb-4 shadow-lg">
              {{ selectedUser.username.charAt(0).toUpperCase() }}
            </div>
            <h4 class="text-2xl font-bold text-blue-100">{{ selectedUser.username }}</h4>
            <p class="text-blue-300 text-lg">{{ selectedUser.email }}</p>

            <div class="grid grid-cols-2 gap-4 text-left mt-6">
              <div>
                <span class="text-blue-300 font-medium">Catégorie:</span>
                <div class="mt-2">
                  <span :class="getCategoryBadgeClass(selectedUser.category)" class="text-sm px-3 py-1.5 rounded-full font-semibold shadow-sm">
                    {{ getCategoryLabel(selectedUser.category) }}
                  </span>
                </div>
              </div>
              <div>
                <span class="text-blue-300 font-medium">Statut:</span>
                <div class="mt-2">
                  <span :class="getStatusBadgeClass(selectedUser.status || 'active')" class="text-sm px-3 py-1.5 rounded-full font-semibold shadow-sm">
                    {{ getStatusLabel(selectedUser.status || 'active') }}
                  </span>
                </div>
              </div>
              </div>
          </div>

          <button
            @click="showViewModal = false"
            class="w-full mt-8 bg-blue-700 hover:bg-blue-600 py-3 px-4 rounded-lg text-lg transition duration-200 shadow-md"
          >
            Fermer
          </button>
        </div>
      </div>

      <div v-if="showConfirmDeleteModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50 p-4 animate-fade-in">
        <div class="bg-blue-900 rounded-xl p-8 w-full max-w-sm mx-auto shadow-2xl transform scale-95 animate-zoom-in">
          <h3 class="text-xl font-bold text-blue-200 mb-4">Confirmer la suppression</h3>
          <p class="text-gray-200 mb-6">Êtes-vous sûr de vouloir supprimer l'utilisateur **{{ userToDelete ? userToDelete.username : '' }}** ? Cette action est irréversible.</p>
          <div class="flex justify-end space-x-4">
            <button @click="cancelDelete" class="bg-gray-600 hover:bg-gray-700 px-5 py-2 rounded-lg text-lg transition duration-200">Annuler</button>
            <button @click="deleteUser" class="bg-red-600 hover:bg-red-700 px-5 py-2 rounded-lg text-lg transition duration-200">Supprimer</button>
          </div>
        </div>
      </div>


      <button @click="sidebarOpen = !sidebarOpen"
              class="fixed top-4 left-4 z-50 bg-blue-700 hover:bg-blue-600 text-white px-4 py-2 rounded-lg shadow-lg transition duration-300">
              ☰
      </button>

      <transition name="slide">
        <div
          v-if="sidebarOpen"
          class="fixed top-0 left-0 h-full bg-blue-950 w-64 px-6 py-8 z-40 shadow-2xl transition-all duration-300 ease-in-out"
        >
          <div class="flex justify-between items-center mb-10">
            <div class="text-3xl font-bold text-blue-100">Admin Panel</div>
            <button @click="sidebarOpen = false" class="text-gray-400 hover:text-white text-3xl transition duration-200">×</button>
          </div>
          <div class="space-y-4">
            <div
              v-for="item in menuItems"
              :key="item.name"
              :class="[
                'flex items-center space-x-3 px-4 py-2.5 rounded-md cursor-pointer text-lg',
                activeMenu === item.name ? 'bg-blue-700 text-white shadow-md' : 'hover:bg-blue-800 text-blue-200 hover:text-white'
              ]"
              @click="navigateTo(item.name)"
            >
              <span>{{ item.icon }}</span>
              <span>{{ item.name }}</span>
            </div>
          </div>
        </div>
      </transition>
    </div>

    <Products />
    <Carts />
    <Comments />
  </main>
</template>

<script>
import Products from '@/components/Products.vue';
import Carts from '@/components/Carts.vue';
import Comments from '@/components/Comments.vue';

import axios from 'axios';

export default {
  name: "admin-users",
  components: {
    Products,
    Carts,
    Comments,
  },
  data() {
    return {
      // Sidebar State
      sidebarOpen: false,
      activeMenu: 'Utilisateurs',
      menuItems: [
        { name: 'Dashboard', icon: '📊' },
        { name: 'Utilisateurs', icon: '👥' },
        { name: 'Compétitions', icon: '🏆' },
        { name: 'Événements', icon: '📅' },
        { name: 'Paramètres', icon: '⚙️' },
      ],

      // CRUD State
      users: [],

      // Category Options (sports + roles)
      categoryOptions: [
        'BasketBall', 'FootBall', 'VolleyBall', 'Running', 'Tennis',
        'admin', 'user', 'moderator', 'editor', 'viewer'
      ],

      // Filters and Search
      searchTerm: '',
      filterCategory: '',
      filterStatus: '',

      // Sorting
      sortField: 'username',
      sortDirection: 'asc',

      // Pagination
      currentPage: 1,
      itemsPerPage: 10,

      // Modals State
      showCreateModal: false,
      showEditModal: false,
      showViewModal: false,
      showConfirmDeleteModal: false, // New state for confirmation modal

      selectedUser: null, // Holds the user object for viewing/editing
      userToDelete: null, // Holds the user object for deletion confirmation
      selectedUserId: null, // Stores the ID (string) of the user being edited/deleted

      // Form Data for Create/Edit
      formData: {
        username: '',
        email: '',
        password: '',
        category: '',
        status: 'active' // Default status for new users
      }
    };
  },

  created() {
    this.getUsers();
  },

  computed: {
    filteredUsers() {
      let filtered = [...this.users];

      // Search filter
      if (this.searchTerm) {
        const term = this.searchTerm.toLowerCase();
        filtered = filtered.filter(user =>
          user.username.toLowerCase().includes(term) ||
          user.email.toLowerCase().includes(term)
        );
      }

      // Category filter
      if (this.filterCategory) {
        filtered = filtered.filter(user => user.category === this.filterCategory);
      }

      // Status filter (handle cases where status might be missing on existing users)
      if (this.filterStatus) {
        filtered = filtered.filter(user => (user.status || 'active') === this.filterStatus);
      }

      // Sorting
      filtered.sort((a, b) => {
        let aValue = a[this.sortField];
        let bValue = b[this.sortField];

        // For string comparisons (username, email, category), ensure case-insensitivity
        if (typeof aValue === 'string' && typeof bValue === 'string') {
          aValue = aValue.toLowerCase();
          bValue = bValue.toLowerCase();
        }

        if (aValue < bValue) return this.sortDirection === 'asc' ? -1 : 1;
        if (aValue > bValue) return this.sortDirection === 'asc' ? 1 : -1;
        return 0;
      });

      return filtered;
    },

    paginatedUsers() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.filteredUsers.slice(start, end);
    },

    totalPages() {
      return Math.ceil(this.filteredUsers.length / this.itemsPerPage);
    }
  },

  methods: {
    async getUsers() {
      try {
        const response = await axios.get('http://localhost:8080/api/users');
      
        this.users = response.data.map(user => ({
          ...user,
          id: user.id,
          status: user.status || 'active'
        }));
        console.log("Users fetched:", this.users);
      } catch (error) {
        console.error('Error fetching users:', error);
        alert(`Erreur lors du chargement des utilisateurs: ${error.response?.data || error.message}`);
      }
    },

    // --- Navigation ---
    navigateTo(name) {
      this.activeMenu = name;
      console.log(`Navigating to: ${name}`);
      // In a real application, you would use Vue Router here
      // this.$router.push({ name: name.toLowerCase() });
      this.sidebarOpen = false; // Close sidebar after navigation
    },

    // --- Utility Methods ---
    getUsersByCategory(category) {
      return this.users.filter(user => user.category === category);
    },

    sortBy(field) {
      if (this.sortField === field) {
        this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortField = field;
        this.sortDirection = 'asc';
      }
      this.currentPage = 1; // Reset pagination on sort
    },

    // --- CRUD Operations ---
    openCreateModal() {
      this.resetFormData(); // Clear form for new user
      this.showCreateModal = true;
    },

    async createUser() {
      try {
        const response = await axios.post('http://localhost:8080/api/users', this.formData);
        console.log('User created successfully:', response.data);
        alert('Utilisateur créé avec succès!');
        await this.getUsers(); // Refresh list to get new user and clear cache
        this.closeModal();
      } catch (error) {
        console.error('Error creating user:', error);
        alert(`Erreur lors de la création: ${error.response?.data?.message || error.message}`);
      }
    },

    editUser(user) {
      this.selectedUser = user;
      // Populate formData with existing user data
      this.formData = {
        username: user.username,
        email: user.email,
        password: '', // Password field is intentionally left blank for editing
        category: user.category,
        status: user.status || 'active' // Ensure status is set
      };
      this.selectedUserId = user.idAsString; // Store the ID for the PUT request
      this.showEditModal = true;
    },

    async updateUser() {
      try {
        const userId = this.selectedUserId;
        if (!userId) {
          throw new Error("User ID is missing for update operation.");
        }

        console.log('User to update ', userId);

        // Envoyer tous les champs requis
        const updateData = {
          username: this.formData.username,
          email: this.formData.email,
          category: this.formData.category,
          status: this.formData.status
        };

        // Ajouter le mot de passe seulement s'il est fourni
        if (this.formData.password) {
          updateData.password = this.formData.password;
        }

        const response = await axios.put(
          `http://localhost:8080/api/users/${userId}`,
          updateData
        );

        console.log('User updated successfully:', response.data);
        alert('Utilisateur mis à jour avec succès!');
        await this.getUsers();
        this.closeModal();
      } catch (error) {
        console.error('Error updating user:', error);
        alert(`Erreur lors de la mise à jour: ${error.response?.data?.message || error.message}`);
      }
    },

    viewUser(user) {
      this.selectedUser = user;
      this.showViewModal = true;
    },

    confirmDeleteUser(user) {
      this.userToDelete = user;
      this.showConfirmDeleteModal = true;
    },

    async deleteUser() {
      try {
        if (!this.userToDelete || !this.userToDelete.idAsString) {
          throw new Error("No user selected for deletion.");
        }
        await axios.delete(`http://localhost:8080/api/users/${this.userToDelete.idAsString}`);

        console.log(`User ${this.userToDelete.username} deleted.`);
        alert('Utilisateur supprimé avec succès!');
        await this.getUsers();
        this.closeModal();
      } catch (error) {
        console.error('Error deleting user:', error);
        alert(`Erreur lors de la suppression: ${error.response?.data?.message || error.message}`);
      }
    },

    cancelDelete() {
      this.userToDelete = null;
      this.showConfirmDeleteModal = false;
    },

    // --- Modal Control ---
    closeModal() {
      this.showCreateModal = false;
      this.showEditModal = false;
      this.showViewModal = false;
      this.showConfirmDeleteModal = false; // Also close confirm modal
      this.selectedUser = null;
      this.userToDelete = null; // Clear user to delete
      this.selectedUserId = null; // Clear selected user ID
      this.resetFormData();
    },

    resetFormData() {
      this.formData = {
        username: '',
        email: '',
        password: '',
        category: '',
        status: 'active'
      };
    },

    // --- Styling and Formatting Helpers ---
    getCategoryBadgeClass(category) {
      const classes = {
        BasketBall: 'bg-orange-500',
        FootBall: 'bg-green-500',
        VolleyBall: 'bg-blue-500',
        Running: 'bg-purple-500',
        Tennis: 'bg-yellow-500',
        admin: 'bg-red-500',
        moderator: 'bg-orange-500',
        editor: 'bg-purple-500',
        user: 'bg-green-500',
        viewer: 'bg-gray-500'
      };
      return classes[category] || 'bg-gray-500';
    },

    getCategoryLabel(category) {
      const labels = {
        BasketBall: 'BasketBall',
        FootBall: 'FootBall',
        VolleyBall: 'VolleyBall',
        Running: 'Running',
        Tennis: 'Tennis',
        admin: 'Administrateur',
        user: 'Utilisateur',
        moderator: 'Modérateur',
        editor: 'Éditeur',
        viewer: 'Visiteur'
      };
      return labels[category] || category;
    },

    getStatusBadgeClass(status) {
      const classes = {
        active: 'bg-green-500',
        inactive: 'bg-gray-500',
        suspended: 'bg-red-500'
      };
      return classes[status] || 'bg-gray-500';
    },

    getStatusLabel(status) {
      const labels = {
        active: 'Actif',
        inactive: 'Inactif',
        suspended: 'Suspendu'
      };
      return labels[status] || status;
    },
  }
};
</script>

<style scoped>
/* Sidebar slide animation */
.slide-enter-active,
.slide-leave-active {
  transition: transform 0.3s ease;
}
.slide-enter-from {
  transform: translateX(-100%);
}
.slide-leave-to {
  transform: translateX(-100%);
}

/* Modal fade-in animation */
@keyframes fade-in {
  from { opacity: 0; }
  to { opacity: 1; }
}
.animate-fade-in {
  animation: fade-in 0.3s ease-out forwards;
}

/* Modal zoom-in animation */
@keyframes zoom-in {
  from { transform: scale(0.95); opacity: 0; }
  to { transform: scale(1); opacity: 1; }
}
.animate-zoom-in {
  animation: zoom-in 0.3s ease-out forwards;
}
</style>