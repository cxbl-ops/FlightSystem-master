// // store/index.js
// import Vue from 'vue'
// import Vuex from 'vuex'
//
// Vue.use(Vuex)
// const store = new Vuex.Store({
//     state: {
//         user: null // 假设用户信息存储在 state 中的 user
//     },
//     getters: {
//         isLoggedIn: state => !!state.user
//     },
//     mutations: {
//         setUser(state, user) {
//             state.user = user
//         },
//         clearUser(state) {
//             state.user = null
//         }
//     },
//     actions: {
//         login({ commit }, user) {
//             commit('setUser', user)
//         },
//         logout({ commit }) {
//             commit('clearUser')
//         }
//     }
// })
//
// export default store
