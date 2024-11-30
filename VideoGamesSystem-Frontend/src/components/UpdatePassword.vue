<template>
    <div class="Update-container">
      <h1>Update Password</h1>
      <form @submit.prevent="updatePassword">
        <div>
          <label for="password">New Password:</label>
          <input type="password" v-model="password" required />
        </div>
        <div>
          <label for="confirmPassword">Confirm Password:</label>
          <input type="password" v-model="confirmPassword" required />
        </div>
        <button type="submit">Update Password</button>
      </form>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  const axiosUpdatePassword = axios.create({
    baseURL: "http://localhost:8081",
  });
  
  export default {
    name: "UpdatePassword",
    data() {
      return {
        password: "",
        confirmPassword: "",
        errorMessage: "",
      };
    },
    methods: {
      async updatePassword() {
        if (this.password !== this.confirmPassword) {
          this.errorMessage = "Passwords do not match.";
          return;
        }
        try {
          const response = await axiosUpdatePassword.put("/user/password", {
            password: this.password,
          });
          alert("Password updated successfully!");
          this.$router.push("/my-account");
        } catch (e) {
          console.error(e);
          this.errorMessage =
            e.response?.data?.errors?.[0] ||
            "An error occurred while updating your password.";
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
  