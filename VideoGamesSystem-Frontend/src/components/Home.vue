<template>
  <div>
    <h1>Welcome, {{ store.user.userName }}!</h1>
    <div v-if="store.userType === 'customer'">
      <!-- Customer-specific content -->
      <p>You are logged in as a customer.</p>
    </div>
    <div v-else-if="store.userType === 'staff'">
      <!-- Staff-specific content -->
      <p>You are logged in as a staff member.</p>
      <!-- Add staff functionalities -->
    </div>

    <div class="home-page">
      <h2>All Games</h2>
      <div class="filters">
        <label for="sort">Sort by:</label>
        <select v-model="sortOption" @change="sortGames">
          <option value="price">Price</option>
          <option value="category">Category</option>
          <option value="consoleType">Console Type</option>
        </select>
      </div>
      <div class="games-list">
        <!-- Loop through games and use router-link for navigation to GameDetails -->
        <div
          v-for="game in games"
          :key="game.id"
          class="game-item"
        >
          <h3>{{ game.title }}</h3>
          <p>{{ game.description }}</p>
          <p>Price: ${{ game.price }}</p>
          <p>Category: {{ game.category }}</p>
          <p>Console: {{ game.consoleType }}</p>
          <p>Available: {{ game.availableQuantity > 0 ? "Yes" : "No" }}</p>
          <button
            @click="addToCart(game)"
            :disabled="game.availableQuantity === 0"
          >
            Add to Cart
          </button>
          <button @click="addToWishlist(game)">Add to Wishlist</button>

          <!-- Added the "See Game Details" button -->
          <router-link
            :to="{ name: 'GameDetails', params: { gameId: game.id } }"
            class="see-details-button"
          >
            See Game Details
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { store } from "../store.js";
import axios from 'axios'; // Axios to make API calls

export default {
  name: "Home",
  setup() {
    return { store };
  },
  data() {
    return {
      sortOption: "price",
      games: [],
    };
  },
  created() {
    if (!store.user || !store.userType) {
      this.$router.push("/login");
    } else {
      this.fetchGames(); // Fetch games on page load
    }
  },
  methods: {
    fetchGames() {
      axios.get('http://localhost:8080/games') // Adjust the URL as needed based on your backend
        .then(response => {
          this.games = response.data;
        })
        .catch(error => {
          console.error('Error fetching games:', error);
        });
    },
    sortGames() {
      if (this.sortOption === "price") {
        this.games.sort((a, b) => a.price - b.price);
      } else if (this.sortOption === "category") {
        this.games.sort((a, b) => a.category.localeCompare(b.category));
      } else if (this.sortOption === "consoleType") {
        this.games.sort((a, b) => a.consoleType.localeCompare(b.consoleType));
      }
    },
    addToCart(game) {
      if (game.availableQuantity === 0) {
        alert("No available copies of this game.");
        return;
      }

      // Find an available SpecificGame instance
      const availableSpecificGame = game.specificGames.find(
        (sg) => sg.availability
      );

      if (!availableSpecificGame) {
        alert("No available copies of this game.");
        return;
      }

      // Add the SpecificGame to the cart
      const exists = store.cartSpecificGames.find(
        (sg) => sg.serialNumber === availableSpecificGame.serialNumber
      );

      if (!exists) {
        // Mark the SpecificGame as unavailable
        availableSpecificGame.availability = false;
        game.availableQuantity -= 1;

        // Add game details to the SpecificGame object for display purposes
        const cartGame = {
          ...availableSpecificGame,
          title: game.title,
          description: game.description,
          price: game.price,
        };

        store.cartSpecificGames.push(cartGame);
        localStorage.setItem(
          "cartSpecificGames",
          JSON.stringify(store.cartSpecificGames)
        );

        alert("Game added to cart!");
      } else {
        alert("This game is already in your cart.");
      }
    },
    addToWishlist(game) {
      // Simulating as I am not currently connected to backend
      alert(`Game "${game.title}" added to wishlist!`);
    },
  },
};
</script>

<style scoped>
.home-page {
  padding: 20px;
}
.games-list {
  display: flex;
  flex-wrap: wrap;
}
.game-item {
  border: 1px solid #ccc;
  padding: 10px;
  margin: 10px;
  width: 200px;
  cursor: pointer;
}
.filters {
  margin-bottom: 20px;
}
.see-details-button {
  margin-top: 10px;
  display: block;
  text-align: center;
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  border: none;
  text-decoration: none;
  border-radius: 5px;
  cursor: pointer;
}

.see-details-button:hover {
  background-color: #45a049;
}
</style>
