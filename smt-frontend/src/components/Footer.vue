<template>
  <footer>
    <ul>
      <li><div class="contact-us">CONTACT US</div> </li>
      <li>
        <div class="help">
          HELP
          <img src="../assets/pics/help-web-button.svg" alt="help_icon"/>
        </div>
      </li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
footer {
  position: absolute;
  height: 114px;
  width: 100%;
  bottom: 0;
  background: transparent;
}

footer ul {
  list-style: none;
  margin-left: auto;
  margin-right: auto;
  padding: 0;
}

footer ul li {
  display: inline-block;
}

footer ul li .contact-us {
  margin-top: 45px;
  margin-left: 1128px;
  font-family: Bungee Hairline, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 25px;
  line-height: 30px;
  text-align: center;
  width: 159px;

  color: #FFFFFF;
}

footer ul li .help {
  font-family: Bungee Hairline, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 25px;
  line-height: 30px;
  text-align: center;
  width: 112px;
  margin-left: 98px;

  color: #FFFFFF;
}

footer ul li .help img {
  height: 32px;
  width: 32px;
}
</style>