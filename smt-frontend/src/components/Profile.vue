<template>
  <div class="profile">
    <img src="../assets/pics/profile-user.svg" alt="user_icon"/>
    <div class="user-box">
      <img src="../assets/pics/profile-user.svg" alt="user_icon"/>
      <p class="content">{{ username}}</p>
      <p class="change">change</p>
    </div>
    <div class="user-box">
      <img src="../assets/pics/keys-hole.svg" alt="user_icon"/>
      <p class="content">●●●●●●●●●</p>
      <p class="change">change</p>
    </div>
    <div class="logout-box">
      <p>Date of registration: {{date}}</p>
      <button @click="logout">Logout</button>
    </div>
  </div>
</template>

<script>
import AuthService from "@/service/auth.service";
import UserService from "@/service/user.service";

export default {
  name: "Profile",
  beforeMount() {
    UserService.getUserByUsername(this.$store.state.auth.user.username).then((response) => {
      this.date = response.data.registrationDate;
      this.username = response.data.username;
    })
  },
  data() {
    return {
      date: "",
      username: ""
    }
  },
  methods: {
    logout() {
      AuthService.logout();
      this.$router.redirectedFrom('/');
    }
  }
}
</script>

<style scoped>
.profile {
  text-align: left;
}
.profile img {
  width: 200px;
  height: 200px;
  margin-top: 96px;
  margin-left: 93px;
  margin-bottom: 92px;
}

.profile .user-box {
  width: 390px;
  height: 64px;
  margin-bottom: 30px;

  background: #C4C4C4;
  border-radius: 15px;

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 35px;
}

.profile .user-box img {
  width: 38px;
  height: 38px;
  margin-top: 13px;
  margin-left: 13px;
  margin-right: 15px;
}

.profile .user-box .content {
  margin-top: 18px;
  margin-bottom: 0;
  position: absolute;
  display: inline-block;
}

.profile .user-box .change {
  margin-top: 18px;
  margin-left: 381px;
  margin-bottom: 0;
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 35px;
  display: inline-block;
  position: absolute;

  color: #FF0000;
}

.profile .logout-box {
  text-align: right;
}

.profile .logout-box p {
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 28px;
  line-height: 41px;

  color: #000000;
}

.profile .logout-box button {
  width: 177px;
  height: 45px;
  background: #000000;
  border-radius: 15px;
  border-style: solid;
  border-color: #000000;

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 30px;
  line-height: 44px;

  color: #657EFF;
}

.profile .logout-box button:hover {
  background: #657EFF;
  border-color: #657EFF;;
  color: #000000;
}

</style>