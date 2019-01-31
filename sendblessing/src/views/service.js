import api from "../api/";
import Vue from 'vue';
var Bus = new Vue();
let getDatas = () => api.post('/api/blessing_content_info', {
	
});
let blessingDatas = () => api.post('http://192.168.1.124:9015/api/blessing_rank', {
	
});
let starDatas = () => api.post('http://192.168.1.124:9015/api/star_bless_info', {
	
});
export default{
	Bus,
	getDatas,
	blessingDatas,
	starDatas
}
