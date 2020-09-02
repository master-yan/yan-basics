package com.yan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicResponseVO;
import com.yan.service.RedisKeyListService;

/**
 * controller - 调用spring 封装的 redis key list api
 * @author master-yan
 *
 */
@RestController
public class RedisKeyListController {

	@Autowired
	private RedisKeyListService redisKeyListService;
	
	/**
	 * 赋值, 从左侧开始插入
	 */
	@GetMapping(
		value = "/keyList/lpush", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lpush() {
		return redisKeyListService.lpush();
	}
	
	/**
	 * 赋值, 从右侧开始插入
	 */
	@GetMapping(
		value = "/keyList/rpush", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO rpush() {
		return redisKeyListService.rpush();
	}
	
	/**
	 * 赋值, 指定下标
	 */
	@GetMapping(
		value = "/keyList/lset", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lset() {
		return redisKeyListService.lset();
	}
	
	/**
	 * 取值,从左开始取,取出后删除
	 */
	@GetMapping(
		value = "/keyList/lpop", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lpop() {
		return redisKeyListService.lpop();
	}
	
	/**
	 * 取值,从右开始取,取出后删除
	 */
	@GetMapping(
		value = "/keyList/rpop", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO rpop() {
		return redisKeyListService.rpop();
	}
	
	/**
	 * 取值,指定范围获取
	 */
	@GetMapping(
		value = "/keyList/lrange", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lrange() {
		return redisKeyListService.lrange();
	}
	
	/**
	 * 取值,指定下标获取
	 */
	@GetMapping(
		value = "/keyList/lindex", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lindex() {
		return redisKeyListService.lindex();
	}
	
	/**
	 * 获取列表长度
	 */
	@GetMapping(
		value = "/keyList/llen", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO llen() {
		return redisKeyListService.llen();
	}
	
	/**
	 * 删除列表中的数据
	 */
	@GetMapping(
		value = "/keyList/lrem", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO lrem() {
		return redisKeyListService.lrem();
	}
	
	/**
	 * 	删除，保留指定范围的数据
	 */
	@GetMapping(
		value = "/keyList/ltrim", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO ltrim() {
		return redisKeyListService.ltrim();
	}
	
	/**
	 * 把源列表的最后一个数据, 剪切到目标列表的头部
	 */
	@GetMapping(
		value = "/keyList/rpoplpush", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO rpoplpush() {
		return redisKeyListService.rpoplpush();
	}
	
}