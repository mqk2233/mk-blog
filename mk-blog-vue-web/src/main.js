import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/iview.js'
import mavonEditor from "mavon-editor"
import live2d from 'live2d-vue'
import VueAxiosPlugin from 'vue-axios-plugin'

Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(live2d)

Vue.use(VueAxiosPlugin, {
  // 请求拦截处理
  reqHandleFunc: config => config,
  reqErrorFunc: error => Promise.reject(error),
  // 响应拦截处理
  resHandleFunc: response => response,
  resErrorFunc: error => Promise.reject(error)
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
