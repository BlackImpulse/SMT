<template>
  <div class="apps">
    <div class="app-box" v-for="app in apps" :key="app.id">
      <img v-bind:src="getImgUrl(app.service.toLowerCase())" :alt="app.service.toLowerCase()"/>
      <p class="name">{{app.service}}</p>
      <button class="delete" @click="deleteToken(app.id)">disconnect</button>
    </div>
  </div>
</template>

<script>
import UserService from '@/service/user.service';
import TokenService from '@/service/token.service';

export default {
  name: "Apps",
  beforeMount() {
    UserService.getAppsByUsername(this.$store.state.auth.user.username).then((response) => {
      this.apps = response.data;
    })
  },
  data() {
    return {
      apps: []
    }
  },
  methods: {
    getImgUrl(name) {
      return require('../assets/pics/' + name + ".png")
    },
    deleteToken(id) {
      TokenService.deleteByUsernameAndServiceId(this.$store.state.auth.user.username, id).then(() => {
        this.apps = this.apps.filter(app => app.id !== id);
      })
    }
  }
}
</script>

<style scoped>
.apps {
  margin-top: 107px;
  text-align: left;
}
.app-box {
  margin-bottom: 36px;
  height: 78px;
}

.app-box img {
  width: 64px;
  height: 64px;
  margin-right: 86px;
}

.app-box .name {
  width: 206px;
  position: absolute;
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 35px;
  color: #000000;
  margin-top: 16px;
  margin-bottom: 0;
  display: inline-block;
}
.app-box .delete {
  width: 177px;
  height: 45px;
  margin-top: 10px;
  position: absolute;
  margin-left: 529px;
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

.app-box .delete:hover {
  background: #657EFF;
  border-color: #657EFF;;
  color: #000000;
}
</style>