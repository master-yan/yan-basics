package com.yan.template;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * spring封装的redis key list结构api
 * @author master-yan
 *
 */
public class RedisKeyListTemplateDemo {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 赋值, 从左侧开始插入
	 */
	@Test
	public void lpush() {
		// 赋值(key, value...)
		stringRedisTemplate.opsForList().leftPush("list", "1");
		
		stringRedisTemplate.opsForList().leftPushAll("list", "-1", "-2", "-3", "-4");
	}

	/**
	 * 赋值, 从右侧开始插入
	 */
	@Test
	public void rpush() {
		// 赋值(key, value...)
		stringRedisTemplate.opsForList().rightPush("list", "2");
		
		stringRedisTemplate.opsForList().rightPushAll("list", "3", "4", "5", "6");
	}

	/**
	 * 赋值, 指定下标
	 */
	@Test
	public void lset() {
		// 赋值, 指定下标(key, index, value)
		// 如果下标存在了, 会覆盖, 下标从0开始,-1表示倒数第一个...
		stringRedisTemplate.opsForList().set("list", 4, "一");
	}

	/**
	 * 取值,从左开始取,取出后删除
	 */
	@Test
	public String lpop() {
		// 取值,从左开始取,取出后删除(key)
		return stringRedisTemplate.opsForList().leftPop("list");
	}

	/**
	 * 取值,从右开始取,取出后删除
	 */
	@Test
	public String rpop() {
		// 取值,从左开始取,取出后删除(key)
		return stringRedisTemplate.opsForList().rightPop("list");
	}

	/**
	 * 取值,指定范围获取
	 */
	@Test
	public List<String> lrange() {
		// 取值,指定范围获取(key, startIndex, stopIndex)
		return stringRedisTemplate.opsForList().range("list", 0, 10);
	}

	/**
	 * 取值,指定下标获取
	 */
	@Test
	public String lindex() {
		// 取值,指定下标获取(key, index)
		return stringRedisTemplate.opsForList().index("list", 0);
	}

	/**
	 * 获取列表长度
	 */
	@Test
	public long llen() {
		// 获取列表长度(key)
		return stringRedisTemplate.opsForList().size("list");
	}

	/**
	 * 删除列表中的数据
	 */
	@Test
	public void lrem() {
		// 删除列表中的数据(key, count, value)
		// 删除count个跟value一样的数据，count大于0从左到右查找, count小于o从右到左查找, count等于0删除全部
		stringRedisTemplate.opsForList().remove("list", 0, "一");
	}

	/**
	 * 删除，保留指定范围的数据
	 */
	@Test
	public void ltrim() {
		// 删除，保留指定范围的数据(key, startIndex, stopIndex)
		stringRedisTemplate.opsForList().trim("list", 3, 5);
	}

	/**
	 * 把源列表的最后一个数据, 剪切到目标列表的头部
	 */
	@Test
	public void rpoplpush() {
		// 把源列表的最后一个数据, 剪切到目标列表的头部(source, target)
		// target不存在会创建
		stringRedisTemplate.opsForList().rightPopAndLeftPush("list", "list2");
	}

}