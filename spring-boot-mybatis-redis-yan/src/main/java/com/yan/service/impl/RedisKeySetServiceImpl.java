package com.yan.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yan.entity.BasicResponseVO;
import com.yan.service.RedisKeySetService;

/**
 * service - 调用spring 封装的 redis key set api
 * @author master-yan
 *
 */
@Service
public class RedisKeySetServiceImpl implements RedisKeySetService {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 赋值
	 */
	@Override
	public BasicResponseVO sadd() {
		// 赋值(key, value...)
		// 不知道为什么全整数的时候会有序...
		stringRedisTemplate.opsForSet().add("set", "一", "二", "三", "四", "五");
		
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 取值
	 */
	@Override
	public BasicResponseVO smembers() {
		// 取值(key)
		Set<String> set = stringRedisTemplate.opsForSet().members("set");
		
		return new BasicResponseVO(200, "OK", set);
	}

	/**
	 * 取值,随机获取一个数据并从set中移除
	 */
	@Override
	public BasicResponseVO spop() {
		// 取值,随机获取一个数据并从set中移除,count不写默认为1(key, count)
		// jedis.spop("set")
		List<String> set = stringRedisTemplate.opsForSet().pop("set", 1);
		
		return new BasicResponseVO(200, "OK", set);
	}

	/**
	 * 取多个集合的交集
	 */
	@Override
	public BasicResponseVO sinter() {
		// 取多个集合的交集(key...)
		Set<String> set = stringRedisTemplate.opsForSet().intersect("set", "set2");
		
		return new BasicResponseVO(200, "OK", set);
	}

	/**
	 * 取多个集合的并集
	 */
	@Override
	public BasicResponseVO sunion() {
		// 取多个集合的并集(key...)
		Set<String> set = stringRedisTemplate.opsForSet().union("set", "set2");
		
		return new BasicResponseVO(200, "OK", set);
	}

	/**
	 * 取多个集合的差集
	 */
	@Override
	public BasicResponseVO sdiff() {
		// 取多个集合的差集(key...)
		Set<String> set = stringRedisTemplate.opsForSet().difference("set", "set2");
		
		return new BasicResponseVO(200, "OK", set);
	}

	/**
	 * 删除数据
	 */
	@Override
	public BasicResponseVO srem() {
		// 删除数据(key, member...)
		stringRedisTemplate.opsForSet().remove("set", "二", "三");
				
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 查看当前set是否包含该值
	 */
	@Override
	public BasicResponseVO sismember() {
		// 查看当前set是否包含该值(key, member)
		Boolean flag = stringRedisTemplate.opsForSet().isMember("set", "1");
		
		return new BasicResponseVO(200, "OK", flag);
	}

}