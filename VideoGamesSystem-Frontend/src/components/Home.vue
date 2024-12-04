<template>
  <div class="home">
    <h1>Welcome, {{ store.user.userName }}!</h1>
    
    <div class="sort-controls">
      <label for="sort">Sort by:</label>
      <select v-model="sortOption" @change="sortGames">
        <option value="price">Price</option>
        <option value="title">Title</option>
        <option value="category">Category</option>
        <option value="consoleType">Console Type</option>
      </select>
    </div>

    <div class="games-list">
      <div v-for="game in sortedGames" :key="game.id" class="game-card">
        <div class="game-info">
          <h3>{{ game.title }}</h3>
          <p>{{ game.description }}</p>
          <p>Price: ${{ game.price }}</p>
          <p>Category: {{ game.category }}</p>
          <p>Console: {{ game.consoleType }}</p>
          <p>Status: {{ game.availableQuantity > 0 ? "Available" : "Out of Stock" }}</p>
        </div>
        <div class="game-actions">
          <button 
            @click="addToCart(game)" 
            class="cart-btn"
            :disabled="game.availableQuantity === 0"
          >
            {{ game.availableQuantity === 0 ? 'Out of Stock' : 'Add to Cart' }}
          </button>
          <button 
            @click="addToWishlist(game)"
            class="wishlist-btn"
          >
            Add to Wishlist
          </button>

          <!-- View Details Button that leads to GameDetails -->
          <button 
            @click="viewGameDetails(game.id)"
            class="view-details-btn"
          >
            View Details
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { store } from "../store.js";
import axios from 'axios';

const axiosGame = axios.create({
  baseURL: "http://localhost:8081",
});

export default {
  name: "Home",
  setup() {
    return { store };
  },
  data() {
    return {
      sortOption: "price",
    };
  },
  async created() {
    try {
      const response = await axiosGame.get('/games');
      store.games = response.data;
    } catch (error) {
      console.error('Error fetching games:', error);
      alert('Failed to load games');
    }
  },
  computed: {
    sortedGames() {
      if (!store.games) return [];
      return [...store.games].sort((a, b) => {
        if (this.sortOption === "price") {
          return a.price - b.price;
        } else if (this.sortOption === "title") {
          return a.title.localeCompare(b.title);
        } else if (this.sortOption === "category") {
          return a.category.localeCompare(b.category);
        } else if (this.sortOption === "consoleType") {
          return a.consoleType.localeCompare(b.consoleType);
        }
        return 0;
      });
    },
  },
  methods: {
    sortGames() {
      if (this.sortOption === "price") {
        this.games.sort((a, b) => a.price - b.price);
      } else if (this.sortOption === "category") {
        this.games.sort((a, b) => a.category.localeCompare(b.category));
      } else if (this.sortOption === "consoleType") {
        this.games.sort((a, b) => a.consoleType.localeCompare(b.consoleType));
      }
    },
    async addToCart(game) {
      try {
        if (game.availableQuantity === 0) {
          alert("No available copies of this game.");
          return;
        }

        // First, get available specific games for this game
        const response = await axiosGame.get('/specificGames');
        const availableSpecificGame = response.data.find(
          sg => sg.gameId === game.id && sg.availability
        );

        if (!availableSpecificGame) {
          alert("No available copies of this game.");
          return;
        }

        // Check if game is already in cart
        const exists = store.cartSpecificGames.find(
          sg => sg.serialNumber === availableSpecificGame.serialNumber
        );

        if (exists) {
          alert("This game is already in your cart.");
          return;
        }

        // Mark the specific game as unavailable
        await axiosGame.put(
          `/specificGames/${availableSpecificGame.serialNumber}/availability`,
          { availability: false }
        );

        // Add game details to cart
        const cartGame = {
          serialNumber: availableSpecificGame.serialNumber,
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
      } catch (error) {
        console.error("Error adding game to cart:", error);
        alert("Failed to add game to cart. Please try again.");
      }
    },
    async addToWishlist(game) {
      try {
        if (!store.user) {
          this.$router.push("/login");
          return;
        }

        // Check if wishlist exists for customer, if not create one
        let wishlist;
        try {
          wishlist = await axiosGame.get(`/wishlists/customer/${store.user.id}`);
        } catch {
          // Create new wishlist for customer
          wishlist = await axiosGame.post('/wishlists', {
            customerId: store.user.id
          });
        }

        // Add game to local storage wishlist
        const alreadyInWishlist = store.wishlistGames.some(
          wishlistGame => wishlistGame.id === game.id
        );

        if (alreadyInWishlist) {
          alert("This game is already in your wishlist.");
          return;
        }

        store.wishlistGames.push(game);
        localStorage.setItem(
          "wishlistGames",
          JSON.stringify(store.wishlistGames)
        );

        alert(`Game "${game.title}" added to wishlist!`);
      } catch (error) {
        console.error("Error adding game to wishlist:", error);
        alert("Failed to add game to wishlist.");
      }
    },

    // Method for navigating to the game details page
    viewGameDetails(gameId) {
      this.$router.push(`/game-details/${gameId}`);
    },
  },
};
</script>

<style scoped>
.home {
  padding: 20px;
}

.sort-controls {
  margin-bottom: 20px;
}

.sort-controls select {
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ddd;
}

.games-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.game-card {
  border: 1px solid #ddd;
  padding: 15px;
  border-radius: 8px;
  background-color: grey;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.game-info {
  margin-bottom: 15px;
}

.game-info h3 {
  margin-top: 0;
  color: #333;
  margin-bottom: 10px;
}

.game-info p {
  margin: 8px 0;
  color: white;
}

.game-actions {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
}

.cart-btn, .wishlist-btn, .view-details-btn {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.2s;
}

.cart-btn {
  background-color: #4CAF50;
  color: white;
}

.cart-btn:hover:not(:disabled) {
  background-color: #45a049;
}

.cart-btn:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.wishlist-btn {
  background-color: #2196F3;
  color: white;
}

.wishlist-btn:hover {
  background-color: #1976D2;
}

.view-details-btn {
  background-color: #ff9800;
  color: white;
}

.view-details-btn:hover {
  background-color: #f57c00;
}
</style>
