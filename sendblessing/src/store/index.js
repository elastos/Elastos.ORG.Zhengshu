import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex)

const state = {
	datas:'',
	txt:'遇到悬崖就飞，喜欢什么就追。'
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