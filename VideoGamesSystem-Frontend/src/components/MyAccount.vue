<template>
    <div class="MyAccount-container">
      <h1>My Account</h1>
      <p>Manage your account settings below:</p>
      <div class="button-group">
        <button @click="goToUpdateUsername">Update Username</button>
        <button @click="goToUpdateEmail">Update Email</button>
        <button @click="goToUpdatePassword">Update Password</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "MyAccount",
    methods: {
      goToUpdateUsername() {
        this.$router.push("/update-username");
      },
      goToUpdateEmail() {
        this.$router.push("/update-email");
      },
      goToUpdatePassword() {
        this.$router.push("/update-password");
      },
    },
  };// pour style cest chat 
  </script>
  
  <style scoped>
  .MyAccount-container {
    max-width: 400px;
    margin: 0 auto;
    text-align: center;
  }
  
  .button-group button {
    display: block;
    width: 100%;
    margin: 10px 0;
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
  }
  
  .button-group button:hover {
    background-color: #0056b3;
  }
  </style>
  