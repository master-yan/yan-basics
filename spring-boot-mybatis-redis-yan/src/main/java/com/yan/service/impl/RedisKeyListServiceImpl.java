package com.yan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yan.entity.BasicResponseVO;
import com.yan.service.RedisKeyListService;

/**
 * service - 调用spring 封装的 redis key list api
 * @author master-yan
 *
 */
@Service
public class RedisKeyListServiceImpl implements RedisKeyListService {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 赋值, 从左侧开始插入
	 */
	@Override
	public BasicResponseVO lpush() {
		// 赋值(key, value...)
		stringRedisTemplate.opsForList().leftPush("list", "1");
		
		stringRedisTemplate.opsForList().leftPushAll("list", "-1", "-2", "-3", "-4");
		
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 赋值, 从右侧开始插入
	 */
	@Override
	public BasicResponseVO rpush() {
		// 赋值(key, value...)
		stringRedisTemplate.opsForList().rightPush("list", "2");
		
		stringRedisTemplate.opsForList().rightPushAll("list", "3", "4", "5", "6");
		
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 赋值, 指定下标
	 */
	@Override
	public BasicResponseVO lset() {
		// 赋值, 指定下标(key, index, value)
		// 如果下标存在了, 会覆盖, 下标从0开始,-1表示倒数第一个...
		stringRedisTemplate.opsForList().set("list", 4, "一");
		
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 取值,从左开始取,取出后删除
	 */
	@Override
	public BasicResponseVO lpop() {
		// 取值,从左开始取,取出后删除(key)
		String data = stringRedisTemplate.opsForList().leftPop("list");
		
		return new BasicResponseVO(200, "OK", data);
	}

	/**
	 * 取值,从右开始取,取出后删除
	 */
	@Override
	public BasicResponseVO rpop() {
		// 取值,从左开始取,取出后删除(key)
		String data = stringRedisTemplate.opsForList().rightPop("list");
		
		return new BasicResponseVO(200, "OK", data);
	}

	/**
	 * 取值,指定范围获取
	 */
	@Override
	public BasicResponseVO lrange() {
		// 取值,指定范围获取(key, startIndex, stopIndex)
		List<String> list = stringRedisTemplate.opsForList().range("list", 0, 10);
		
		return new BasicResponseVO(200, "OK", list);
	}

	/**
	 * 取值,指定下标获取
	 */
	@Override
	public BasicResponseVO lindex() {
		// 取值,指定下标获取(key, index)
		String data = stringRedisTemplate.opsForList().index("list", 0);
		
		return new BasicResponseVO(200, "OK", data);
	}

	/**
	 * 获取列表长度
	 */
	@Override
	public BasicResponseVO llen() {
		// 获取列表长度(key)
		Long size = stringRedisTemplate.opsForList().size("list");
		
		return new BasicResponseVO(200, "OK", size);
	}

	/**
	 * 删除列表中的数据
	 */
	@Override
	public BasicResponseVO lrem() {
		// 删除列表中的数据(key, count, value)
		// 删除count个跟value一样的数据，count大于0从左到右查找, count小于o从右到左查找, count等于0删除全部
		stringRedisTemplate.opsForList().remove("list", 0, "一");
		
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 删除，保留指定范围的数据
	 */
	@Override
	public BasicResponseVO ltrim() {
		// 删除，保留指定范围的数据(key, startIndex, stopIndex)
		stringRedisTemplate.opsForList().trim("list", 3, 5);
		
		return new BasicResponseVO(200, "OK", null);
	}

	/**
	 * 把源列表的最后一个数据, 剪切到目标列表的头部
	 */
	@Override
	public BasicResponseVO rpoplpush() {
		// 把源列表的最后一个数据, 剪切到目标列表的头部(source, target)
		// target不存在会创建
		stringRedisTemplate.opsForList().rightPopAndLeftPush("list", "list2");
		
		return new BasicResponseVO(200, "OK", null);
	}

}