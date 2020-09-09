package com.yan.template;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * spring封装的redis key string结构api
 * @author master-yan
 *
 */
public class RedisKeyStringTemplateDemo {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Test
	public void set(String key, String value) {
		// 监听key
		stringRedisTemplate.watch(key);

		// 开启事务
		stringRedisTemplate.multi();

		try {
			// 赋值(key, value)
			stringRedisTemplate.opsForValue().set(key, value);
			
			if (value.equals("100")) {
				throw new Exception("造个异常");
			}
			
			// 执行事务
			stringRedisTemplate.exec();
		} catch (Exception e) {
			// 取消事务
			stringRedisTemplate.discard();
			e.printStackTrace();
		}
	}

	@Test
	public void mset() {
		// 准备数据
		Map<String, String> map = new HashMap<>();
		map.put("redisTest1", "随随便便给的值1");
		map.put("redisTest2", "随随便便给的值2");
		map.put("redisTest3", "随随便便给的值3");
		
		// 批量赋值(key, value, ...)
		stringRedisTemplate.opsForValue().multiSet(map);
	}

	@Test
	public String get() {
		// 取值(key)
		return stringRedisTemplate.opsForValue().get("redisTest1");
	}

	@Test
	public List<String> mget() {
		// 准备数据
		Set<String> keys = new HashSet<>();
		keys.add("redisTest1");
		keys.add("redisTest2");
		keys.add("redisTest3");
		
		// 取值(key...)
		return stringRedisTemplate.opsForValue().multiGet(keys);
	}

	@Test
	public void incr() {
		// 自增1(值为数字的情况)
		stringRedisTemplate.opsForValue().increment("redisTest");
	}

	@Test
	public void incrBy() {
		// 自增指定的值(key, increment)
		stringRedisTemplate.opsForValue().increment("redisTest", 20);
	}

	@Test
	public void decr() {
		// 自减1(值为数字的情况)
		stringRedisTemplate.opsForValue().decrement("redisTest");
	}

	@Test
	public void decrBy() {
		// 自减指定的值(key, increment)
		stringRedisTemplate.opsForValue().decrement("redisTest", 10);
	}

	@Test
	public void setex() {
		// 设置值的同时,设置生命周期[时间的单位是秒, 有效期一分钟就写60]
		// key, seconds, value
		stringRedisTemplate.opsForValue().set("redisTest", "50", 10);
		stringRedisTemplate.expire("redisTest", 10, TimeUnit.SECONDS);
	}

	@Test
	public void setnx() {
		// 赋值,key不存在时起效(key, value)
		stringRedisTemplate.opsForValue().getAndSet("redisTest", "90");
	}

	@Test
	public void apped() {
		// 在key对应的值后追加内容(key, value)
		stringRedisTemplate.opsForValue().append("redisTest", "90");
	}

	@Test
	public long strlen() {
		// 在key对应的值后追加内容(key, value)
		return stringRedisTemplate.opsForValue().size("redisTest");
	}

}
