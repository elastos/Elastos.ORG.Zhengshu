import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex)

const state = {
	datas:''
}

const mutations = {
    changeTestMsg(state, str){
        state.datas = str;
    },
//  changeChildText(state, str){
//      state = str;
//  }

}

const store = new Vuex.Store({
    state: state,
    mutations: mutations
})

export default store;