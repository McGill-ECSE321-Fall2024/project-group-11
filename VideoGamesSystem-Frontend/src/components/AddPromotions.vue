<!-- src/components/AddPromotions.vue -->
<template>
    <div class="AddPromotions-container">
      <h1>Add a Promotion to a Game</h1>
      <form @submit.prevent="addPromotion">
        <div>
          <label for="discount">Discount (%):</label>
          <input type="number" v-model="percentage" required />
        </div>
  
        <div>
          <label for="startDate">Start Date:</label>
          <input type="date" id="startDate" v-model="startDate" required />
        </div>
  
        <div>
          <label for="endDate">End Date:</label>
          <input type="date" id="endDate" v-model="endDate" required />
        </div>
  <!-- we need to be able to select the games we want to apply prromotion to, should be able to do to all games,single game i think Maybe category
        <div>
          <label for="game">Select Game:</label>
          <select id="game" v-model="selectedGame" required>
            <option value="" disabled>Select a game</option>
            <option v-for="game in games" :key="game.id" :value="game.id">
              {{ game.title }}
            </option>
          </select>
        </div>
  -->
        <button type="submit">Add Promotion</button>
      </form>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import { store } from "../store.js";
  
  const axiosPromotion = axios.create({
    baseURL: "http://localhost:8081",
  });
  
  export default {
    name: "AddPromotion",
    data() {
      return {
        percentage: null,
        startDate: null,
        endDate: null,
        //selectedGame: "", // Holds the selected game ID
        //games: [], // List of games to populate the dropdown
        errorMessage: "",
      };
    },
    /* by chat 
    in DOM ??
    export default {
  mounted() {
    // Code to run after the component is mounted
    console.log("Component is mounted");
  },
};

    async mounted() {
      // Fetch games to populate the dropdown
      try {
        const response = await axiosPromotion.get("/games");
        this.games = response.data;
      } catch (e) {
        console.error("Failed to load games:", e);
        this.errorMessage = "Failed to fetch games for promotion.";
      }
    },
    */
   // + add a method whch associate the promotion to the selected games 
    methods: {
      async addPromotion() {
        const newPromotion = {
          percentage: this.percentage,
          startDate: this.startDate,
          endDate: this.endDate,
          gameId: this.selectedGame, // Link the promotion to the selected game
        };
  
        try {
          const response = await axiosPromotion.post("/promotions", newPromotion);
  
          if (!store.promotions) store.promotions = [];
          localStorage.setItem("promotion", JSON.stringify(response.data));
  
          this.$router.push("/home");
        } catch (e) {
          console.error(e);
          if (
            e.response &&
            e.response.data &&
            e.response.data.errors &&
            e.response.data.errors[0]
          ) {
            this.errorMessage = e.response.data.errors[0];
          } else {
            this.errorMessage = "An error occurred while adding the promotion.";
          }
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .AddPromotions-container {
    max-width: 400px;
    margin: 0 auto;
  }
  .error-message {
    color: red;
  }
  </style>
  