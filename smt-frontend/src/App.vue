<template>
  <div id="app">
    <Header></Header>
    <div class="container">
      <router-view />
    </div>
    <Footer></Footer>
  </div>
</template>


<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: 'App',
  components: {Footer, Header}
}
</script>

<style>
@font-face {
  font-family: "Bungee Hairline";
  src: local("Bungee Hairline"), url("./assets/fonts/BungeeHairline-Regular.ttf") format("truetype");
}
@font-face {
  font-family: "Karma";
  src: local("Karma"), url("./assets/fonts/Karma-Regular.ttf") format("truetype");
}
@font-face {
  font-family: "Bungee Outline";
  src: local("Bungee Outline"), url("./assets/fonts/BungeeOutline-Regular.ttf") format("truetype");
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  background: linear-gradient(180deg, #C4C4C4 9.06%, #171717 100%);
  min-height: 1080px;
  height: auto;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
}

.container {
  margin-top: 88px;
  margin-left: auto;
  margin-right: auto;
  max-width: 1170px;
  text-align: center;
}
</style>
