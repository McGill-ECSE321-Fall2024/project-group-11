<!-- src/components/Navbar.vue -->
<template>
    <nav class="navbarEmp" v-if="store.user">
      <ul>
        <li><router-link to="/homeEmp">Home</router-link></li>
        <li><router-link to="/AddGames">AddGames</router-link></li>
        <li><router-link to="/AddPromotions">AddPromotions</router-link></li>
        <li><router-link to="/MyAccount">MyAccount</router-link></li>

        <li @click="logout">Logout</li>
      </ul>
    </nav>
  </template>
  
  <script>
  import { store } from "../store.js";
  
  export default {
    name: "navbarEmp",
    setup() {
      return { store };
    },
    methods: {
      logout() {
        localStorage.removeItem("user");
        localStorage.removeItem("userType");
        localStorage.removeItem("cartSpecificGames");
        store.user = null;
        store.userType = null;
        this.$router.push("/login");
      },
    },
  };
  </script>
  
  <style scoped>
  .navbar {
    background-color: #333;
    overflow: hidden;
  }
  .navbar ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    display: flex;
  }
  .navbar li {
    padding: 14px 20px;
  }
  .navbar a {
    color: #f2f2f2;
    text-decoration: none;
  }
  .navbar a:hover {
    background-color: #ddd;
    color: black;
  }
  </style>
  