package com.yan.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 缓存配置
 * @author master-yan
 *
 */
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	/**
	 * 缓存管理器
	 */
	@Bean
	@Override
	public CacheManager cacheManager() {
		// redis 字符串序列化
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();

		// redis json序列化
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		
		// 解决查询缓存转换异常
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		
		// 创建redis缓存配置（配置序列化，解决乱码问题）
		// entryTtl 设置缓存有效时间
		// serializeKeysWith 序列化key的值
		// serializeValuesWith 序列化value的值
		// disableCachingNullValues 禁止缓存null值
		RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//				.entryTtl(Duration.ofSeconds(120))
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.disableCachingNullValues();

		// 创建redis缓存管理器
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultCacheConfiguration).build();
	}
	
	/**
	 * key生成策略
	 */
	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				//格式化缓存key字符串
                StringBuffer sb = new StringBuffer();
                
                //追加类名
                sb.append(target.getClass().getName());
                
                //追加方法名
                sb.append(method.getName());
                
                //遍历参数并且追加
                for (Object param : params) {
                    sb.append(param.toString());
                }
                
                return sb.toString();
			}
		};
	}
	
}