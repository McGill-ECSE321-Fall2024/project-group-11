<template>
  <div class="game-detail" v-if="game">
    <h2>{{ game.title }}</h2>
    <p><strong>Description:</strong> {{ game.description }}</p>
    <p><strong>Price:</strong> ${{ game.price }}</p>
    <p><strong>Category:</strong> {{ game.category }}</p>
    <p><strong>Console:</strong> {{ game.consoleType }}</p>
    <p><strong>Available Quantity:</strong> {{ game.availableQuantity }}</p>
    <button
      @click="addToCart(game)"
      :disabled="game.availableQuantity === 0"
    >
      Add to Cart
    </button>
    <button @click="addToWishlist(game)">
      Add to Wishlist
    </button>
  </div>

  <!-- If no game is found, display an error or loading message -->
  <div v-else>
    <p>Game not found. Please make sure the game exists in the list.</p>
  </div>
</template>

<script>
export default {
  name: "GameDetails",
  data() {
    return {
      game: null, // This will hold the game details
    };
  },
  created() {
    const gameId = this.$route.params.gameId; // Get the gameId from route params
    const games = JSON.parse(localStorage.getItem("games")); // Fetch all games from localStorage

    // Find the game by gameId
    if (games) {
      this.game = games.find((game) => game.id === parseInt(gameId)); // Match game by ID
    }

    if (!this.game) {
      console.error("Game not found!"); // If game is not found, log the error
    }
  },
  methods: {
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
}
button {
  margin-top: 10px;
}
</style>
