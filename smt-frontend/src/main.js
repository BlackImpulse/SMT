import Vue from 'vue'
import Vuex from 'vuex';
import router from './router'
import store from './store';
import App from "@/App";

Vue.config.productionTip = false
Vue.use(Vuex);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
