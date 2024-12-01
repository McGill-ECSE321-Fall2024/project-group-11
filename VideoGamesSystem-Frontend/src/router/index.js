// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import Home from "../components/Home.vue";
import Wishlist from "../components/Wishlist.vue";
import Cart from "../components/Cart.vue";
import MyGames from "../components/MyGames.vue";
import Login from "../components/Login.vue";
import Signup from "../components/Signup.vue";
import HomeOwner from "@/components/HomeOwner.vue";
import AddPromotions from "@/components/AddPromotions.vue";
import AddGames from "@/components/AddGames.vue";
import AddEmployee from "@/components/AddEmployee.vue";

const routes = [
  { path: "/", name: "Home", component: Home },
  { path: "/home", name: "HomePage", component: Home },
  { path: "/wishlist", name: "Wishlist", component: Wishlist },
  { path: "/cart", name: "Cart", component: Cart },
  { path: "/my-games", name: "MyGames", component: MyGames },
  { path: "/login", name: "Login", component: Login },
  { path: "/signup", name: "Signup", component: Signup },
  { path: "/addPromotions", name: "AddPromotions", component: AddPromotions },
  { path: "/addGames", name: "AddGames", component: AddGames },
  { path: "/homeOwner", name: "HomeOwner", component: HomeOwner },
  { path: "/addEmployee", name: "AddEmployee", component: AddEmployee },
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
