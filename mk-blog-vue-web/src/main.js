import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/iview.js'
import api from "./request"; // 导入api接口
import mavonEditor from "mavon-editor"
import live2d from 'live2d-vue'
import VueAxiosPlugin from 'vue-axios-plugin'
import 'mavon-editor/dist/css/index.css'
import APlayer from '@moefe/vue-aplayer';

Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(live2d)
Vue.prototype.$api = api; // 将api挂载到vue的原型上
Vue.use(VueAxiosPlugin, {
    // 请求拦截处理
    reqHandleFunc: config => config,
    reqErrorFunc: error => Promise.reject(error),
    // 响应拦截处理
    resHandleFunc: response => response,
    resErrorFunc: error => Promise.reject(error)
})
Vue.use(APlayer, {
    defaultCover: 'https://github.com/u3u.png',
    productionTip: true,
});
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
