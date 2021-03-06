import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueRouter from 'vue-router'

import ElementUI from "element-ui";
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'

import './assets/styles/element-variables.scss'
// global css
import './assets/styles/index.scss'

Vue.use(ElementUI);

Vue.config.productionTip = false

Vue.use(VueRouter)

import './assets/icons' // icon

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
