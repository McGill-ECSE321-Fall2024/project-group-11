<template>
    <div>
      <h1>Welcome to Video Games System</h1>
  
      <div class="home-page">
        <h2>Available Games</h2>
        <div class="filters">
          <label for="sort">Sort by:</label>
          <select v-model="sortOption" @change="sortGames">
            <option value="price">Price</option>
            <option value="category">Category</option>
            <option value="consoleType">Console Type</option>
          </select>
        </div>
        <div class="games-list">
          <div v-for="game in games" :key="game.id" class="game-item">
            <h3>{{ game.title }}</h3>
            <p class="description">{{ game.description }}</p>
            <p class="price">Price: ${{ game.price }}</p>
            <p class="category">Category: {{ game.category }}</p>
            <p class="console">Console: {{ game.consoleType }}</p>
            <p class="availability" :class="{ 'out-of-stock': game.availableQuantity === 0 }">
              {{ game.availableQuantity > 0 ? 'In Stock' : 'Out of Stock' }}
            </p>
            <div class="login-prompt">
              <router-link to="/login" class="login-link">Log in to purchase</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "GuestHome",
    data() {
      return {
        sortOption: "price",
        games: [
          {
            id: 1,
            title: "Where's my Rami?",
            description: "An adventure where you try to find the work Rami did on this deliverable.",
            price: 60,
            category: "Action",
            consoleType: "PS4",
            availableQuantity: 1,
          },
          {
            id: 2,
            title: "Mystery tests",
            description: "A game where rami finds all non functional tests and comments them out instead of fixing code",
            price: 40,
            category: "Puzzle",
            consoleType: "Switch",
            availableQuantity: 1,
          },
          {
            id: 3,
            title: "Tennis Pro",
            description: "Game where you get to hit Emile with a tennis racket",
            price: 50,
            category: "Racing",
            consoleType: "PS4",
            availableQuantity: 0,
          },
          {
            id: 4,
            title: "AYCE HotPot",
            description: "Extreme sport where everyone eats hotpot until they throw up",
            price: 55,
            category: "Sports",
            consoleType: "Switch",
            availableQuantity: 3,
          },
          {
            id: 5,
            title: "Fantazizi",
            description: "Explore the fantasyzi world.",
            price: 45,
            category: "RPG",
            consoleType: "PS4",
            availableQuantity: 1,
          },
        ],
      };
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
  
  .filters {
    margin-bottom: 20px;
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
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    transition: transform 0.2s;
  }
  
  .game-item:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
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
  
  .category, .console {
    color: #666;
    font-size: 0.9rem;
  }
  
  .availability {
    color: #4caf50;
    font-weight: bold;
    margin: 10px 0;
  }
  
  .out-of-stock {
    color: #f44336;
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
  </style>