package com.yan.origin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis api 原生用法
 * key-zset结构： 一个key对应一个有序set
 * @author master-yan
 *
 */
public class RedisKeyZsetOrigin {

	/**
	 * 赋值,成员唯一
	 */
	@Test
	public void zadd() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值,成员唯一(key, score, member...)
		jedis.zadd("zset", 100, "张三");
		
		Map<String, Double> members = new HashMap<>();
		members.put("李四", 60D);
		members.put("王五", 75D);
		members.put("赵六", 80D);
		members.put("宋七", 99D);
		
		// 赋值,成员唯一(key, score, member...)
		jedis.zadd("zset", members);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 自增成员的数值
	 */
	@Test
	public void zincrby() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 修改成员的数值(key, score, member)
		// 成员存在时增加数值, 不存在时新增成员
		jedis.zincrby("zset", 120, "张三");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 查看指定成员的数值
	 */
	@Test
	public void zscore() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看指定成员的数值(key, member)
		System.out.println(jedis.zscore("zset", "张三"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 查看键的成员数量
	 */
	@Test
	public void zcard() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看键的成员数量(key, member)
		System.out.println(jedis.zcard("zset"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 根据数值查询在范围内的成员有几个
	 */
	@Test
	public void zcount() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 根据数值查询在范围内的成员有几个(key, maxScore, minScore)
		System.out.println(jedis.zcount("zset", 10, 100));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 删除键中的成员
	 */
	@Test
	public void zrem() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 删除键中的成员(key, memeber)
		System.out.println(jedis.zrem("zset", "宋七"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 根据数值从小到大排序,根据下标选范围
	 */
	@Test
	public void zrange() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 根据数值从小到大排序,根据下标选范围(key, startIndex, stopIndex)
		// 如果结尾添加withscores,那么会返回成员对应的分数
		Set<String> zset = jedis.zrange("zset", 0, 2);
		
		for (String string : zset) {
			System.out.println(string);
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 根据数值从大到小排序,根据下标选范围
	 */
	@Test
	public void zrevrange() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 根据数值从大到小排序,根据下标选范围(key, startIndex, stopIndex)
		// 如果结尾添加withscores,那么会返回成员对应的分数
		Set<String> zset = jedis.zrevrange("zset", 0, 2);
		
		for (String string : zset) {
			System.out.println(string);
		}
		
		// 关闭链接
		jedis.close();
	}
	
}