// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import Home from "../components/Home.vue";
import Wishlist from "../components/Wishlist.vue";
import Cart from "../components/Cart.vue";
import MyGames from "../components/MyGames.vue";
import Login from "../components/Login.vue";
import Signup from "../components/Signup.vue";
import AddEmployee from "../components/AddEmployee.vue";
import AddGames from "../components/AddGames.vue";
import AddPromotions from "../components/AddPromotions.vue";
import MyAccount from "../components/MyAccount.vue";
import UpdateUsername from "../components/UpdateUsername.vue";
import UpdateEmail from "../components/UpdateEmail.vue";
import UpdatePassword from "../components/UpdatePassword.vue";



const routes = [
  { path: "/", name: "Home", component: Home },
  { path: "/home", name: "HomePage", component: Home },
  { path: "/wishlist", name: "Wishlist", component: Wishlist },
  { path: "/cart", name: "Cart", component: Cart },
  { path: "/my-games", name: "MyGames", component: MyGames },
  { path: "/login", name: "Login", component: Login },
  { path: "/signup", name: "Signup", component: Signup},
  { path : "AddEmployee", name: "AddEmployee", component: AddEmployee},
  { path : "AddGames", name: "AddGames", component: AddGames},
  { path : "AddPromotions", name: "AddPromotions", component: AddPromotions},
  { path : "MyAccount", name: "MyAccount", component: MyAccount},
  { path : "UpdateUserName", name: "UpdateUserName", component: UpdateUserName},
  { path : "UpdateEmail", name: "UpdateEmail", component: UpdateEmail},
  { path : "UpdatePassword", name: "UpdatePassword", component: UpdatePassword},
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Navigation guard to protect routes
router.beforeEach((to, from, next) => {
  const publicPages = ["/login", "/signup"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");

  if (authRequired && !loggedIn) {
    return next("/login");
  }
  next();
});

export default router;
