<!-- src/App.vue -->
<template>
  <div id="app">
    <navbarOwn />
    <router-view />
  </div>
</template>

<script>
import Navbar from "./components/Navbar.vue";
import navbarOwn from "./components/NavbarOwn.vue"
import { store } from "./store.js";

export default {
  name: "App",
  components: {
    Navbar,
    navbarOwn,
  },
  setup() {
    return { store };
  },
};
</script>

<style>
/* Your existing styles */
</style>
