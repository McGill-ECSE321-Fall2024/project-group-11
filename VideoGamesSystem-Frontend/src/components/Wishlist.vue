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
      store.wishlistGames =
        JSON.parse(localStorage.getItem("wishlistGames")) || [];
    },

    async removeFromWishlist(game) {
      const index = store.wishlistGames.findIndex(
        (g) => g.id === game.id
      );
      if (index !== -1) {
        store.wishlistGames.splice(index, 1);
        localStorage.setItem(
          "wishlistGames",
          JSON.stringify(store.wishlistGames)
        );
      }
    },

    async addToCart(game) {
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
  },
};
</script>

<style scoped>
/* Add styles here */
</style>
