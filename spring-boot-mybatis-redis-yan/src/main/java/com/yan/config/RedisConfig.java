package com.yan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * redis配置
 * @author master-yan
 *
 */
@Configuration
public class RedisConfig {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	/**
	 * redis连接配置
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public StringRedisTemplate redisTemplate() {
		// 创建连接对象
		StringRedisTemplate template = new StringRedisTemplate();
		
		// 设置连接工厂
		template.setConnectionFactory(redisConnectionFactory);
		
		// 打开事务支持
		template.setEnableTransactionSupport(true);
		
		return template;
	}

}