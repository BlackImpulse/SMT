<template>
  <div class="history">
    <table>
      <tr>
        <th>
          Date
        </th>
        <th>
          From
        </th>
        <th>
          To
        </th>
        <th>
          Album/Song/Playlist
        </th>
        <th>
          Count
        </th>
      </tr>
      <tr v-for="item in history" :key="item.id">
        <th>
          {{item.date}}
        </th>
        <th>
          {{item.fromService.service}}
        </th>
        <th>
          {{item.toService.service}}
        </th>
        <th>
          {{item.migrationType.type}}
        </th>
        <th>
          {{item.itemsCount}}
        </th>
      </tr>
    </table>
  </div>
</template>

<script>
import UserService from '@/service/user.service';

export default {
  name: "History",
  beforeMount() {
    UserService.getMigrationsByUsername(this.$store.state.auth.user.username).then((response) => {
      this.history = response.data;
    })
  },
  data() {
    return {
      history: []
    }
  }
}
</script>

<style scoped>
.history {
  margin-bottom: 101px;
}
table {
  width: 100%;
  margin-top: 51px;
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 35px;

  color: #000000;
}
th {
  padding-right: 41px;
}
</style>