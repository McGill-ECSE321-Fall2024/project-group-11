<template>
  <div>
    <h1>Welcome, {{ user.userName }}!</h1>
    <div v-if="userType === 'customer'">
      <!-- Customer-specific content -->
      <p>You are logged in as a customer.</p>
      <!-- Add customer functionalities -->
    </div>
    <div v-else-if="userType === 'staff'">
      <!-- Staff-specific content -->
      <p>You are logged in as a staff member.</p>
      <!-- Add staff functionalities -->
    </div>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: null,
      userType: null,
    };
  },
  created() {
    const userData = localStorage.getItem("user");
    const userType = localStorage.getItem("userType");
    if (userData && userType) {
      this.user = JSON.parse(userData);
      this.userType = userType;
    } else {
      // If no user is logged in, redirect to login
      this.$router.push("/login");
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("user");
      localStorage.removeItem("userType");
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
/* Add styles here */
</style>
