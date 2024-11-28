<!-- src/components/NavbarOwn.vue -->
<template>
    <nav class="navbarOwn" v-if="store.user">
      <ul>
        <li><router-link to="/homeOwner">Home</router-link></li>
        <li><router-link to="/Add-Games">Add-Games</router-link></li>
        <li><router-link to="/Add-Promotion">Add-Promotion</router-link></li>
        <li><router-link to="/Add-Employee">Add-Promotion</router-link></li>
        <li><router-link to="/Delete-Employee">Delete-employee</router-link></li>
        <li @click="logout">Logout</li>
      </ul>
    </nav>
  </template>
  
  <script>
  import { store } from "../store.js";
  
  export default {
    name: "navbarOwn",
    setup() {
      return { store };
    },
    methods: {
      logout() {
        localStorage.removeItem("user");
        localStorage.removeItem("userType");
        localStorage.removeItem("cartSpecificGames");// maybe more items to remove , to determine
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
  