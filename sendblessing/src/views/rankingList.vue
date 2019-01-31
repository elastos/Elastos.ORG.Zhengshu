<template>
	<div class="ranking">
		<router-link to="/" style="text-decoration:none;">
			<div class="share_btn">
				<i class="iconfont icon-zuo"></i>
				<span>返回</span>
			</div>
		</router-link>
		<div class="ranking_content">
			<div class="content_list" v-for="(item,index) in datas">
				<dl>
					<dt>
						<!--<img :src="item.imgurl"/>-->
						
						<img :src="getimg(item.starName)"/>
					</dt>
					<dd>{{item.starName}}</dd>
				</dl>
				<div class="content_num">
					<span>累计祝福</span>
					<span class="style">{{item.blessingCount}}</span>
				</div>

			</div>
			<!--<div class="content_list">
				<dl>
					<dt>
						<img src="../../static/images/touxiang_big/吴亦凡.png"/>
					</dt>
					<dd>张宏亮</dd>
				</dl>
				<div class="content_num">
					<span>累计祝福</span>
					<span class="style">1000</span>
				</div>

			</div>-->

		</div>
	</div>
</template>

<script>
	import axios from 'axios';
	import api from "../api/";
	export default {
		name: "",
		data() {
			return {
				datas: ""
			}
		},
		methods: {
			getimg(item) {
				return require("../../static/images/touxiang_big/" + item + ".png")
			}
		},
		created() {
			var data = {};
			//var datas = ;
			console.log()
			//console.log(data)
			var url = api+'/api/blessing_rank';
			axios.post(
				url,
				data, {
					headers: {
						'Content-Type': 'application/json;charse=UTF-8'
					}
				}
			).then(result => {
				this.datas = result.data.data.StarRanking;
				console.log(this.datas)
			})

			
		}
	}
</script>

<style scoped="scoped" lang="scss">
	@import "../styles/iconfont.css";
	.ranking {
		width: 100%;
		min-height: 100%;
		background: url(../assets/images/bg.png) no-repeat;
		background-size: 100% 100%;
		.share_btn {
			color: #fff;
			font-size: 1.3rem;
			padding: 1rem .5rem;
			font-weight: 300;
			box-sizing: border-box;
			span {
				position: relative;
				top: -.3rem;
			}
		}
		.icon-zuo {
			font-size: 2rem;
			color: #fff;
		}
		.ranking_content {
			width: 92%;
			border-radius: 1rem;
			margin: 0 auto;
			-webkit-box-shadow: #5056f0 0px 0px 50px inset;
			box-shadow: #5056f0 0px 0px 50px inset;
			/* position: relative; */
			/* top: 15rem; */
			/* left: 50%; */
			/* margin-left: -46%; */
			display: -webkit-box;
			display: -ms-flexbox;
			display: flex;
			-ms-flex-wrap: wrap;
			flex-wrap: wrap;
			padding: 1.3rem .8rem;
			box-sizing: border-box;
			overflow: hidden;
			overflow-y: scroll;
			background: #3a268d;
			.content_list {
				width: 100%;
				display: flex;
				justify-content: space-between;
				border-bottom: 1px dashed #5835B1;
				padding: .5rem 1rem;
				dl {
					margin: 0;
					dt {
						width: 30%;
						float: left;
						img {
							width: 100%;
						}
					}
					dd {
						color: #fff;
						line-height: 3rem;
						padding-left: 1.5rem;
						box-sizing: border-box;
						font-weight: 300;
					}
				}
			}
			.content_num {
				span {
					color: #fff;
					display: block;
					text-align: center;
					padding-top: .2rem;
					box-sizing: border-box;
					font-weight: 300;
				}
				.style {
					color: rgb(229, 139, 240);
				}
			}
		}
	}
</style>