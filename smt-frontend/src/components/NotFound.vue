<template>
  <div class="not-found">
    <ul>
      <li><img class="four" src="../assets/pics/four.png" alt="four" /></li>
      <li><img class="cd" src="../assets/pics/cd.png" alt="cd" /></li>
      <li><img class="four" src="../assets/pics/four.png" alt="four" /></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "NotFound"
}
</script>

<style scoped>
.not-found {
  position: absolute;
  margin-left: 176px;
  margin-top: 200px;
}

.not-found ul {
  list-style: none;
}

.not-found ul li{
  display: inline-block;
}

.not-found img.four {
  width: 252px;
  height: 252px;
}

.not-found img.cd {
  width: 252px;
  height: 252px;
}
</style>