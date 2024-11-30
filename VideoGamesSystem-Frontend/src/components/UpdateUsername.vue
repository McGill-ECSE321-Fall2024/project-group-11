<template>
    <div class="Update-container">
      <h1>Update Username</h1>
      <form @submit.prevent="updateUsername">
        <div>
          <label for="username">New Username:</label>
          <input type="text" v-model="username" required />
        </div>
        <button type="submit">Update Username</button>
      </form>
      <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  const axiosUpdateUsername = axios.update({
    baseURL: "http://localhost:8081",
  });
  
  export default {
    name: "UpdateUsername",
    data() {
      return {
        username: "",
        errorMessage: "",
      };
    },
    methods: {
      async updateUsername() {
        try {
          const response = await axiosUpdateUsername.put("/user/username", {
            username: this.username,
          });
          alert("Username updated successfully!");
          this.$router.push("/my-account");
        } catch (e) { // error message jen ai auvune idee
          console.error(e);
          this.errorMessage =
            e.response?.data?.errors?.[0] ||
            "An error occurred while updating your username.";
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .Update-container {
    max-width: 400px;
    margin: 0 auto;
  }
  .error-message {
    color: red;
  }
  </style>
  