package com.yan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicResponseVO;
import com.yan.service.RedisKeySetService;

/**
 * controller - 调用spring 封装的 redis key set api
 * @author master-yan
 *
 */
@RestController
public class RedisKeySetController {

	@Autowired
	private RedisKeySetService redisKeySetService;
	
	/**
	 * 赋值
	 */
	@GetMapping(
		value = "/keySet/sadd", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO sadd() {
		return redisKeySetService.sadd();
	}
	
	/**
	 * 取值
	 */
	@GetMapping(
		value = "/keySet/smembers", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO smembers() {
		return redisKeySetService.smembers();
	}
	
	/**
	 * 取值,随机获取一个数据并从set中移除
	 */
	@GetMapping(
		value = "/keySet/spop", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO spop() {
		return redisKeySetService.spop();
	}
	
	/**
	 * 取多个集合的交集
	 */
	@GetMapping(
		value = "/keySet/sinter", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO sinter() {
		return redisKeySetService.sinter();
	}
	
	/**
	 * 取多个集合的并集
	 */
	@GetMapping(
		value = "/keySet/sunion", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO sunion() {
		return redisKeySetService.sunion();
	}
	
	/**
	 * 取多个集合的差集
	 */
	@GetMapping(
		value = "/keySet/sdiff", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO sdiff() {
		return redisKeySetService.sdiff();
	}
	
	/**
	 * 删除数据
	 */
	@GetMapping(
		value = "/keySet/srem", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO srem() {
		return redisKeySetService.srem();
	}
	
	/**
	 * 查看当前set是否包含该值
	 */
	@GetMapping(
		value = "/keySet/sismember", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO sismember() {
		return redisKeySetService.sismember();
	}
	
}