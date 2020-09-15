package com.yan.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * service - 用户详细（实现的spring security的接口）
 * @Component spring配置，把该class设置为spring的组件（不是bean，所以不会放到bean容器）
 * @author master-yan
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 根据用户名获取用户信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 加密密码
		String password = passwordEncoder().encode("123456");
		
		// 创建用户详情
		User userDetails = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		
		return userDetails;
	}

}