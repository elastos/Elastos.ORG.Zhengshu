import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex)

const state = {
	datas:'',
	txt:''
}

const mutations = {
    changeTestMsg(state, str){
        state.datas = str;
    },
    changeChildText(state, str){
        state.txt = str;
    }

}

const store = new Vuex.Store({
    state: state,
    mutations: mutations
})

export default store;