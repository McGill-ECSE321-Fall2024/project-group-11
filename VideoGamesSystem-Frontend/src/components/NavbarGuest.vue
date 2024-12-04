<!-- src/components/NavbarGuest.vue -->
<template>
    <nav class="navbar">
      <div class="nav-brand">Video Games System</div>
      <div class="nav-links">
        <router-link to="/login" class="nav-link">Login</router-link>
        <router-link to="/signup" class="nav-link">Sign Up</router-link>
      </div>
    </nav>
  </template>
  
  <script>
  export default {
    name: 'NavbarGuest'
  }
  </script>
  
  <style scoped>
  .navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem 2rem;
    background-color: #333;
    color: white;
  }
  
  .nav-brand {
    font-size: 1.5rem;
    font-weight: bold;
  }
  
  .nav-links {
    display: flex;
    gap: 1rem;
  }
  
  .nav-link {
    color: white;
    text-decoration: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    transition: background-color 0.3s;
  }
  
  .nav-link:hover {
    background-color: #555;
  }
  </style>