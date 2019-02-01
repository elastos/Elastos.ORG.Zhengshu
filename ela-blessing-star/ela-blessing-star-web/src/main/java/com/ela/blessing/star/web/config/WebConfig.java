package com.ela.blessing.star.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 作者 GuoZg E-mail: guozhenguo@huiyihuiying.com
 * @description 解决跨域问题
 * @date 创建时间：2017年2月20日 上午11:43:38
 * @version 1.0
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
//		.allowedHeaders("Access-Control-Allow-Origin")
//		.allowedHeaders("application/x-www-form-urlencoded")
		.allowCredentials(true)//防止session丢失问题
		.allowedMethods("GET", "POST", "DELETE", "PUT")
		.maxAge(3600);
	}

}
