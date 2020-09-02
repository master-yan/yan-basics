package com.yan.origin;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * redis api 原生用法，管道用法
 * @author master-yan
 *
 */
public class RedisPipelineOrigin {

	public static void main(String[] args) {
		// 普通方式
		ordinaryTest();
		// 管道方式
		pipelineTest();
	}
	
	/**
	 * 普通方式
	 */
	public static void ordinaryTest() {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 普通方式自增十万次
		long startTime = System.currentTimeMillis();
		System.out.println("普通方式开始时间: " + sdf.format(new Timestamp(startTime)));
		
		// 创建连接池
		JedisPool pool = new JedisPool("127.0.0.1", 6379);
		
		// 连接库
		Jedis jedis = pool.getResource();
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 保存到redis
		jedis.set("number", "1");
		
		for (int i = 1; i < 100000; i++) {
			jedis.incr("number");
		}
		
		// 关闭连接
		jedis.close();
		
		// 关闭连接池
		pool.close();
		
		System.out.println("普通方式结束时间: " + sdf.format(new Timestamp(System.currentTimeMillis())));
		System.out.println(System.currentTimeMillis() - startTime);
	}
	
	/**
	 * 管道方式
	 */
	public static void pipelineTest() {
		// 设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 管道方式自增十万次
		long startTime = System.currentTimeMillis();
		System.out.println("管道方式开始时间: " + sdf.format(new Timestamp(startTime)));

		// 创建连接池
		JedisPool pool = new JedisPool("127.0.0.1", 6379);
		
		// 连接库
		Jedis jedis = pool.getResource();
		
		// 输入密码
		jedis.auth("fenrir123456.");
		
		// 创建管道
		Pipeline pipeline = jedis.pipelined();
		
		// 保存到redis
		pipeline.set("number", "1");
		
		for (int i = 1; i < 100000; i++) {
			pipeline.incr("number");
		}
		
		// 执行命令
		pipeline.syncAndReturnAll();
		
		// 关闭连接
		jedis.close();
		
		// 关闭连接池
		pool.close();
		
		System.out.println("管道方式结束时间: " + sdf.format(new Timestamp(System.currentTimeMillis())));
		System.out.println(System.currentTimeMillis() - startTime);
	}
	
}