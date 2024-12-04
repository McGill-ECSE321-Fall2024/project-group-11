<template>
  <div class="login-container">
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="userName">Username:</label>
        <input type="text" v-model="userName" required />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="userType">User Type:</label>
        <select v-model="userType" required>
          <option value="customer">Customer</option>
          <option value="staff">Staff</option>
        </select>
      </div>
      <button type="submit">Login</button>
    </form>
    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>
    <p class="signup-link">
      Don't have an account?
      <a @click="goToSignup">Create one here</a>
    </p>
  </div>
</template>

<script>
import axios from "axios";
import { store } from "../store.js";

const axiosClient = axios.create({
  baseURL: "http://localhost:8081",
});

export default {
  name: "Login",
  data() {
    return {
      userName: "",
      password: "",
      userType: "customer",
      errorMessage: "",
    };
  },
  methods: {
    async login() {
      try {
        const response = await axiosClient.post("/login", {
          userName: this.userName,
          password: this.password,
          userType: this.userType,
        });
        localStorage.setItem("user", JSON.stringify(response.data));
        localStorage.setItem("userType", this.userType);
        store.user = response.data;
        store.userType = this.userType;
        this.$router.push("/home");
      } catch (e) {
        console.error("Login failed:", e.response);
        if (e.response && e.response.status === 401) {
          this.errorMessage = "Invalid username or password";
        } else {
          this.errorMessage = "An error occurred during login";
        }
      }
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
  margin: 40px auto;
  padding: 20px;
}

h1 {
  text-align: center;
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input, select {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #45a049;
}

.error-message {
  color: red;
  text-align: center;
  margin-top: 10px;
}

.signup-link {
  text-align: center;
  margin-top: 15px;
}

.signup-link a {
  color: #4CAF50;
  cursor: pointer;
  text-decoration: none;
}

.signup-link a:hover {
  text-decoration: underline;
}
</style>
