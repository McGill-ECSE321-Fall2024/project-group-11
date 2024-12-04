<template>
  <div class="my-games-page">
    <h1>My Games</h1>
    
    <!-- Empty state -->
    <div v-if="!hasGames" class="empty-state">
      You haven't purchased any games yet.
    </div>
    
    <!-- Games list -->
    <div v-else class="games-list">
      <div v-for="game in sortedGames" :key="game.serialNumber" class="game-item">
        <h3>{{ game.title }}</h3>
        <p>{{ game.description }}</p>
        <p class="price">Price: ${{ game.price }}</p>
        <div class="game-details">
          <p>Serial Number: {{ game.serialNumber }}</p>
          <p>Order #: {{ game.orderNumber }}</p>
          <p>Purchased: {{ formatDate(game.purchaseDate) }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { store } from "../store.js";
import { computed } from 'vue';

export default {
  name: "MyGames",
  
  setup() {
    // Initialize store.orderedGames if it doesn't exist
    if (!store.orderedGames) {
      store.orderedGames = [];
    }

    const hasGames = computed(() => {
      return store.orderedGames && store.orderedGames.length > 0;
    });

    const sortedGames = computed(() => {
      if (!store.orderedGames) return [];
      return [...store.orderedGames].sort((a, b) => 
        new Date(b.purchaseDate) - new Date(a.purchaseDate)
      );
    });

    return {
      store,
      hasGames,
      sortedGames
    };
  },
  
  created() {
    if (!store.user) {
      this.$router.push("/login");
      return;
    }
    
    // Load ordered games from localStorage if they exist
    const savedGames = localStorage.getItem("orderedGames");
    if (savedGames) {
      store.orderedGames = JSON.parse(savedGames);
    }
  },
  
  methods: {
    formatDate(dateString) {
      if (!dateString) return 'N/A';
      return new Date(dateString).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
      });
    }
  }
};
</script>

<style scoped>
.my-games-page {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.games-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.game-item {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  background: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.game-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.game-details {
  margin-top: 10px;
  padding-top: 10px;
  border-top: 1px solid #eee;
  font-size: 0.9em;
  color: #666;
}

.empty-state {
  text-align: center;
  padding: 40px;
  color: #666;
}

.price {
  font-weight: bold;
  color: #2c3e50;
  font-size: 1.1em;
  margin: 10px 0;
}
</style>