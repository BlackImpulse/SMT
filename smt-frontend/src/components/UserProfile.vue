<template>
  <div class="user">
    <div class="tabs">
      <button v-for="tab in tabs"
              :key="tab" @click="selected = tab;"
              :class="['tab-btn', { active: selected === tab }]">
        {{ tab }}
      </button>
    </div>
    <component :is="selected"></component>
  </div>
</template>

<script>
import Profile from "@/components/Profile";
import History from "@/components/History";
import Apps from "@/components/Apps";

export default {
  name: "UserProfile",
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (!this.loggedIn) {
      this.$router.push('/');
    }
  },
  data() {
    return {
      tabs: ["Profile", "History", "Apps"],
      selected: "Profile",
    }
  },
  methods: {},
  components: {
    Profile, History, Apps
  }
}
</script>

<style scoped>
.user {
  margin-top: 153px;
}

.tab-btn {
  width: 386px;
  height: 50px;
  margin-right: 4px;


  background: #000000;
  border: 3px solid #000000;
  box-sizing: border-box;
  border-radius: 15px 15px 0 0;

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 35px;

  color: #BBBDCA;
}

.active {
  background: #FFFFFF;
  color: #000000;
}

</style>