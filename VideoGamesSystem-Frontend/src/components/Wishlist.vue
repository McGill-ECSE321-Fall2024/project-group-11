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
    
    const response = await axiosClient.get(`/wishlists/customer/${store.user.id}`);
    store.wishlistGames = response.data.games || [];
    localStorage.setItem("wishlistGames", JSON.stringify(store.wishlistGames));
  } catch (error) {
    console.error("Error fetching wishlist:", error);
    // Fall back to local storage
    store.wishlistGames = JSON.parse(localStorage.getItem("wishlistGames")) || [];
  }
},
async removeFromWishlist(game) {
  try {
    const index = store.wishlistGames.findIndex(g => g.id === game.id);
    if (index !== -1) {
      store.wishlistGames.splice(index, 1);
      localStorage.setItem("wishlistGames", JSON.stringify(store.wishlistGames));
      
      // Update wishlist on backend
      await axiosClient.delete(`/wishlists/${store.user.id}/games/${game.id}`);
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
  },
};
</script>

<style scoped>
/* Add styles here */
</style>
