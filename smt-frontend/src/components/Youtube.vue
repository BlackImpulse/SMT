<template>
  <div class="youtube">
  </div>
</template>

<script>
import UserService from "@/service/user.service";
import Token from "@/models/token";
import TokenService from "@/service/token.service";

export default {
  name: "Youtube",
  props: {
    code: {
      type: String,
      default: ""
    }
  },
  beforeMount() {
    if (this.code) {
      UserService.getUserIdByUsername(this.$store.state.auth.user.username).then(response => {
        let token = new Token(response.data.id, 2);
        TokenService.saveToken(token, this.code);
      });
    }
    this.$router.push("/");
  }
}
</script>

<style scoped>

</style>