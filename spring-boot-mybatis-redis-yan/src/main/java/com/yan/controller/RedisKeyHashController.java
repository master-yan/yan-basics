package com.yan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicResponseVO;
import com.yan.service.RedisKeyHashService;

/**
 * controller - 调用spring 封装的 redis key hash api
 * @author master-yan
 *
 */
@RestController
public class RedisKeyHashController {

	@Autowired
	private RedisKeyHashService redisKeyHashService;
	
	/**
	 * 赋值
	 */
	@GetMapping(
		value = "/keyHash/hset", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hset() {
		return redisKeyHashService.hset();
	}

	/**
	 * 批量赋值
	 */
	@GetMapping(
		value = "/keyHash/hmset", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hmset() {
		return redisKeyHashService.hmset();
	}

	/**
	 * 取值
	 */
	@GetMapping(
		value = "/keyHash/hget", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hget() {
		return redisKeyHashService.hget();
	}
	
	/**
	 * 批量取值
	 */
	@GetMapping(
		value = "/keyHash/hmget", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hmget() {
		return redisKeyHashService.hmget();
	}
	
	/**
	 * 自增指定的值(仅value为数字有效)
	 */
	@GetMapping(
		value = "/keyHash/hincrby", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hincrby() {
		return redisKeyHashService.hincrby();
	}
	
	/**
	 * 检查field是否存在
	 */
	@GetMapping(
		value = "/keyHash/hexists", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO hexists() {
		return redisKeyHashService.hexists();
	}

}