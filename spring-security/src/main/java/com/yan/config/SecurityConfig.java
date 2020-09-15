package com.yan.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.yan.handler.MyAuthenticationFailureHandler;
import com.yan.handler.MyAuthenticationSuccessHandler;
import com.yan.service.impl.MyUserDetailsService;

/**
 * 安全配置
 * WebSecurityConfigurerAdapter spring security配置，配置系统的拦截，授权规则
 * @Configuration spring boot配置，把该class设置为spring的配置
 * @EnableWebSecurity spring security配置，使安全配置起作用
 * @EnableGlobalMethodSecurity spring security配置，设置全局拦截
 * @author master-yan
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Autowired
	private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	/**
	 * 数据源
	 */
	@Autowired
	private DataSource dataSource;
	
	/**
	 * 记住我功能dao
	 * @return
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 创建记住我功能表
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	/**
	 * 授权配置
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置用户详情服务
		auth.userDetailsService(myUserDetailsService);
	}
	
	/**
	 * web安全配置
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不需要验证的资源
		web.ignoring().antMatchers(
			"/taojia/login",
			"/taojia/logout",
			"/verificationCode/getSmsVerificationCode/*",
			"/test/getTest"
		);
	}

	/**
	 * http安全配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		
		// 任何http请求都需要身份认证
		http.authorizeRequests().anyRequest().authenticated();
		
		// 记住我配置
		http.rememberMe().tokenRepository(persistentTokenRepository());
		
//		http.sessionManagement().invalidSessionStrategy(invalidSessionStrategy)
		
		// session配置
		http.sessionManagement()
		// session生成策略 - STATELESS禁用session
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
		// 表单登录配置
		http.formLogin()
		// 自定义登录url
		.loginProcessingUrl("/my/login")
		// 自定义登录成功处理器
		.successHandler(myAuthenticationSuccessHandler)
		// 自定义登录失败处理器
		.failureHandler(myAuthenticationFailureHandler);
			
		// 登出配置
		http.logout()
		// 自定义登出url
		.logoutUrl("/my/logout")
		// 注销时是否清除身份验证
		.clearAuthentication(true);
					
		// 记住我配置
		http.rememberMe()
		// 设置token库
		.tokenRepository(persistentTokenRepository())
		// 设置用户管理服务
		.userDetailsService(myUserDetailsService)
		// 是否始终记住我
		.alwaysRemember(true)
		// 设置token的有效时间, 1 = 1s, 1 * 60 * 60 * 2 = 2小时
		.tokenValiditySeconds(7200);
		
		// csrf配置
		http.csrf()
		// 禁止跨域跨域
		.disable();
					
		// 授权配置
		http.authorizeRequests()
		// 任何请求
		.anyRequest()
		// 都需要验证
		.authenticated();
	}

}