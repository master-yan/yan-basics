package com.yan.origin;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis api 原生用法
 * key-set结构： 一个key对应一个set
 * @author master-yan
 *
 */
public class RedisKeySetOrigin {

	/**
	 * 赋值
	 */
	@Test
	public void sadd() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值(key, value...)
		// 不知道为什么全整数的时候会有序...
		jedis.sadd("set", "一", "二", "三", "四", "五");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值
	 */
	@Test
	public void smembers() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值(key)
		Set<String> set = jedis.smembers("set");
		
		for (String string : set) {
			System.out.println(string);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值,随机获取一个数据并从set中移除
	 */
	@Test
	public void spop() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值,随机获取一个数据并从set中移除,count不写默认为1(key, count)
		// jedis.spop("set")
		Set<String> set = jedis.spop("set", 2);
		
		for (String string : set) {
			System.out.println(string);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取多个集合的交集
	 */
	@Test
	public void sinter() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取多个集合的交集(key...)
		Set<String> set = jedis.sinter("set", "set2", "set3");
		
		for (String string : set) {
			System.out.println(string);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取多个集合的并集
	 */
	@Test
	public void sunion() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取多个集合的并集(key...)
		Set<String> set = jedis.sunion("set", "set2", "set3");
		
		for (String string : set) {
			System.out.println(string);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取多个集合的差集
	 */
	@Test
	public void sdiff() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取多个集合的差集(key...)
		Set<String> set = jedis.sdiff("set", "set2", "set3");
		
		for (String string : set) {
			System.out.println(string);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 删除数据
	 */
	@Test
	public void srem() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 删除数据(key, member...)
		System.out.println(jedis.srem("set", "1", "2"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 查看当前set是否包含该值
	 */
	@Test
	public void sismember() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看当前set是否包含该值(key, member)
		System.out.println(jedis.sismember("set", "1"));
		
		// 关闭链接
		jedis.close();
	}
	
}