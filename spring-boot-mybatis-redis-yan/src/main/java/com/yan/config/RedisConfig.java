package com.yan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
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
public class RedisConfig extends CachingConfigurerSupport {
	
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

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return super.keyGenerator();
	}
	
	@Bean
	@Override
	public CacheManager cacheManager() {
//		RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
//		boolean allowInFlightCacheCreation
		搞到这，明天要研究下spring boot自带的缓存
		return new RedisCacheManager(redisTemplate(), );
		return null;
	}

}