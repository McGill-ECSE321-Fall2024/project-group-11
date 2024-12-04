<template>
  <div class="AddEmployee-container">
    <h1>Add Employee</h1>
    <form @submit.prevent="addEmployee">
      <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required />
      </div>

      <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required />
      </div>

      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>

      <button type="submit">Add Employee</button>
    </form>

    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>
  </div>
</template>

<script>
import axios from "axios";

const axiosEmployee = axios.create({
  baseURL: "http://localhost:8081",
});

export default {
  name: "AddEmployee",
  data() {
    return {
      username: "",
      email: "",
      password: "",
      errorMessage: "",
    };
  },
  methods: {
    async addEmployee() {
      const newEmployee = {
        username: this.username,
        email: this.email,
        password: this.password,
      };

      try {
        const response = await axiosEmployee.post("/employees", newEmployee);
        localStorage.setItem("Employee", JSON.stringify(response.data));

        // Redirect to a different page (e.g., employee list) after successful submission
        this.$router.push("/HomeOwner"); // TODO: a ajouter
      } catch (e) {
        console.error("Error adding employee:", e);
        if (
          e.response &&
          e.response.data &&
          e.response.data.errors &&
          e.response.data.errors[0]
        ) {
          this.errorMessage = e.response.data.errors[0];
        } else {
          this.errorMessage = "An error occurred while adding the employee.";
        }
      }
    },
  },
};
</script>

<style scoped>
.AddEmployee-container {
  max-width: 400px;
  margin: 0 auto;
}

.error-message {
  color: red;
}
</style>
