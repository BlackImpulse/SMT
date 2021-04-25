<template>
  <header class="header">
    <ul>
      <li class="logo"><router-link to="/"><img src="../assets/pics/logo.png" alt="logo"></router-link></li>
      <li class="text">SONG MIGRATION TOOL</li>
      <li class="button">
        <router-link :to="'/' + (this.$store.state.auth.status.loggedIn ? 'profile' : 'sign-up')">
          <button>{{this.$store.state.auth.status.loggedIn ? this.$store.state.auth.user.username : "Sign up"}}</button>
        </router-link>
      </li>
    </ul>
  </header>
</template>

<script>
export default {
  name: "Header",
}
</script>

<style scoped>
.header {
  top: 0;
  left: 0;
  width: 100%;
  height: 88px;
  position: absolute;
}

.header ul {
  list-style: none;
  margin-left: auto;
  margin-right: auto;
  max-width: 1170px;
  padding: 0;
}

.header ul li {
  display: inline;
}

.header ul li.logo {
}

.header ul li.logo img {
  max-width: 154px;
  max-height: 61px;
}

.header ul li.text {
  margin-left: 63px;
  font-family: "Bungee Hairline", sans-serif;
  font-style: normal;
  font-weight: bold;
  font-size: 22px;
  line-height: 26px;
  color: #000000;
  overflow-wrap: break-word;
  max-width: 150px;
  display: inline-flex;
  position: absolute;
  top: 6px;
}

.header ul li.button {
  margin-left: 840px;
  top: 22px;
  position: absolute;
}

.header ul li.button button {
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
.header ul li.button button:hover {
  background: #657EFF;
  border-color: #657EFF;;
  color: #000000;
}
</style>