import axios from 'axios'
import Qs from 'qs'
//import {
//	Message,
//	MessageBox,
//	Notification
//} from 'element-ui'
//import store from '@/store'
//import {
//	getToken
//} from '@/utils/auth'

const service = axios.create({
	//baseURL: process.env.BASE_API,
	//timeout: 1,
	transformRequest: [function (data) {
        return Qs.stringify(data)
    }]
})

//service.interceptors.request.use(config => {
//	if (getToken()) {
//		config.headers['token'] = getToken();
//	}
//	return config
//}, error => {
//	console.log(error)
//	Promise.reject(error)
//})

//service.interceptors.response.use(
//	response => {
//		if (response.config.url.includes('static/map')) {
//			return response.data;
//		}
//		if(response.headers.token == 'sessionTimeout'){
//			MessageBox.confirm('登陆超时，可以取消继续留在该页面，或者重新登录',  '确定登出', {
//				confirmButtonText: '重新登录',
//				cancelButtonText: '取消',
//				type: 'warning'
//			}).then(() => {
//				store.dispatch('FedLogOut').then(() => {
//					location.reload();
//				});
//			});
//			return Promise.reject();
//		}
//		const res = response.data;
//		if (res.errCode === 200 || res.errCode === '0') {
//			if(res.data != null){
//				return res.data;
//			}
//			
//		} else if (res.errCode === 20003) {
//			MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', res.errMsg || '确定登出', {
//				confirmButtonText: '重新登录',
//				cancelButtonText: '取消',
//				type: 'warning'
//			}).then(() => {
//				store.dispatch('FedLogOut').then(() => {
//					location.reload();
//				});
//			});
//			return Promise.reject('error');
//		} else {
//			Message({
//				type: 'error',
//				message: res.errMsg || '未知异常'
//			});
//			return Promise.reject('error');
//		}
//		
//	}, error => {
//		Message({
//			message: error.message,
//			type: 'error'
//		})
//		return Promise.reject(error)
//	})

export default service
