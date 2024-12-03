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
        <select v-model="category" required>
          <option value="">Select a category</option>
          <option value="ACTION">Action</option>
          <option value="ADVENTURE">Adventure</option>
          <option value="PUZZLE">Puzzle</option>
          <option value="RACING">Racing</option>
          <option value="RPG">RPG</option>
          <option value="SPORTS">Sports</option>
          <option value="STRATEGY">Strategy</option>
        </select>
      </div>
      <div>
        <label for="consoleType">Console Type:</label>
        <select v-model="consoleType" required>
          <option value="">Select a console type</option>
          <option value="PS4">PS4</option>
          <option value="PS5">PS5</option>
          <option value="XBOX_ONE">Xbox One</option>
          <option value="XBOX_X">Xbox Series X</option>
          <option value="SWITCH">Nintendo Switch</option>
          <option value="PC">PC</option>
        </select>
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
      const newGame = {
        description: this.description,
        price: this.price,
        title: this.title,
        category: this.category,
        consoleType: this.consoleType,
      };
      try {
        const response = await axiosGame.post("/games", newGame);
        localStorage.setItem("game", JSON.stringify(response.data));
        
        if (!store.games) store.games = [];
        store.games.push(response.data);

        const userType = store.userType || localStorage.getItem("userType");
        if (userType === "OWNER") {
          this.$router.push("/homeOwner");
        } else if (userType === "EMPLOYEE") {
          this.$router.push("/homeEmp");
        } else {
          console.error("Unknown user type:", userType);
          this.$router.push("/login");
        }
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
select {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}
input {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}
label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}
button {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background-color: #45a049;
}
</style>
