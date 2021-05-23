<template>
  <div class="spotify">
  </div>
</template>

<script>
import TokenService from '@/service/token.service'
import UserService from '@/service/user.service'
import Token from '@/models/token'

export default {
  name: "Spotify",
  props: {
    code: {
      type: String,
      default: ""
    }
  },
  beforeMount() {
    if (this.code) {
      UserService.getUserByUsername(this.$store.state.auth.user.username).then(response => {
        let token = new Token(response.data.id, 1);
        TokenService.saveToken(token, this.code).then(() => {
          window.close();
        });
      });
    }
  }
}
</script>

<style scoped>

</style>