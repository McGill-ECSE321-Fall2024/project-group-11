<template>
    <div class="like-dislike-buttons">
      <button @click="incrementLike" :disabled="userLiked">
        👍 Like ({{ likeCount }})
      </button>
      <button @click="incrementDislike" :disabled="userDisliked">
        👎 Dislike ({{ dislikeCount }})
      </button>
      <p v-if="userLiked">You liked this game!</p>
      <p v-if="userDisliked">You disliked this game!</p>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  const axiosClient = axios.create({
    baseURL: "http://localhost:8081", // Update with your backend base URL
  });
  
  export default {
    name: "DislikeLikeButton",
    props: {
      gameId: {
        type: String,
        required: true,
      },
    },
    data() {
      return {
        likeCount: 0,
        dislikeCount: 0,
        userLiked: false,
        userDisliked: false,
      };
    },
    created() {
      this.fetchLikeDislikeCounts();
      this.checkUserInteraction();
    },
    methods: {
      async fetchLikeDislikeCounts() {
        try {
          const response = await axiosClient.get(`/games/${this.gameId}/likes`);
          this.likeCount = response.data.likeCount || 0;
          this.dislikeCount = response.data.dislikeCount || 0;
        } catch (error) {
          console.error("Error fetching like/dislike counts:", error);
        }
      },
      async checkUserInteraction() {
        try {
          const response = await axiosClient.get(`/games/${this.gameId}/user-interaction`);
          this.userLiked = response.data.liked || false;
          this.userDisliked = response.data.disliked || false;
        } catch (error) {
          console.error("Error checking user interaction:", error);
        }
      },
      async incrementLike() {
        if (!this.userLiked && !this.userDisliked) {
          try {
            await axiosClient.put(`/games/${this.gameId}/like`);
            this.likeCount += 1;
            this.userLiked = true;
          } catch (error) {
            console.error("Error incrementing like:", error);
          }
        }
      },
      async incrementDislike() {
        if (!this.userLiked && !this.userDisliked) {
          try {
            await axiosClient.put(`/games/${this.gameId}/dislike`);
            this.dislikeCount += 1;
            this.userDisliked = true;
          } catch (error) {
            console.error("Error incrementing dislike:", error);
          }
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .like-dislike-buttons {
    display: flex;
    flex-direction: column;
    gap: 10px;
    margin-top: 10px;
  }
  button {
    padding: 5px 10px;
    font-size: 14px;
    cursor: pointer;
  }
  button:disabled {
    cursor: not-allowed;
    opacity: 0.6;
  }
  </style>
  