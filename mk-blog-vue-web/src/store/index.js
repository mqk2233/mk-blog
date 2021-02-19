import Vue from "vue";
import Vuex from "vuex";
import {CURRENT_USER, LOGIN, LOGOUT} from "@/mutation/mutation-types";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: "",
    user: false
  },
  mutations: {
    [LOGIN](state, data) {
      localStorage.setItem("token", data);
      state.token = data;
    },
    [LOGOUT](state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = null;
      state.user = false;
    },
    [CURRENT_USER](state, data) {
      state.user = true;
      localStorage.user = data;
    }
  },
  actions: {}
});