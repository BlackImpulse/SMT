<template>
  <div class="login">
    <ul>
      <router-link to="/sign-up"><li class="sign-up-tab"><p>SIGN UP</p></li></router-link>
      <li class="login-tab"><p>LOG IN</p></li>
    </ul>
    <form id="sign-up" @submit="checkForm">
      <div class="user-box">
        <input @focusin="inputFocusIn"
               v-model="username"
               type="text"
               name="username"
               required=""
               minlength="4"
               maxlength="9"
               autocomplete="off">
        <img src="../assets/pics/profile-user.svg" alt="user_icon"/>
      </div>
      <div class="user-box">
        <input @focusin="inputFocusIn"
               v-model="password"
               type="text" name="password"
               required=""
               minlength="4"
               maxlength="9"
               autocomplete="off">
        <img src="../assets/pics/keys-hole.svg" alt="password_icon"/>
      </div>
      <div class="user-box">
        <label> Stay logged in
          <input type="checkbox" v-model="stayLoggedIn">
          <span class="checkmark"></span>
        </label>
      </div>
      <button type="submit"><img src="../assets/pics/right.svg" alt="submit_icon"></button>
    </form>
  </div>
</template>

<script>
import User from "@/models/user";

export default {
  name: "Login",
  data() {
    return {
      usernameError: false,
      passwordError: false,
      username: "USERNAME",
      password: "PASSWORD",
      stayLoggedIn: true
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/');
    }
  },
  methods: {
    inputFocusIn($event) {
      const words = ['USERNAME', 'PASSWORD', 'CONFIRM PASSWORD']
      if (words.lastIndexOf($event.target.value) >= 0) {
        if ($event.target.value !== words[0]) {
          $event.target.type = "password";
        }
        $event.target.value = "";
      }
    },
    checkForm(e) {
      e.preventDefault();

      this.$store.dispatch('auth/login', new User(this.username, this.password)).then(
          () => {
            this.$router.push('/');
          },
          () => {
            this.usernameError = true;
          }
      )

      if (!this.passwordError || !this.usernameError) {
        return true;
      }
    }
  }
}
</script>

<style scoped>
.login {
  margin-top: 102px;
  margin-left: 345px;
  width: 480px;
  height: 620px;
  position: absolute;

  background: #657EFF;
  border-radius: 15px;
}

.login ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.login ul li {
  display: inline-block;
}

.login ul li.login-tab {
  width: 240px;
  height: 80px;

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 40px;
  line-height: 58px;

  color: #000000;

  background: #657EFF;
  border-radius: 0 15px 0 0;
}

.login ul li.sign-up-tab:hover {
  text-decoration-line: underline;
}

.login ul li.sign-up-tab {
  width: 240px;
  height: 80px;

  background: #000000;
  border-radius: 15px 0 0 0;

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 40px;
  line-height: 58px;

  color: #657EFF;
}

.login ul li p {
  margin-top: 11px;
}

.login form {
  margin-top: 45px;
}

.login form .user-box {
  position: relative;
  margin-bottom: 30px;
}

.login form .user-box input{
  width: 390px;
  height: 64px;

  background: #C4C4C4;
  border-radius: 15px;

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 35px;

  text-align: center;

  color: rgba(0, 0, 0, 0.76);
}

.login form .user-box img {
  max-height: 33px;
  max-width: 33px;
  position: absolute;
  top: 17px;
  left: 59px;
}

.login form button {
  position: absolute;
  margin-top: 50px;
  left: 200px;
  width: 80px;
  height: 80px;
  background: #C4C4C4;
  border-radius: 15px;
}

.login form .user-box p {

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 14px;
  line-height: 20px;

  color: #FF0000;
}
</style>