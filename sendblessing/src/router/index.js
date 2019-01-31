import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)

Vue.use(Router)

export default new Router({
	mode: 'history',
  routes: [
    {
		path: '/index',
		component: _import('index'),
		hidden: true
	},
	{
		path: '/details',
		name:'details',
		component: _import('details'),
		hidden: true
	},
	{
		path: '/share',
		name:'share',
		component: _import('share'),
		hidden: true
	},
	{
		path: '/ranking',
		name:'ranking',
		component: _import('rankingList'),
		hidden: true
	}
  ]
})
