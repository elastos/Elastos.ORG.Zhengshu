<template>
	<div class="content">
		<div class="title">
			已有{{starbless.blessing_count}}位粉丝为<span>{{detailsList.name}}</span>送上祝福
		</div>
		<div class="blessing_card">
			<img :src="detailsList.cardUrl" />
		</div>
		<div class="blessings">
			<div class="blessings_top">
				<span class="user">
					<dl>
						<dt><img :src="userImg"/></dt>
						<dd>{{nickname}}</dd>
					</dl>
				</span> TO
				<span class="star">
					<dl>
						<dt><img :src="detailsList.headerUrl"/></dt>
						<dd>{{detailsList.name}}</dd>
					</dl>
				</span>
			</div>
			<keep-alive>
				<div class="choice_blessings">
					<div class="choice_title">
						<img src="../assets/images/tip_left.png" />
						<span>
						选择爱的表白
					</span>
						<img src="../assets/images/tip_right.png" />
						<ul class="choose_btn">
							<li v-for="(item,index) in chooseList" @click.stop="chooseFn(item,index)">
								<!--	<span v-bind:class="active?'selection':'selection_none'"></span>{{item.text}}-->
								<span v-bind:class="{checked:index==current}"></span>{{item.content}}
							</li>
						</ul>
					</div>

				</div>
			</keep-alive>

		</div>
		<div class="bottom_btn" @click.stop="rankingList">
			<button class="ranking_list_btn">
			雕刻祝福
			</button>
		</div>
		<div class="address">
			<img src="../assets/images/logo.png" alt="" />
		</div>
	</div>
</template>
<script>
	import service from './service';
	import axios from 'axios'
	import datas from '../data/data';
	import api from "../api/";
	import store from '../store'
	export default {
		name: 'details',
		data() {
			return {
				num: 1001,
				name: "杨幂",
				active: false,
				current: 0,
				chooseList: '',
				detailsList: '',
				ids: '',
				query: {
					avatar: 'https%3A%2F%2Fwx.qlogo.cn%2Fmmopen%2Fvi_32%2FQ0j4TwGTfTIdEe3fch4obFGXAZ4WiaSibobUVwZUFx7Op6AL4hbdxtaLO6QG3hBxoIPusaTsaGysZpQdAR5M19lg%2F132',
					nickname: 'iqiyiTest'
				},
				userImg: '',
				starbless: '',
				bless_list: '遇到悬崖就飞，喜欢什么就追。',
				openid: '111',
				nickname: '',
				encodenickname:''
			}
		},
		computed: {
			msg() {
				return store.state.testMsg;
			}
		},
		methods: {

			chooseFn(item, index) {
				this.current = index;
				this.bless_list = item.content;
				this.changeText(item.content);
			},
			rankingList() {
				let data = {};
				//let openid = this.query.openId;
				var datas = new Object();
				datas.openId = this.query.openid;
				datas.nickName = this.encodenickname;
				datas.starId = this.ids;
				datas.starName = this.detailsList.name;
				datas.emoticonId = "0";
				datas.blessingContent = this.bless_list;
				var jon = JSON.stringify(datas);
				//var url = 'http://192.168.1.124:9015/api/blessing_save';
				let url = api + '/api/blessing_save';
				//console.log(urls)
				axios.post(
					url,
					datas, {
						headers: {
							'Content-Type': 'application/json;charse=UTF-8'
						}
					}
				).then(result => {
					let datas = result.data.data;
					this.clickHandler(datas);
					//					service.Bus.$emit('datasfn', datas);
					//					service.Bus.$emit('blessList', this.bless_list);
					if(result.data.code == 0) {
						console.log(this.$route.query.nickname)
						this.$router.push({
							name: "share",
							query: {
								id: this.ids,
								nickname: this.$route.query.nickname,
								txt: store.state.txt,
								hash: datas.hash,
								blessingcount: datas.blessingCount

							}
						})
					}else if(result.data.code == 1002){
						this.opens();
					} else {
						this.open();
					}
				}).catch(function(error) {
					console.log(error);
				});

			},
			open() {
				this.$message('提交失败');
			},
			opens() {
				this.$message('每天最多只能送10次祝福哦！');
			},
			changeText(data) {
				store.commit('changeChildText', data);
			},
			clickHandler(data) {
				store.commit('changeTestMsg', data);
			},
		},
		mounted() {
		},
		store,
		created() {
			this.nickname = decodeURIComponent(this.$route.query.nickname);
			this.userImg = decodeURIComponent(this.$route.query.avatar);
			this.encodenickname = encodeURI(this.nickname);
			if(this.chooseList == '') {
				let data = {}
				var url = api + '/api/blessing_content_info';
				axios.post(
					url,
					data, {
						headers: {
							'Content-Type': 'application/json;charse=UTF-8'
						}
					}
				).then(result => {
					that.chooseList = result.data.data;
				})
			}
			var that = this;
			this.query = this.$route.query;
			let id = this.$route.query.id;
			this.ids = id;
			var student = new Object();
			student.starId = this.ids;
			var jon = JSON.stringify(student);
			var url = api + '/api/star_bless_info';
			axios.post(
				url,
				jon, {
					headers: {
						'Content-Type': 'application/json;charse=UTF-8'
					}
				}
			).then(result => {
				that.starbless = result.data.data;
			})

			datas.datas.list.forEach((item) => {
				if(item.id == id) {
					this.detailsList = item
				}
			})

		}

	}
</script>

