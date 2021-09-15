import Vue from 'vue'
import Vuex from 'vuex'
import settings from "./modules/settings";
import user from"./modules/user"

Vue.use(Vuex)


const store = new Vuex.Store({
  modules:{
    settings,
    user
  }
})

export default store
