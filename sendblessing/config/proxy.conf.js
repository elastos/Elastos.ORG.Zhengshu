module.exports = {
	'/api': {
		target: 'http://test.youxue.pxjy.com',//测试
		//target:'http://app.ipolecity.com.cn',//正式
		//target:'http://122.113.5.37:8080',
		//target: "http://122.113.5.31:8033",
		changeOrigin: true,
		pathRewrite: {
			'^/api': '/api'
		}
	},
}