<style scoped="scoped" lang="scss">
	.content {
		width: 100%;
		min-height: 100%;
		background: url(../assets/images/bg.png) no-repeat;
		background-size: 100% 100%;
		padding-top: 2.5rem;
		box-sizing: border-box;
		overflow: hidden;
		overflow-y: scroll;
		.title {
			width: 100%;
			text-align: center;
			color: #fff;
			font-size: 1rem;
			font-weight: 400;
			padding: 0 0 .3rem 0;
			span {
				font-size: 1.5rem;
				color: rgb(229, 139, 240);
				padding: 0 .3rem;
			}
		}
		.blessing_card {
			width: 92%;
			margin: 0 auto;
			/*border-radius: 1rem;
			
			-webkit-box-shadow: #5056f0 0px 0px 50px inset;
			box-shadow: #5056f0 0px 0px 50px inset;*/
			/* position: relative; */
			/* top: 15rem; */
			/* left: 50%; */
			/* margin-left: -46%; */
			.style_btn {
				display: flex;
				span {
					flex: 1;
					text-align: center;
					img {
						width: 26%;
					}
				}
			}
			img {
				width: 100%;
			}
			.style_card {
				img {
					width: 9%;
				}
			}
			.btn {
				/*width: 2.5rem;
				height: 3rem;
				display: inline-block;*/
			}
			.btn_left {
				/*background: url(../assets/images/icon_left.png) no-repeat;
				background-size: 100% 100%;*/
			}
			.btn_right {
				/*background: url(../assets/images/icon_right.png) no-repeat;
				background-size: 100% 100%;*/
			}
		}
		.blessings {
			width: 92%;
			height: 13rem;
			margin: 0 auto;
			border-radius: 1rem;
			-webkit-box-shadow: rgb(80, 86, 240) 0px 0px 50px inset;
			box-shadow: rgb(80, 86, 240) 0px 0px 50px inset;
			position: relative;
			top: .5rem;
			/* position: relative; */
			/* top: 15rem; */
			/* left: 50%; */
			/* margin-left: -46%; */
			.blessings_top {
				width: 80%;
				display: flex;
				line-height: 3rem;
				color: rgb(190, 161, 255);
				/*padding:0 2rem;*/
				box-sizing: border-box;
				border-bottom: 1px dashed #6341B7;
				margin: 0 auto;
				.user {
					dl {
						margin: 0;
						dt {
							img {
								border-radius: 50%;
								border: 2px solid #533ABB;
								width: 50%;
							}
						}
						dd {
							width: 50%;
							overflow: hidden;
							text-overflow: ellipsis;
							white-space: nowrap;
							margin: 0;
						}
					}
				}
				span {
					flex: 1;
					text-align: center;
					padding-top: .5rem;
					box-sizing: border-box;
					width: 50%;
					dl {
						margin: 0;
						line-height: 2rem;
						padding-left: 1rem;
						box-sizing: border-box;
						dt {
							width: 33%;
							float: left;
							padding-top: .3rem;
							img {
								width: 70%;
							}
						}
						dd {
							position: relative;
							left: -.1rem;
						}
					}
				}
				.star {
					dt {
						float: left;
						img {}
					}
					dd {
						overflow: hidden;
						text-overflow: ellipsis;
						white-space: nowrap;
					}
				}
			}
			.choice_blessings {
				width: 100%;
				height: 6rem;
				.choice_title {
					width: 100%;
					text-align: center;
					padding: .5rem 0;
					height: 100%;
					span {
						font-size: 1.3rem;
						color: #fff;
						font-weight: 300;
						padding: 0 .5rem;
					}
					img {
						width: 11%;
					}
					.choose_btn {
						height: 100%;
						margin: 0;
						list-style: none;
						height: 100%;
						overflow: hidden;
						overflow-y: scroll;
						padding: .6rem 0 0 .5rem;
						li {
							text-align: left;
							display: flex;
							align-items: center;
							color: #fff;
							font-weight: 300;
							font-size: 15px;
							line-height: 1.8rem;
							span {
								width: .3rem;
								height: 1.2rem;
								margin: 0 .5rem;
								background: url(../assets/images/icon_weixuan.png) no-repeat;
								background-size: 100% 100%;
								display: inline-block;
							}
						}
					}
				}
			}
		}
		.checked {
			background: url(../assets/images/icon_xuanzhong.png) no-repeat !important;
			background-size: 100% 100% !important;
		}
		.selection {
			background: url(../assets/images/icon_xuanzhong.png) no-repeat;
			background-size: 100% 100%;
		}
		.selection_none {
			background: url(../assets/images/icon_weixuan.png) no-repeat;
			background-size: 100% 100%;
			display: inline-block;
		}
		.bottom_btn {
			padding: .5rem 0;
			text-align: center;
		}
		.ranking_list_btn {
			background-image: linear-gradient(106deg, #FFDE7E, #FFF7B9);
			width: 13rem;
			height: 2.5rem;
			
			top: 5rem;
			/* bottom: 0; */
			margin: 0 auto;
			border-radius: 2rem;
			color: #32287E;
			text-align: center;
			line-height: 2.5rem;
			font-weight: 500;
			font-size: 18px;
			border: none;
			position: relative;
			top: .5rem;
		}
		.address {
			/*position: absolute;
			bottom: 0;
			display: inline-block;*/
			width: 100%;
			text-align: center;
			padding: .5rem 0;
			padding-top: 3rem;
			box-sizing: border-box;
		}
	}
</style>