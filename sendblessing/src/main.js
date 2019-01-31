// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//import WXConfig from './assets/wx/wx'//微信分享功能
import Element from 'element-ui'
import QRCode from 'qrcodejs2';
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/base.css';

Vue.config.productionTip = false;
//Vue.prototype.WXConfig =WXConfig;
Vue.use(Element)
/* eslint-disable no-new */

	new Vue({
		el: '#app',
		router,
		components: {
			App
		},
		template: '<App/>'
	})