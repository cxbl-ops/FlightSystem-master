import Vue from 'vue'
import App from './App.vue'
import ElementUI, {Message} from 'element-ui'
import VueRouter from 'vue-router'
import routes from './router'
import 'element-ui/lib/theme-chalk/index.css';
import Vuex from 'vuex'

Vue.config.productionTip = false
Vue.use(ElementUI)
// Vue.use(axios)
Vue.use(Vuex)
const router = new VueRouter({
  routes
})

export function errorMessage(code,message){
  Message({
    type:'error',
    message: message
  })
}

export function successMessage(code,message){
  Message({
    type:'success',
    message: message
  })
}


Vue.use(VueRouter)
new Vue({
  // store,
  router,
  render: h => h(App)
}).$mount('#app')
