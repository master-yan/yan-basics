package com.yan.origin;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis api 原生用法
 * key-string结构： 一个key对应一个string
 * @author master-yan
 *
 */
public class RedisKeyStringOrigin {

	/**
	 * 赋值
	 */
	@Test
	public void set() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值(key, value)
		jedis.set("redisTest", "10");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 批量赋值
	 */
	@Test
	public void mset() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 批量赋值(key, value, ...)
		jedis.mset("redisTest1", "随随便便给的值1", "redisTest2", "随随便便给的值2");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值
	 */
	@Test
	public void get() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值(key)
		String data = jedis.get("redisTest");
		
		// 关闭连接
		jedis.close();
		
		System.out.println(data);
	}
	
	/**
	 * 批量取值
	 */
	@Test
	public void mget() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值(key)
		List<String> data = jedis.mget("redisTest", "redisTest1", "redisTest2");
		
		// 关闭连接
		jedis.close();
		
		int size = data.size();
		for (int i = 0; i < size; i++) {
			System.out.println(data.get(i));
		}
	}
	
	/**
	 * 自增1
	 */
	@Test
	public void incr() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 自增1(值为数字的情况)
		jedis.incr("redisTest");
		
		// 关闭连接
		jedis.close();
	}
	
	/**
	 * 自增指定的值
	 */
	@Test
	public void incrBy() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 自增指定的值(key, increment)
		jedis.incrBy("redisTest", 20);
		
		// 关闭连接
		jedis.close();
	}
	
	/**
	 * 自减1
	 */
	@Test
	public void decr() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 自减1(值为数字的情况)
		jedis.decr("redisTest");
		
		// 关闭连接
		jedis.close();
	}
	
	/**
	 * 自减指定的值
	 */
	@Test
	public void decrBy() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 自减指定的值(key, increment)
		jedis.decrBy("redisTest", 10);
		
		// 关闭连接
		jedis.close();
	}
	
	/**
	 * 设置值的同时,设置生命周期
	 */
	@Test
	public void setex() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 设置值的同时,设置生命周期[时间的单位是秒, 有效期一分钟就写60]
		// key, seconds, value
		jedis.setex("redisTest", 10, "50");
		
		// 关闭连接
		jedis.close();
	}
	
	/**
	 * 赋值, key不存在时起效
	 */
	@Test
	public void setnx() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值,key不存在时起效(key, value)
		jedis.setnx("redisTest", "90");
		
		// 关闭连接
		jedis.close();
	}
	
	/**
	 * 追加内容
	 */
	@Test
	public void apped() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 在key对应的值后追加内容(key, value)
		jedis.append("redisTest", "90");
		
		// 关闭连接
		jedis.close();
	}
	
	/**
	 * 查看value的长度
	 */
	@Test
	public void strlen() {
		// 连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 在key对应的值后追加内容(key, value)
		Long length = jedis.strlen("redisTest");
		System.out.println(length);
		
		// 关闭连接
		jedis.close();
	}

}