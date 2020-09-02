package com.yan.origin;

import java.sql.Timestamp;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.util.SerializationUtils;

import com.yan.entity.LambPO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis api 原生用法，连接池用法
 * @author master-yan
 *
 */
public class RdiesPoolOrigin {

	/**
	 * 保存对象到redis
	 */
	@Test
	public void addObject() {
		// 创建连接池
		JedisPool pool = new JedisPool("127.0.0.1", 6379);
		
		// 连接库
		Jedis jedis = pool.getResource();
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 保存到redis
		jedis.set(getKey(), getValue());
		
		// 关闭连接
		jedis.close();
		
		// 关闭连接池
		pool.close();
	}
	
	/**
	 * 保存对象到redis
	 */
	@Test
	public void queryObject() {
		// 创建连接池配置
		GenericObjectPoolConfig<Jedis> config = new GenericObjectPoolConfig<Jedis>();
		// 设置最大连接数量
		config.setMaxTotal(100);
		// 设置最大空闲连接数量
		config.setMaxIdle(10);
		// 设置最小空闲连接数量
		config.setMinIdle(5);
		// 
		config.setMaxWaitMillis(3000);
		
		// 创建连接池
//		JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
		// 创建连接池并设置超时时间和密码
		JedisPool pool = new JedisPool(config, "127.0.0.1", 6379, 3000, "fenrir123456.");
		
		// 连接redis
		Jedis jedis = pool.getResource();
		
		// 转换数据
		System.out.println(SerializationUtils.deserialize(jedis.get(getKey())));
		
		// 关闭连接
		jedis.close();
		
		// 关闭连接池
		pool.close();
	}

	private byte[] getKey() {
		String key = "lamb";
		return SerializationUtils.serialize(key);
	}
	
	private byte[] getValue() {
		LambPO lamb = new LambPO(0, "喜羊羊", 1, new Timestamp(System.currentTimeMillis()));
		return SerializationUtils.serialize(lamb);
	}
	
}