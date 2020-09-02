package com.yan.origin;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis api 原生用法，库常用命令
 * @author master-yan
 *
 */
public class RedisRepositoryOrigin {

	/**
	 * 清空当前所在的数据库
	 */
	@Test
	public void flushDB() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 清空当前所在的数据库
		jedis.flushDB();
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 清空所有数据库
	 */
	@Test
	public void flushAll() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 清空所有数据库
		jedis.flushAll();
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 查看当前库的key有几个
	 */
	@Test
	public void dbSize() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看当前库的key有几个
		System.out.println(jedis.dbSize());
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 查看最后一次操作的时间
	 */
	@Test
	public void lastsave() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 查看最后一次操作的时间
		System.out.println(jedis.lastsave());
		
		// 关闭链接
		jedis.close();
	}
	
	/**
	 * 实时监控redis服务接收到的目录,一般用来调试?待测试
	 */
	@Test
	public void monitor() {
		// 连接redis(ip, 端口)
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 实时监控redis服务接收到的目录,一般用来调试
//		jedis.monitor();
		
		// 关闭链接
		jedis.close();
	}
	
}