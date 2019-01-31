<template>
	<div class="share">
		<span class="share_btn" @click="shareFn">分享</span>
		<h1 class="share_title">粉丝祝福卡</h1>
		<div class="star_card">
			<img :src="datalist.cardUrl" />
			<div class="blessings_box">
				<span class="blessings">{{$route.query.nickname}}:</span>
				<span>{{$route.query.txt}}</span>
			</div>
		</div>
		<div class="users"><span>{{$route.query.nickname}}</span>为<span>{{datalist.name}}</span><br />送上了第 {{$route.query.blessingcount}} 份爱的表白</div>
		<div class="hash_code">
			<p>爱的表白已在<b>Elastos区块链</b>上雕刻，证书hash：</p>
			<span class="code_num">{{$route.query.hash}}</span>
		</div>
		<div class="erweima">
			<div id="qrCode" ref="qrCodeDiv"></div>
		</div>
		<div class="address">
			<img src="../assets/images/logo.png" alt="" />
		</div>
		<div class="massage">
			<span>本证书由<a href="https://play.fishchain.io/">bitgame</a>提供技术支持</span>
			<!--<br />点此查看
				<a href="https://play.fishchain.io/">bitgame更多</a>好玩内容-->
			<br />
			<a href="https://play.fishchain.io/">https://play.fishchain.io/</a>-->
		</div>

	</div>
</template>

<script>
	import QRCode from 'qrcodejs2';
	import service from './service';
	import datas from '../data/data';
	import store from '../store'
	export default {
		name: '',
		data() {
			return {
				datass: '111',
				datalist: '',
				bless_list: {
					content: '遇到悬崖就飞，喜欢什么就追。'
				},

			}
		},
		methods: {
			bindQRCode: function() {
				console.log(window.location.href)
				new QRCode(this.$refs.qrCodeDiv, {
					text: window.location.href,
					width: 150,
					height: 150,
					colorDark: "#333333", //二维码颜色
					colorLight: "#ffffff", //二维码背景色
					correctLevel: QRCode.CorrectLevel.L //容错率，L/M/H
				})
			},
			open() {
				this.$message('请您截图分享');
			},
			shareFn() {
				console.log(1111)
				//this.WXConfig.wxShowMenu();
				this.open();
			}
		},
		mounted() {
			console.log()
			this.$nextTick(function() {
				//alert(11)
				this.bindQRCode();
			})
			var that = this;
			service.Bus.$on('datasfn', function(data) {
				console.log(data)
				that.datass = data;
				console.log(that.datass)
			});
			//			service.Bus.$on('blessList', function(data) {
			//				that.bless_list = data;
			//				console.log(that.bless_list)
			//			});
		},
		created() {
			console.log(this.$route.query)
			//			if(this.$route.query.data !=''){
			//				console.log(1111)
			//				this.datass=this.$route.query.data
			//			}else{
			//				this.datass = store.state.datas;
			//			}
			this.datass = store.state.datas;
			this.bless_list = store.state.txt;
			let that = this;
			console.log(that.bless_list)
			service.Bus.$on('blessList', function(data) {
				if(data != '') {
					that.bless_list = data;
				}

			});
			//			service.Bus.$on('datasfn', function(data) {
			//				console.log(data)
			//				that.datass = data;
			//				console.log(that.datass)
			//			});
			let startId = this.$route.query.id;
			datas.datas.list.forEach((item) => {
				if(item.id == startId) {
					this.datalist = item;
					console.log(item)
				}
			})
		},
		watch: {
			datas(curVal, oldVal) {　　　　　　　　　　
				console.log(curVal, oldVal);　　　　　　　　
			},
		}
	}
</script>

<style scoped="scoped" lang="scss">
	#qrCode {
		border: 4px solid #fff;
		display: inline-block;
		img {
			margin: 0 auto;
		}
	}
	
	.share {
		word-wrap: break-word;
		width: 100%;
		min-height: 100%;
		/*height: auto;*/
		background: url(../assets/images/bg.png) no-repeat;
		background-size: 100% 100%;
		padding-top: 1rem;
		box-sizing: border-box;
		overflow: hidden;
		overflow-y: scroll;
		position: relative;
		.share_btn {
			color: #fff;
			font-size: 1rem;
			padding: .3rem 1rem 0 1rem;
			font-weight: 300;
			/* float: right; */
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
			/* position: relative; */
			/* top: .3rem; */
			width: 100%;
			display: inline-block;
			text-align: right;
		}
		.share_title {
			text-align: center;
			font-weight: 300;
			color: #fff;
			margin: 0;
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
			font-size: 1.5rem;
			position: absolute;
			left: 50%;
			top: 1rem;
			margin-left: -4rem;
			display: inline-block;
		}
		.star_card {
			width: 92%;
			margin: 0 auto;
			position: relative;
			padding-top: 0.8rem;
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
			.blessings_box {
				position: absolute;
				bottom: 2rem;
				left: 1.5rem;
				padding: .5rem;
				box-sizing: border-box;
				background: rgba(114, 79, 124, .5);
				color: #fff;
				font-size: .8rem;
				font-weight: 300;
				border-radius: .3rem;
				.blessings {
					color: #E48BEF;
				}
			}
			img {
				width: 100%;
			}
		}
		.users {
			width: 100%;
			text-align: center;
			color: #fff;
			font-weight: 100;
			span {
				font-size: 1.2rem;
				color: rgb(229, 139, 240);
				padding: 0 .3rem;
				box-sizing: border-box;
			}
		}
		.hash_code {
			width: 100%;
			font-size: 1rem;
			font-weight: 300;
			color: #fff;
			padding: .8rem 1rem;
			display: flex;
			flex-wrap: wrap;
			box-sizing: border-box;
			p {
				margin: 0;
				padding: .2rem 0;
				b {
					color: cornflowerblue;
				}
			}
			.code_num {
				width: 100%;
				color: rgb(229, 139, 240);
			}
		}
		.erweima {
			width: 100%;
			text-align: center;
			padding-top: 1.5rem;
			box-sizing: border-box;
			img {
				width: 30%;
			}
		}
		.address {
			width: 100%;
			padding: .8rem;
			box-sizing: border-box;
			text-align: center;
			img {
				width: 30%;
				padding-bottom: 1rem;
			}
		}
		.massage {
			color: #44249D;
			font-weight: 100;
			padding: .8rem 0;
			/*position: absolute;
			bottom: .8rem;*/
			/*left: 50%;
			margin-left: -6.5rem;*/
			box-sizing: border-box;
			width: 100%;
			text-align: center;
			span {
				color: #fff;
				font-weight: 400;
				width: 100%;
				display: inline-block;
			}
			a {
				color: #42249E;
				width: 100%;
				text-align: center;
				display: inline-block;
				font-weight: 400;
				padding-top: .5rem;
			}
			/*a {
				color: rgb(229, 139, 240);
			}*/
		}
	}
</style>
