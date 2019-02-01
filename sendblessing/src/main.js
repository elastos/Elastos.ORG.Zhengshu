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
//router.beforeEach((to, from, next) => {
//	
//	setTimeout(() => {
//			var _hmt = _hmt || [];
//			(function() {
//				var hm = document.createElement("script");
//				hm.src = "https://hm.baidu.com/hm.js?d2a67c3ea8b64f1b9da8676be29f98aa";
//				var s = document.getElementsByTagName("script")[0];
//				s.parentNode.insertBefore(hm, s);
//			})();
//	}, 0)
//});
router.afterEach( ( to, from, next ) => {
 setTimeout(()=>{
   var _hmt = _hmt || [];
   (function() {
    //每次执行前，先移除上次插入的代码
    document.getElementById('baidu_tj') && document.getElementById('baidu_tj').remove();
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?d2a67c3ea8b64f1b9da8676be29f98aa";
    hm.id = "baidu_tj"
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
   })();
 },0);
} );
new Vue({
	el: '#app',
	router,
	components: {
		App
	},
	template: '<App/>'
})