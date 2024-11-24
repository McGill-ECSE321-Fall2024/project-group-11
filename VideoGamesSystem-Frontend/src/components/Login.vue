<template>
  <div class="login-container">
    <h1>Game Shop Login</h1>
    <form @submit.prevent="login">
      <div>
        <label for="userType">User Type:</label>
        <select v-model="userType" required>
          <option value="customer">Customer</option>
          <option value="staff">Staff</option>
        </select>
      </div>
      <div>
        <label for="userName">Username:</label>
        <input type="text" v-model="userName" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" v-model="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>
    <p>
      Don't have an account?
      <a v-if="userType === 'customer'" @click="goToSignup">Create one here</a>
    </p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      userType: "customer",
      userName: "",
      password: "",
      errorMessage: "",
    };
  },
  methods: {
    login() {
      axios
        .post("http://localhost:8080/login", {
          userName: this.userName,
          password: this.password,
          userType: this.userType,
        })
        .then((response) => {
          // Save user data to local storage
          localStorage.setItem("user", JSON.stringify(response.data));
          localStorage.setItem("userType", this.userType);
          // Redirect to the main page
          this.$router.push("/home");
        })
        .catch((error) => {
          // Handle login error
          console.error("Login failed:", error.response);
          if (error.response && error.response.status === 401) {
            this.errorMessage = "Invalid username or password";
          } else {
            this.errorMessage = "An error occurred during login";
          }
        });
    },
    goToSignup() {
      this.$router.push("/signup");
    },
  },
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
}
.error-message {
  color: red;
}
</style>
