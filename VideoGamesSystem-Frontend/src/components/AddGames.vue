<!-- src/components/AddGames.vue -->
<template>
  <div class="AddGames-container">
    <h1>Add a New Game</h1>
    <form @submit.prevent="addGame">
      <div>
        <label for="title">Title:</label>
        <input type="text" v-model="title" required />
      </div>
      <div>
        <label for="description">Description:</label>
        <input type="text" v-model="description" required />
      </div>
      <div>
        <label for="price">Price:</label>
        <input type="number" v-model="price" required />
      </div>
      <div>
        <label for="category">Category:</label>
        <input type="text" v-model="category" required />
      </div>
      <div>
        <label for="consoleType">Console Type:</label>
        <input type="text" v-model="consoleType" required />
      </div>
      <button type="submit">Request to Add Game</button>
    </form>
    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { store } from "../store.js";

const axiosGame = axios.create({
  baseURL: "http://localhost:8081",
});

export default {
  name: "AddGame",
  data() {
    return {
      title: "",
      description: "",
      price: null,
      category: "",
      consoleType: "",
      errorMessage: "",
    };
  },
  methods: {
    async addGame() {
      const newGame = { // ordre matters je crois
        description: this.description,
        price: this.price,
        title: this.title,
        category: this.category,
        consoleType: this.consoleType,
      };
      try {
        const response = await axiosGame.post("/games", newGame);
        // jsp ce quil fait store dans local storage : pas bien compros a quoi ca sert 
        localStorage.setItem("game", JSON.stringify(response.data));

        
        if (!store.games) store.games = []; //ca cest chat:checks 
        //if the store.games property exists, and if it doesn’t, it initializes it as an empty array. After ensuring store.games is defined, it adds the new game data
        store.games.push(response.data);

        this.$router.push("/home");
      } catch (e) { // je pense pas que je dois changer le error , il est automatique non avec le backend
        console.error(e);
        if (
          e.response &&
          e.response.data &&
          e.response.data.errors &&
          e.response.data.errors[0]
        ) {
          this.errorMessage = e.response.data.errors[0];
        } else {
          this.errorMessage = "An error occurred while adding the game.";
        }
      }
    },
  },
};
</script>

<style scoped>
.AddGames-container {
  max-width: 400px;
  margin: 0 auto;
}
.error-message {
  color: red;
}
</style>
