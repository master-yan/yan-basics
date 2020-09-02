package com.yan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicResponseVO;

/**
 * controller - 调用spring封装的redis(key list)
 * @author master-yan
 *
 */
@RestController
public class RedisKeyListController {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	/**
	 * 赋值, 从左侧开始插入
	 */
	@GetMapping(
		value = "/keyList/lpush", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lpush() {
		// 赋值(key, value...)
		stringRedisTemplate.opsForList().leftPush("list", "1");
		
		stringRedisTemplate.opsForList().leftPushAll("list", "-1", "-2", "-3", "-4");
		
		return new BasicResponseVO(200, "OK", null);
	}
	
	/**
	 * 赋值, 从右侧开始插入
	 */
	@GetMapping(
		value = "/keyList/rpush", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO rpush() {
		// 赋值(key, value...)
		stringRedisTemplate.opsForList().rightPush("list", "2");
		
		stringRedisTemplate.opsForList().rightPushAll("list", "3", "4", "5", "6");
		
		return new BasicResponseVO(200, "OK", null);
	}
	
	/**
	 * 赋值, 指定下标
	 */
	@GetMapping(
		value = "/keyList/lset", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lset() {
		// 赋值, 指定下标(key, index, value)
		// 如果下标存在了, 会覆盖, 下标从0开始,-1表示倒数第一个...
		stringRedisTemplate.opsForList().set("list", 4, "一");
		
		return new BasicResponseVO(200, "OK", null);
	}
	
	/**
	 * 取值,从左开始取,取出后删除
	 */
	@GetMapping(
		value = "/keyList/lpop", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lpop() {
		// 取值,从左开始取,取出后删除(key)
		String data = stringRedisTemplate.opsForList().leftPop("list");
		
		return new BasicResponseVO(200, "OK", data);
	}
	
	/**
	 * 取值,从右开始取,取出后删除
	 */
	@GetMapping(
		value = "/keyList/rpop", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO rpop() {
		// 取值,从左开始取,取出后删除(key)
		String data = stringRedisTemplate.opsForList().rightPop("list");
		
		return new BasicResponseVO(200, "OK", data);
	}
	
	/**
	 * 取值,指定范围获取
	 */
	@GetMapping(
		value = "/keyList/lrange", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lrange() {
		// 取值,指定范围获取(key, startIndex, stopIndex)
		List<String> list = stringRedisTemplate.opsForList().range("list", 0, 10);
		
		return new BasicResponseVO(200, "OK", list);
	}

	/**
	 * 取值,指定下标获取
	 */
	@GetMapping(
		value = "/keyList/lindex", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lindex() {
		// 取值,指定下标获取(key, index)
		String data = stringRedisTemplate.opsForList().index("list", 0);

		return new BasicResponseVO(200, "OK", data);
	}
	
	/**
	 * 获取列表长度
	 */
	@GetMapping(
		value = "/keyList/llen", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO llen() {
		// 获取列表长度(key)
		Long size = stringRedisTemplate.opsForList().size("list");

		return new BasicResponseVO(200, "OK", size);
	}

	/**
	 * 删除列表中的数据
	 */
	@GetMapping(
		value = "/keyList/lrem", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lrem() {
		// 删除列表中的数据(key, count, value)
		// 删除count个跟value一样的数据，count大于0从左到右查找, count小于o从右到左查找, count等于0删除全部
		stringRedisTemplate.opsForList().remove("list", 0, "一");

		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 	删除，保留指定范围的数据
	 */
	@GetMapping(
		value = "/keyList/ltrim", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO ltrim() {
		// 删除，保留指定范围的数据(key, startIndex, stopIndex)
		stringRedisTemplate.opsForList().trim("list", 3, 5);

		return new BasicResponseVO(200, "OK", null);
	}
	
	/**
	 * 把源列表的最后一个数据, 剪切到目标列表的头部
	 */
	@GetMapping(
		value = "/keyList/rpoplpush", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO rpoplpush() {
		// 把源列表的最后一个数据, 剪切到目标列表的头部(source, target)
		// target不存在会创建
		stringRedisTemplate.opsForList().rightPopAndLeftPush("list", "list2");

		return new BasicResponseVO(200, "OK", null);
	}
	
}