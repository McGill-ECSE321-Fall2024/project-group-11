<!-- src/components/Wishlist.vue -->
<template>
  <div class="wishlist-page">
    <h1>My Wishlist</h1>
    <div v-if="store.wishlistGames.length === 0">Your wishlist is empty.</div>
    <div v-else class="games-list">
      <div v-for="game in store.wishlistGames" :key="game.id" class="game-item">
        <h2>{{ game.title }}</h2>
        <p>{{ game.description }}</p>
        <p>Price: ${{ game.price }}</p>
        <button @click="removeFromWishlist(game)">Remove</button>
        <button @click="addToCart(game)">Add to Cart</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { store } from "../store.js";

const axiosClient = axios.create({
  baseURL: "http://localhost:8081",
});

export default {
  name: "Wishlist",
  setup() {
    return { store };
  },
  created() {
    if (store.user) {
      this.fetchWishlist();
    } else {
      this.$router.push("/login");
    }
  },
  methods: {
    async fetchWishlist() {
      try {
        if (!store.user) return;

        const response = await axiosClient.get(
          `/wishlists/customer/${store.user.id}`
        );
        const wishlistId = response.data.id;

        // Fetch games in the wishlist
        const gamesResponse = await axiosClient.get(
          `/wishlists/${wishlistId}/games`
        );
        store.wishlistGames = gamesResponse.data || [];
        localStorage.setItem(
          "wishlistGames",
          JSON.stringify(store.wishlistGames)
        );
      } catch (error) {
        console.error("Error fetching wishlist:", error);
        // Fall back to local storage
        store.wishlistGames =
          JSON.parse(localStorage.getItem("wishlistGames")) || [];
      }
    },

    async removeFromWishlist(game) {
      try {
        const index = store.wishlistGames.findIndex((g) => g.id === game.id);
        if (index !== -1) {
          store.wishlistGames.splice(index, 1);
          localStorage.setItem(
            "wishlistGames",
            JSON.stringify(store.wishlistGames)
          );

          // Update wishlist on backend
          await axiosClient.delete(
            `/wishlists/${store.user.id}/games/${game.id}`
          );
        }
      } catch (error) {
        console.error("Error removing game from wishlist:", error);
        alert("Failed to remove game from wishlist.");
      }
    },

    async addToCart(game) {
      try {
        if (game.availableQuantity === 0) {
          alert("No available copies of this game.");
          return;
        }

        // First, get available specific games for this game
        const response = await axiosClient.get("/specificGames");
        const availableSpecificGame = response.data.find(
          (sg) => sg.gameId === game.id && sg.availability
        );

        if (!availableSpecificGame) {
          alert("No available copies of this game.");
          return;
        }

        // Check if game is already in cart
        const exists = store.cartSpecificGames.find(
          (sg) => sg.serialNumber === availableSpecificGame.serialNumber
        );

        if (exists) {
          alert("This game is already in your cart.");
          return;
        }

        // Mark the specific game as unavailable
        await axiosClient.put(
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
  },
};
</script>

<style scoped>
.wishlist-page {
  padding: 20px;
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 2rem;
}

.games-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
}

.game-item {
  border: 1px solid #ddd;
  padding: 15px;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.game-item h2 {
  margin-top: 0;
  color: #333;
  margin-bottom: 10px;
}

.game-item p {
  margin: 8px 0;
  color: #555;
}

button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
  font-weight: 500;
  transition: background-color 0.2s, opacity 0.2s;
}

.remove-btn {
  background-color: #f44336; /* Red */
  color: white;
  margin-bottom: 5px;
}

.remove-btn:hover {
  background-color: #d32f2f;
}

.add-to-cart-btn {
  background-color: #4caf50; /* Green */
  color: white;
}

.add-to-cart-btn:hover {
  background-color: #388e3c;
}
</style>
