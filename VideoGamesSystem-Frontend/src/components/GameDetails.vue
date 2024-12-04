<template>
  <div class="game-detail" v-if="game">
    <h1 class="game-title">{{ game.title }}</h1>
    <p><strong>Description:</strong> {{ game.description }}</p>
    <p><strong>Price:</strong> ${{ game.price }}</p>
    <p><strong>Available Quantity:</strong> {{ game.availableQuantity }}</p>

    <!-- Action Buttons -->
    <button
      @click="addToCart(game)"
      :disabled="game.availableQuantity === 0"
      class="btn btn-primary"
    >
      Add to Cart
    </button>
    <button @click="addToWishlist(game)" class="btn btn-secondary">
      Add to Wishlist
    </button>

    <!-- Back to Home Button -->
    <router-link to="/home" class="btn btn-back">
      Back to Home
    </router-link>
  </div>

  <div v-else>
    <p>Game not found. Please make sure the game exists in the list.</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "GameDetails",
  data() {
    return {
      game: null,
    };
  },
  created() {
    this.fetchGame();
  },
  methods: {
    async fetchGame() {
      const gameId = this.$route.params.gameId;
      try {
        const response = await axios.get(`http://localhost:8080/games/${gameId}`);
        this.game = response.data;
        console.log("Fetched game:", this.game);
      } catch (error) {
        console.error("Error fetching game data:", error);
      }
    },
    addToCart(game) {
      alert(`${game.title} added to the cart!`);
    },
    addToWishlist(game) {
      alert(`${game.title} added to the wishlist!`);
    },
  },
};
</script>

<style scoped>
.game-detail {
  padding: 20px;
  border: 1px solid #ccc;
  background-color: #f9f9f9;
  max-width: 800px;
  margin: 20px auto;
  border-radius: 8px;
}

.game-title {
  font-size: 2rem;
  color: #333;
  margin-bottom: 10px;
}

p {
  font-size: 1.1rem;
  color: #555;
  margin-bottom: 10px;
}

button {
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  font-size: 16px;
}

.btn-primary {
  background-color: #007bff;
  color: white;
  border: none;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
  border: none;
}

button:disabled {
  background-color: #ddd;
  cursor: not-allowed;
}

/* New Back to Home Button */
.btn-back {
  display: inline-block;
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #28a745;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  text-align: center;
  cursor: pointer;
}

.btn-back:hover {
  background-color: #218838;
}
</style>
