package com.yan.template;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * spring封装的redis key set结构api
 * @author master-yan
 *
 */
@Service
public class RedisKeySetTemplateDemo {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 赋值
	 */
	@Test
	public void sadd() {
		// 赋值(key, value...)
		// 不知道为什么全整数的时候会有序...
		stringRedisTemplate.opsForSet().add("set", "一", "二", "三", "四", "五");
	}

	/**
	 * 取值
	 */
	@Test
	public Set<String> smembers() {
		// 取值(key)
		return stringRedisTemplate.opsForSet().members("set");
	}

	/**
	 * 取值,随机获取一个数据并从set中移除
	 */
	@Test
	public List<String> spop() {
		// 取值,随机获取一个数据并从set中移除,count不写默认为1(key, count)
		// jedis.spop("set")
		return stringRedisTemplate.opsForSet().pop("set", 1);
	}

	/**
	 * 取多个集合的交集
	 */
	@Test
	public Set<String> sinter() {
		// 取多个集合的交集(key...)
		return stringRedisTemplate.opsForSet().intersect("set", "set2");
	}

	/**
	 * 取多个集合的并集
	 */
	@Test
	public Set<String> sunion() {
		// 取多个集合的并集(key...)
		return stringRedisTemplate.opsForSet().union("set", "set2");
	}

	/**
	 * 取多个集合的差集
	 */
	@Test
	public Set<String> sdiff() {
		// 取多个集合的差集(key...)
		return stringRedisTemplate.opsForSet().difference("set", "set2");
	}

	/**
	 * 删除数据
	 */
	@Test
	public void srem() {
		// 删除数据(key, member...)
		stringRedisTemplate.opsForSet().remove("set", "二", "三");
	}

	/**
	 * 查看当前set是否包含该值
	 */
	@Test
	public boolean sismember() {
		// 查看当前set是否包含该值(key, member)
		return stringRedisTemplate.opsForSet().isMember("set", "1");
	}

}