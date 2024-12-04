<!-- src/components/Home.vue -->
<template>
  <div class="home">
    <h1>Welcome, {{ store.user.userName }}!</h1>

    <!-- Search Bar -->
    <div class="search-bar">
      <label for="search">Search Games:</label>
      <input
        type="text"
        id="search"
        v-model="searchQuery"
        @input="handleSearchInput"
        placeholder="Search by title, description, etc..."
        aria-label="Search Games"
      />
    </div>

    <!-- Sort Controls -->
    <div class="sort-controls">
      <label for="sort">Sort by:</label>
      <select
        v-model="sortOption"
        @change="sortGames"
        id="sort"
        aria-label="Sort Games"
      >
        <option value="price">Price</option>
        <option value="title">Title</option>
        <option value="category">Category</option>
        <option value="consoleType">Console Type</option>
      </select>
    </div>

    <!-- Games List -->
    <div class="games-list">
      <div
        v-for="game in filteredAndSortedGames"
        :key="game.id"
        class="game-card"
      >
        <div class="game-info">
          <h3>{{ game.title }}</h3>
          <p>{{ game.description }}</p>
          <p>Price: ${{ game.price.toFixed(2) }}</p>
          <p>Category: {{ game.category }}</p>
          <p>Console: {{ game.consoleType }}</p>
          <p>Status: {{ game.stockQuantity > 0 ? "Available" : "Out of Stock" }}</p>
        </div>
        <div class="game-actions">
          <button
            @click="addToCart(game)"
            class="cart-btn"
            :disabled="game.stockQuantity === 0"
          >
            {{ game.stockQuantity === 0 ? 'Out of Stock' : 'Add to Cart' }}
          </button>
          <button
            @click="addToWishlist(game)"
            class="wishlist-btn"
            :disabled="isAddingToWishlist"
            aria-label="Add to Wishlist"
          >
            Add to Wishlist
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { store } from "../store.js";
import axios from "axios";
import debounce from "lodash/debounce"; // Ensure Lodash is installed

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

      // Fetch the stock quantity for each game
      for (const game of store.games) {
        const stockResponse = await axiosGame.get(`/games/${game.id}/stock`);
        game.stockQuantity = stockResponse.data;
      }
    } catch (error) {
      console.error('Error fetching games:', error);
      alert('Failed to load games');
    }
  },
  computed: {
    filteredAndSortedGames() {
      if (!store.games) return [];

      const query = this.searchQuery.toLowerCase().trim();
      let filteredGames = store.games;

      if (query) {
        filteredGames = store.games.filter((game) => {
          return (
            game.title.toLowerCase().includes(query) ||
            game.description.toLowerCase().includes(query) ||
            game.category.toLowerCase().includes(query) ||
            game.consoleType.toLowerCase().includes(query)
          );
        });
      }

      return filteredGames.sort((a, b) => {
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
    async fetchGames() {
      try {
        const response = await axiosGame.get("/games");
        store.games = response.data;
      } catch (error) {
        console.error("Error fetching games:", error);
        alert("Failed to load games");
      }
    },
    sortGames() {
      // Sorting is handled in the computed property 'filteredAndSortedGames'
      // This method can be retained for additional sorting logic if needed
    },
    handleSearchInput() {
      if (this.debouncedSearch) {
        this.debouncedSearch();
      }
    },
async addToCart(game) {
  try {
    if (game.stockQuantity === 0) {
      alert("No available copies of this game.");
      return;
    }
    // Get available specific games for this game
    const response = await axiosGame.get(`/games/${game.id}/specific-games`);
    const availableSpecificGames = response.data;

    if (availableSpecificGames.length === 0) {
      alert("No available copies of this game.");
      return;
    }

    const specificGameToAdd = availableSpecificGames[0];

    // Check if game is already in cart
    const exists = store.cartSpecificGames.some(
      sg => sg.serialNumber === specificGameToAdd.serialNumber
    );

    if (exists) {
      alert("This game is already in your cart.");
      return;
    }
  //   // Mark the specific game as unavailable
  //   await axiosGame.put(
  // `/specificGames/${specificGameToAdd.serialNumber}/availability`,
  //     { newAvailability: false }
  //   );

    // Add game details to cart
    const cartGame = {
      serialNumber: specificGameToAdd.serialNumber,
      title: game.title,
      description: game.description,
      price: game.price,
    };

    store.cartSpecificGames.push(cartGame);
    localStorage.setItem(
      "cartSpecificGames",
      JSON.stringify(store.cartSpecificGames)
    );

    // Update the game's stock quantity
    game.stockQuantity--;

        alert("Game added to cart!");
      } catch (error) {
        console.error("Error adding game to cart:", error);
        alert("Failed to add game to cart. Please try again.");
      } finally {
        this.isAddingToCart = false;
      }
    }, 300), // 300ms debounce

    addToWishlist: debounce(async function (game) {
      this.isAddingToWishlist = true;
      try {
        if (!store.user) {
          this.$router.push("/login");
          return;
        }

        // Check if wishlist exists for customer, if not create one
        let wishlist;
        try {
          const response = await axiosGame.get(
            `/wishlists/customer/${store.user.id}`
          );
          wishlist = response.data;
        } catch (err) {
          // If wishlist doesn't exist, create one
          const createResponse = await axiosGame.post("/wishlists", {
            customerId: store.user.id,
          });
          wishlist = createResponse.data;
        }

        // Add game to wishlist via backend API
        await axiosGame.put(`/games/${game.id}/wishlist/${wishlist.id}`);

        // Update local store and localStorage
        const alreadyInWishlist = store.wishlistGames.some(
          (wishlistGame) => wishlistGame.id === game.id
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
      } finally {
        this.isAddingToWishlist = false;
      }
    }, 300), // 300ms debounce
  },
  created() {
    this.fetchGames();

    // Initialize debounced search function (optional)
    this.debouncedSearch = debounce(() => {
      // If you want to perform any action after debounced search
      // Currently, the computed property handles filtering
    }, 300); // 300ms delay
  },
};
</script>

<style scoped>
.home {
  padding: 20px;
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 2rem;
}

.search-bar {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.search-bar label {
  margin-right: 10px;
  font-weight: bold;
}

.search-bar input {
  flex: 1;
  padding: 8px 12px;
  border-radius: 4px;
  border: 1px solid #ddd;
  font-size: 1rem;
}

.sort-controls {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.sort-controls label {
  margin-right: 10px;
  font-weight: bold;
}

.sort-controls select {
  padding: 8px 12px;
  border-radius: 4px;
  border: 1px solid #ddd;
  font-size: 1rem;
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
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: transform 0.2s, box-shadow 0.2s;
}

.game-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.game-info h3 {
  margin-top: 0;
  color: #333;
  margin-bottom: 10px;
}

.game-info p {
  margin: 8px 0;
  color: #555;
}

.available {
  color: #4caf50; /* Green */
  font-weight: bold;
}

.out-of-stock {
  color: #f44336; /* Red */
  font-weight: bold;
}

.game-actions {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
}

.cart-btn,
.wishlist-btn {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.2s, opacity 0.2s;
}

.cart-btn {
  background-color: #4caf50;
  color: white;
}

.cart-btn:hover:not(:disabled) {
  background-color: #45a049;
}

.cart-btn:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
  opacity: 0.7;
}

.wishlist-btn {
  background-color: #2196f3;
  color: white;
}

.wishlist-btn:hover {
  background-color: #1976d2;
}
</style>
