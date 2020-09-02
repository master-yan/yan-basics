package com.yan.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicResponseVO;

/**
 * controller - 调用spring封装的redis(key hash)
 * @author master-yan
 *
 */
@RestController
public class RedisKeyHashController {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 赋值
	 */
	@GetMapping(
		value = "/keyHash/hset", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hset() {
		// 赋值(key, field, value)
		stringRedisTemplate.opsForHash().put("hash", "field", "value");
		
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 批量赋值
	 */
	@GetMapping(
		value = "/keyHash/hmset", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hmset() {
		// 创建hash(field, value)
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("name", "fenrir");
		hash.put("age", "18");
		hash.put("power", "100000");
		
		// 赋值(key, field, value)
		stringRedisTemplate.opsForHash().putAll("hash", hash);

		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 取值
	 */
	@GetMapping(
		value = "/keyHash/hget", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hget() {
		// 赋值(key, field, value)
		Object hash = stringRedisTemplate.opsForHash().get("hash", "field");
		
		return new BasicResponseVO(200, "OK", hash);
	}
	
	/**
	 * 批量取值
	 */
	@GetMapping(
		value = "/keyHash/hmget", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hmget() {
		// 准备数据
		Collection<Object> hashKeys = new HashSet<>();
		hashKeys.add("field");
		hashKeys.add("name");
		hashKeys.add("age");
		hashKeys.add("power");
		
		// 赋值(key, field, value)
		List<Object> data = stringRedisTemplate.opsForHash().multiGet("hash", hashKeys);
		
		return new BasicResponseVO(200, "OK", data);
	}
	
	/**
	 * 赋值,field不存在时起效
	 */
	@GetMapping(
		value = "/keyHash/hincrby", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hincrby() {
		// 自增指定的值(key, field, value)
		stringRedisTemplate.opsForHash().increment("hash", "age", 7);
		
		return new BasicResponseVO(200, "OK", null);
	}
	
	/**
	 * 检查field是否存在
	 */
	@GetMapping(
		value = "/keyHash/hexists", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hexists() {
		// 检查field是否存在(key, field)
		Boolean flag = stringRedisTemplate.opsForHash().hasKey("hash", "speed");
		
		return new BasicResponseVO(200, "OK", flag);
	}

}