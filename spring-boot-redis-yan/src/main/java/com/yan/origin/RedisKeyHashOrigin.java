package com.yan.origin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis api 原生用法
 * key-hash结构： 一个key对应一个hash
 * @author master-yan
 *
 */
public class RedisKeyHashOrigin {

	/**
	 * 赋值
	 */
	@Test
	public void hset() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值(key, field, value)
		jedis.hset("hash", "field", "value");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 批量赋值
	 */
	@Test
	public void hmset() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 创建hash(field, value)
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("name", "fenrir");
		hash.put("age", "18");
		hash.put("power", "100000");
		
		// 赋值(key, hash)
		jedis.hmset("hash", hash);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值
	 */
	@Test
	public void hget() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值(key, field)
		String value = jedis.hget("hash", "field");
		System.out.println(value);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 批量取值
	 */
	@Test
	public void hmget() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 批量取值(key, field...)
		List<String> values = jedis.hmget("hash", "field", "name", "age", "power");
		
		int size = values.size();
		for (int i = 0; i < size; i++) {
			System.out.println(values.get(i));
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 自增指定的值(仅value为数字有效)
	 */
	@Test
	public void hincrby() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 自增指定的值(key, field, value)
		jedis.hincrBy("hash", "age", 7);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 赋值,field不存在时起效
	 */
	@Test
	public void hsetnx() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值,field不存在时起效(key, field, value)
		jedis.hsetnx("hash", "speed", "100000");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 检查field是否存在
	 */
	@Test
	public void hexists() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 检查field是否存在(key, field)
		System.out.println(jedis.hexists("hash", "speed"));
		
		// 关闭链接
		jedis.close();
	}

	/**
	 * 获取当前hash结构中的全部属性和值
	 */
	@Test
	public void hgetAll() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 获取当前hash结构中的全部属性和值(key)
		Map<String, String> hash = jedis.hgetAll("hash");
		
		for(Map.Entry<String, String> entry : hash.entrySet()){
			System.out.println(entry.getKey() + ":" +  entry.getValue());
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 获取当前hash结构中的全部键
	 */
	@Test
	public void hkeys() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 获取当前hash结构中的全部键(key)
		Set<String> fields = jedis.hkeys("hash");
		
		for (String field : fields) {
			System.out.println(field);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 获取当前hash结果中的全部值
	 */
	@Test
	public void hvals() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 获取当前hash结构中的全部值(key)
		List<String> values = jedis.hvals("hash");
		
		int size = values.size();
		
		for (int i = 0; i < size; i++) {
			System.out.println(values.get(i));
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 获取当前hash结构中属性的数量
	 */
	@Test
	public void hlen() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 获取当前hash结构中属性的数量(key)
		System.out.println(jedis.hlen("hash"));
		
		// 关闭链接
		jedis.close();
	}
	
}