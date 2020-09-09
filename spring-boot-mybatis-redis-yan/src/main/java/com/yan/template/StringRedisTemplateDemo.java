package com.yan.template;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * spring封装的redis key hash结构api
 * @author master-yan
 *
 */
public class StringRedisTemplateDemo {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 赋值
	 */
	@Test
	public void hset() {
		// 赋值(key, field, value)
		stringRedisTemplate.opsForHash().put("hash", "field", "value");
	}

	/**
	 * 批量赋值
	 */
	@Test
	public void hmset() {
		// 创建hash(field, value)
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("name", "fenrir");
		hash.put("age", "18");
		hash.put("power", "100000");
		
		// 赋值(key, field, value)
		stringRedisTemplate.opsForHash().putAll("hash", hash);
	}

	/**
	 * 取值
	 */
	@Test
	public Object hget() {
		// 赋值(key, field, value)
		return stringRedisTemplate.opsForHash().get("hash", "field");
	}

	/**
	 * 批量取值
	 */
	@Test
	public List<Object> hmget() {
		// 准备数据
		Collection<Object> hashKeys = new HashSet<>();
		hashKeys.add("field");
		hashKeys.add("name");
		hashKeys.add("age");
		hashKeys.add("power");
		
		// 赋值(key, field, value)
		return stringRedisTemplate.opsForHash().multiGet("hash", hashKeys);
	}

	/**
	 * 赋值,field不存在时起效
	 */
	@Test
	public void hincrby() {
		// 自增指定的值(key, field, value)
		stringRedisTemplate.opsForHash().increment("hash", "age", 7);
	}

	/**
	 * 检查field是否存在
	 */
	@Test
	public boolean hexists() {
		// 检查field是否存在(key, field)
		return stringRedisTemplate.opsForHash().hasKey("hash", "speed");
	}

}