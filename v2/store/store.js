// // store.js

// import Vue from 'vue';
// import Vuex from 'vuex';
// import {uname,password} from "@/views/login.vue"
// import { from } from 'core-js/core/array';
// Vue.use(Vuex);

// const store = new Vuex.Store({
//   state: {
//     // 这里定义应用程序的状态
//     count: 0,
//     user: null,
//   },
//   mutations: {
//     // 这里定义修改状态的方法（同步操作）
//     increment(state) {
//       state.count++;
//     },
//     setUser(state, user) {
//       state.user = user;
//     },
//   },
//   actions: {
//     // 这里定义异步操作和业务逻辑
//     fetchUser(context) {
//       // 模拟异步请求
//       setTimeout(() => {
//         const user = { id: 1, name: 'John' };
//         context.commit('setUser', user);
//       }, 1000);
//     },
//   },
//   getters: {
//     // 这里定义获取状态的方法
//     isLoggedIn(state) {
//       return state.user !== null;
//     },
//   },
// });

// export default store;
