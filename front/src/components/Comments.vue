<template>
    <div class="p-6">
      <h2 class="text-2xl font-bold mb-4">Gestion des Commentaires</h2>
  
      <!-- Liste des commentaires -->
      <table class="min-w-full bg-white shadow rounded">
        <thead>
          <tr class="bg-gray-100 text-left">
            <th class="p-2 text-black">ID</th>
            <th class="p-2 text-black">Utilisateur</th>
            <th class="p-2 text-black">Produit</th>
            <th class="p-2 text-black">Contenu</th>
            <th class="p-2 text-black">Date</th>
            <th class="p-2 text-black">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="comment in comments" :key="comment.id" class="border-t">
            <td class="p-2 text-black">{{ comment.idAsString }}</td>
            <td class="p-2 text-black">{{ comment.user?.username || 'N/A' }}</td>
            <td class="p-2 text-black">{{ comment.product?.name || 'N/A' }}</td>
            <td class="p-2 text-black">
              <span v-if="editId !== comment.id">{{ comment.content }}</span>
              <input v-else v-model="editContent" class="border p-1 w-full" />
            </td>
            <td class="p-2 text-black">{{ new Date(comment.publishDate).toLocaleString() }}</td>
            <td class="p-2 space-x-2">
              <button v-if="editId !== comment.id" @click="startEdit(comment)" class="bg-blue-500 text-white px-2 py-1 rounded">Modifier</button>
              <button v-else @click="saveEdit(comment.id)" class="bg-green-500 text-white px-2 py-1 rounded">Enregistrer</button>
              <button @click="deleteComment(comment.id)" class="bg-red-500 text-white px-2 py-1 rounded">Supprimer</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
</template>
  
<script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        comments: [],
        editId: null,
        editContent: '',
      };
    },
    methods: {
      fetchComments() {
        axios.get('http://localhost:8080/api/comments')
          .then(res => {
            this.comments = res.data.map(comment => {
              let parsedContent = comment.content;
              try {
                const parsed = JSON.parse(comment.content);
                parsedContent = parsed.content || comment.content;
              } catch (e) {
                console.warn('Contenu non parsable:', comment.content);
              }

              let dateStr = comment.publishDate;
              if (dateStr.includes('[UTC]')) {
                dateStr = dateStr.replace('[UTC]', '');
              }

              return {
                ...comment,
                content: parsedContent,
                publishDate: dateStr,
              };
            });

            console.log('Commentaires traités:', this.comments);
          })
          .catch(err => {
            console.error('Erreur lors de la récupération des commentaires:', err);
          });
      },
      startEdit(comment) {
        this.editId = comment.id;
        this.editContent = comment.content;
      },
      saveEdit(commentId) {
        axios.put(`http://localhost:8080/api/comments/edit/${commentId}`, this.editContent, {
          headers: { 'Content-Type': 'application/json' }
        })
          .then(() => {
            this.editId = null;
            this.editContent = '';
            this.fetchComments();
          })
          .catch(err => {
            console.error('Erreur lors de la mise à jour du commentaire:', err);
          });
      },
      deleteComment(commentId) {
        if (!confirm('Voulez-vous vraiment supprimer ce commentaire ?')) return;
  
        axios.delete(`http://localhost:8080/api/comments/delete/${commentId}`)
          .then(() => {
            this.fetchComments();
          })
          .catch(err => {
            console.error('Erreur lors de la suppression du commentaire:', err);
          });
      },
    },
    mounted() {
      this.fetchComments();
    },
  };
</script>
  
<style scoped>
  table {
    border-collapse: collapse;
    width: 100%;
  }
</style>  