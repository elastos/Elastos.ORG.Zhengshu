<template>
	<keep-alive>
		<div class="content">
			<div class="top">
				<div class="header">
					<img src="../assets/images/indexbg_01.png" />
				</div>
				<div class="title-images">
					<img src="../assets/images/biaoti.png" />
				</div>

				<div class="ranking_list">
					<img src="../assets/images/chakan_bg.png" />
					<div class="ranking_list_btn" @click="rankingList">
						<router-link to="ranking" style="text-decoration:none;">
							查看祝福排行
						</router-link>
					</div>
				</div>
				<div class="title">
					<p># 选择你喜爱的明星，生成专属祝福卡 #<br />在区块链上刻上永恒的烙印</p>
				</div>
			</div>
			<div class="img">
				<div class="head_portrait">
					<dl v-for="(item,index) in datas" @click="detailsFn(item)">
						<dt><img :src="item.url"/></dt>
						<dd>{{item.name}}</dd>
					</dl>

				</div>
			</div>

			<div class="footer">
				<img src="../assets/images/indexbg_02.png" />
			</div>
		</div>
	</keep-alive>
</template>

<script>
	import axios from 'axios';
	import datas from '../data/data';
	export default {
		name: "",
		data() {
			return {
				datas: ''
			}
		},
		methods: {
			rankingList() {
				console.log(1111)
			},
			detailsFn(item) {
				//				let data = {"starId":10}
				//				var url = 'http://192.168.1.116:9015/api/star_bless_info';
				//				axios.post(
				//					url,
				//					data, {
				//						headers: {
				//							'Content-Type': 'application/json;charse=UTF-8'
				//						}
				//					}
				//				).then(result => {
				//					this.datas = result.data.data;
				//					console.log(this.datas)
				//				})
				this.$router.push({
					name: 'details',
					query: {
						id: item.id,
					}
				})
			}
		},
		mounted() {

		},
		created() {

			this.datas = datas.datas.list;

		}
	}
</script>

<style scoped="scoped" lang="scss">
	.content {
		width: 100%;
		overflow: hidden;
		overflow-y: scroll;
		background-image: linear-gradient(#2D2974, #5F1FCB);
		/*background: url(../assets/images/indexs-bg.png) no-repeat;
	background-size: 100% 100%;*/
		position: relative;
		.img {
			padding-bottom: 6rem;
			box-sizing: border-box;
		}
		.top {
			width: 100%;
			height: 0rem;
			position: relative;
		}
		.header {
			width: 100%;
			position: absolute;
			top: 0;
			img {
				width: 100%;
				height: 100%;
			}
		}
		.footer {
			width: 100%;
			position: absolute;
			bottom: 0;
			img {
				width: 100%;
				height: 100%;
			}
		}
		.title-images {
			width: 84%;
			margin: 0 auto;
			position: absolute;
			top: 2.4rem;
			left: 50%;
			margin-left: -42%;
			img {
				width: 100%;
				height: 100%;
			}
		}
		.title {
			/* margin: 0 auto; */
			position: absolute;
			top: 6.5rem;
			color: #fff;
			width: 85%;
			/* text-align: center; */
			left: 50%;
			margin-left: -42.5%;
			p {
				font-size: 1rem;
				text-align: center;
			}
		}
		.ranking_list {
			width: 84%;
			margin: 0 auto;
			position: absolute;
			left: 50%;
			margin-left: -38%;
			top: 7.5rem;
			.ranking_list_btn {
				background-image: linear-gradient(106deg, #FFDE7E, #FFF7B9);
				width: 13rem;
				height: 2.5rem;
				position: absolute;
				left: 50%;
				margin-left: -7.5rem;
				/* right: 0; */
				top: 5rem;
				/* bottom: 0; */
				/* margin: 0 auto; */
				border-radius: 2rem;
				color: #32287E;
				text-align: center;
				line-height: 2.5rem;
				font-weight: 500;
				font-size: 18px;
			}
			img {
				width: 100%;
				height: 100%;
			}
		}
		.head_portrait {
			width: 92%;
			border-radius: 1rem;
			margin: 0 auto;
			-webkit-box-shadow: #5056f0 0px 0px 50px inset;
			box-shadow: #5056f0 0px 0px 50px inset;
			/* position: relative; */
			/* top: 15rem; */
			/* left: 50%; */
			/* margin-left: -46%; */
			margin-top: 16.5rem;
			display: flex;
			flex-wrap: wrap;
			padding: 1.3rem .5rem;
			dl {
				width: 33.3%;
				padding-left: .5rem;
				display: inline-block;
				margin: 0;
				padding: .3rem;
				-webkit-box-sizing: border-box;
				dt {
					width: 100%;
					text-align: center;
					img {
						width: 90%;
					}
				}
				dd {
					color: #fff;
				}
			}
		}
	}
</style>