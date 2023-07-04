import {createStore} from "vuex";

export default createStore({
  state: {
    userInfo: {}
  },
  getters: {
    getUserInfo(state) {
      return state.userInfo
    }
  },
  mutations: {
    setUserInfo(state, data) {
      state.userInfo = data
    }
  },
  actions: {
    actUserInfo({commit}, data) {
      commit('setUserInfo', data)
    }
  },
  modules: {},
});