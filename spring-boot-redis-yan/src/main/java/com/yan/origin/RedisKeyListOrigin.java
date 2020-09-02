package com.yan.origin;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis api 原生用法
 * key-list结构： 一个key对应一个list
 * @author master-yan
 *
 */
public class RedisKeyListOrigin {

	/**
	 * 赋值, 从左侧开始插入
	 */
	@Test
	public void lpush() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值(key, value...)
		jedis.lpush("list", "1", "-1", "-2", "-3", "-4");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 赋值, 从右侧开始插入
	 */
	@Test
	public void rpush() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值(key, value...)
		jedis.rpush("list", "2", "3", "4", "5", "6");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 赋值, 指定下标
	 */
	@Test
	public void lset() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 赋值, 指定下标(key, index, value)
		// 如果下标存在了, 会覆盖, 下标从0开始,-1表示倒数第一个...
		jedis.lset("list", 4, "一");
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值,从左开始取,取出后删除
	 */
	@Test
	public void lpop() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值,从左开始取,取出后删除(key)
		System.out.println(jedis.lpop("list"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值,从右开始取,取出后删除
	 */
	@Test
	public void rpop() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值,从右开始取,取出后删除(key)
		System.out.println(jedis.rpop("list"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值,指定范围获取
	 */
	@Test
	public void lrange() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值,指定范围获取(key, startIndex, stopIndex)
		List<String> list = jedis.lrange("list", 0, 10);
		
		int size = list.size();
		
		for (int i = 0; i < size; i++) {
			System.out.println(list.get(i));
		}
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 取值,指定下标获取
	 */
	@Test
	public void lindex() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 取值,指定下标获取(key, index)
		System.out.println(jedis.lindex("list", 0));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 获取列表长度
	 */
	@Test
	public void llen() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 获取列表长度(key)
		System.out.println(jedis.llen("list"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 删除列表中的数据
	 */
	@Test
	public void lrem() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 删除列表中的数据(key, count, value)
		// 删除count个跟value一样的数据，count大于0从左到右查找, count小于o从右到左查找, count等于0删除全部
		System.out.println(jedis.lrem("list", 0, "一"));
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 	删除，保留指定范围的数据
	 */
	@Test
	public void ltrim() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 删除，保留指定范围的数据(key, startIndex, stopIndex)
		jedis.ltrim("list", 3, 5);
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 把源列表的最后一个数据, 剪切到目标列表的头部
	 */
	@Test
	public void rpoplpush() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 把源列表的最后一个数据, 剪切到目标列表的头部(source, target)
		// target不存在会创建
		jedis.rpoplpush("list", "list2");
		
		// 关闭链接
		jedis.close();
	}
	
}