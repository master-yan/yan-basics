package com.yan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.yan.interceptor.MyInterceptor;

/**
 * 拦截器配置
 * @Configuration spring boot配置把该class设置为spring的配置
 * @author master-yan
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
	
	@Autowired
	private MyInterceptor myInterceptor;

	/**
	 * 注册自定义拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor);
	}
	
}