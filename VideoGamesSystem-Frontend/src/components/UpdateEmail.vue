<template>
    <div class="Update-container">
      <h1>Update Email</h1>
      <form @submit.prevent="updateEmail">
        <div>
          <label for="email">New Email:</label>
          <input type="email" v-model="email" required />
        </div>
        <button type="submit">Update Email</button>
      </form>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  const axiosUpdateEmail = axios.create({
    baseURL: "http://localhost:8081",
  });
  
  export default {
    name: "UpdateEmail",
    data() {
      return {
        email: "",
        errorMessage: "",
      };
    },
    methods: {
      async updateEmail() {
        try {
          const response = await axiosUpdateEmail.put("/user/email", {
            email: this.email,
          });
          alert("Email updated successfully!");
          this.$router.push("/my-account");
        } catch (e) {
          console.error(e);
          this.errorMessage =
            e.response?.data?.errors?.[0] ||
            "An error occurred while updating your email.";
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
  