import { reactive } from "vue";

export const store = reactive({
  user: JSON.parse(localStorage.getItem("user")) || null,
  userType: localStorage.getItem("userType") || null,
  games:[],
  cartSpecificGames:
    JSON.parse(localStorage.getItem("cartSpecificGames")) || [],
  wishlistGames: JSON.parse(localStorage.getItem("wishlistGames")) || [],
  // Other shared state can be added here
});
