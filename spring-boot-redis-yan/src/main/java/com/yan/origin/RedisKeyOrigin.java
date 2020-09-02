package com.yan.origin;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis api 原生用法，常用命令
 * @author master-yan
 *
 */
public class RedisKeyOrigin {

	/**
	 * 查看redis中的全部key
	 */
	@Test
	public void keys() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看redis中的全部key(repository...)
		Set<String> keys = jedis.keys("*");
		
		for (String key : keys) {
			System.out.println(key);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * key是否存在
	 */
	@Test
	public void exists() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// key是否存在(key)
		System.out.println(jedis.exists("list"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 删除key
	 */
	@Test
	public void del() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 删除key(key...)
		System.out.println(jedis.del("redisTest1", "redisTest2"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 设置key的生命周期,时间单位秒
	 */
	@Test
	public void expire() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 设置key的生命周期,时间单位秒(key, seconds)
		jedis.expire("redisTest", 10);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 设置key的生命周期,时间单位毫秒
	 */
	@Test
	public void pexpire() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 设置key的生命周期,时间单位毫秒(key, milliseconds)
		jedis.pexpire("redisTest", 1000);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 设置键的生命周期,时间单位时间戳?这个待研究
	 */
	@Test
	public void expireAt() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 设置key的生命周期,时间单位时间戳(key, milliseconds)
		jedis.expireAt("redisTest", 1000);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 设置key的生命周期,时间单位时间戳?这个待研究
	 */
	@Test
	public void pexpireAt() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 设置key的生命周期,时间单位时间戳(key, milliseconds)
		jedis.pexpireAt("redisTest", 1000);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 查看key剩余的生存时间单位秒
	 */
	@Test
	public void ttl() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看key剩余的生存时间单位秒(key, milliseconds)
		System.out.println(jedis.ttl("list"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 查看键key余的生存时间单位毫秒
	 */
	@Test
	public void pttl() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看key剩余的生存时间单位毫秒(key, milliseconds)
		System.out.println(jedis.pttl("list"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 移除key的生存时间
	 */
	@Test
	public void persist() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 移除key的生存时间(key)
		System.out.println(jedis.persist("list"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 根据下标切换库
	 */
	@Test
	public void select() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 根据下标切换库(repositoryIndex)
		jedis.select(1);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 把key移到另一个库
	 */
	@Test
	public void move() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 把key移到另一个库(key, repositoryIndex)
		jedis.move("list", 1);
		
		// 关闭链接
		jedis.close();
	}
	
}