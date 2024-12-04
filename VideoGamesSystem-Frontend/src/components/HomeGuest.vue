<template>
  <div>
    <h1>Welcome to Video Games System</h1>

    <div class="home-page">
      <h2>Available Games</h2>

      <!-- Search Bar -->
      <div class="search-bar">
        <label for="search">Search Games:</label>
        <input
          type="text"
          id="search"
          v-model="searchQuery"
          placeholder="Enter game title or description..."
        />
      </div>

      <!-- Sort Controls -->
      <div class="filters">
        <label for="sort">Sort by:</label>
        <select v-model="sortOption" @change="sortGames">
          <option value="price">Price</option>
          <option value="category">Category</option>
          <option value="consoleType">Console Type</option>
          <option value="title">Title</option>
        </select>
      </div>

      <!-- Loading and Error States -->
      <div v-if="loading" class="loading">Loading games...</div>

      <div v-else-if="error" class="error">
        {{ error }}
      </div>

      <!-- Games List -->
      <div v-else class="games-list">
        <div v-for="game in sortedGames" :key="game.id" class="game-item">
          <h3>{{ game.title }}</h3>
          <p class="description">{{ game.description }}</p>
          <p class="price">Price: ${{ game.price }}</p>
          <p class="category">Category: {{ game.category }}</p>
          <p class="console">Console: {{ game.consoleType }}</p>
          <!-- Availability Paragraph -->
          <p
            class="availability"
            :class="{
              'in-stock': game.availableQuantity > 0,
              'out-of-stock': game.availableQuantity === 0,
            }"
          >
            {{ game.availableQuantity > 0 ? "In Stock" : "Out of Stock" }}
          </p>
          <div class="login-prompt">
            <router-link to="/login" class="login-link"
              >Log in to purchase</router-link
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "GuestHome",
  data() {
    return {
      sortOption: "price",
      searchQuery: "", // New data property for search
      games: [],
      loading: false,
      error: null,
    };
  },
  computed: {
    sortedGames() {
      // First, filter the games based on the search query
      let filtered = this.games.filter((game) => {
        const query = this.searchQuery.trim().toLowerCase();
        if (!query) return true; // If search query is empty, include all games

        // Check if the query matches the title or description
        return (
          game.title.toLowerCase().includes(query) ||
          game.description.toLowerCase().includes(query)
        );
      });

      // Then, sort the filtered games based on the sortOption
      return filtered.sort((a, b) => {
        if (this.sortOption === "price") {
          return a.price - b.price;
        } else if (this.sortOption === "category") {
          return a.category.localeCompare(b.category);
        } else if (this.sortOption === "consoleType") {
          return a.consoleType.localeCompare(b.consoleType);
        } else if (this.sortOption === "title") {
          return a.title.localeCompare(b.title);
        }
        return 0;
      });
    },
  },
  methods: {
    async fetchGames() {
      this.loading = true;
      this.error = null;
      try {
        const response = await axios.get("http://localhost:8081/games");
        this.games = response.data;
      } catch (err) {
        console.error("Error fetching games:", err);
        this.error = "Failed to load games. Please try again later.";
      } finally {
        this.loading = false;
      }
    },
    sortGames() {
      // Sorting is handled by the computed property
    },
  },
  created() {
    this.fetchGames();
  },
};
</script>

<style scoped>
.home-page {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
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
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ddd;
}

.filters {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.filters label {
  margin-right: 10px;
  font-weight: bold;
}

.filters select {
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ddd;
}

.games-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
  padding: 10px;
}

.game-item {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 20px;
  background: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.game-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h3 {
  color: #2c3e50;
  margin-top: 0;
  font-size: 1.2rem;
  margin-bottom: 10px;
}

.description {
  color: #666;
  font-size: 0.9rem;
  margin-bottom: 15px;
  line-height: 1.4;
}

.price {
  font-weight: bold;
  color: #2c3e50;
  font-size: 1.1rem;
}

.category,
.console {
  color: #666;
  font-size: 0.9rem;
}

.availability {
  font-weight: bold;
  margin: 10px 0;
}

/* New classes for availability colors */
.in-stock {
  color: #4caf50; /* Green */
}

.out-of-stock {
  color: #f44336; /* Red */
}

.login-prompt {
  margin-top: 15px;
  padding: 10px;
  background-color: #f5f5f5;
  border-radius: 4px;
  text-align: center;
}

.login-link {
  color: #1976d2;
  text-decoration: none;
  font-weight: 500;
}

.login-link:hover {
  text-decoration: underline;
}

.loading,
.error {
  text-align: center;
  font-size: 1.1rem;
  color: #666;
  margin: 20px 0;
}

.error {
  color: #f44336;
}
</style>
