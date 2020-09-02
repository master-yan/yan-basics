package com.yan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicResponseVO;
import com.yan.service.RedisKeyStringService;

/**
 * controller - 调用spring 封装的 redis key string api
 * @author master-yan
 *
 */
@RestController
public class RedisKeyStringController {

	@Autowired
	private RedisKeyStringService redisKeyStringService;
	
	/**
	 * ket string 赋值
	 * @param key
	 * @param value
	 * @return
	 */
	@GetMapping(
		value = "/keyString/set/{key}/{value}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO set(
		@PathVariable(value = "key") String key, 
		@PathVariable(value = "value") String value
	) {
		return redisKeyStringService.set(key, value);
	}
	
	/**
	 * 批量赋值
	 */
	@GetMapping(
		value = "/keyString/mset", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO mset() {
		return redisKeyStringService.mset();
	}
	
	/**
	 * 取值
	 */
	@GetMapping(
		value = "/keyString/get", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO get() {
		return redisKeyStringService.get();
	}
	
	/**
	 * 批量取值
	 */
	@GetMapping(
		value = "/keyString/mget", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO mget() {
		return redisKeyStringService.mget();
	}
	
	/**
	 * 自增1
	 */
	@GetMapping(
		value = "/keyString/incr", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO incr() {
		return redisKeyStringService.incr();
	}
	
	/**
	 * 自增指定的值
	 */
	@GetMapping(
		value = "/keyString/incrBy", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO incrBy() {
		return redisKeyStringService.incrBy();
	}
	
	/**
	 * 自减1
	 */
	@GetMapping(
		value = "/keyString/decr", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO decr() {
		return redisKeyStringService.decr();
	}
	
	/**
	 * 自减指定的值
	 */
	@GetMapping(
		value = "/keyString/decrBy", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO decrBy() {
		return redisKeyStringService.decrBy();
	}
	
	/**
	 * 设置值的同时,设置生命周期
	 */
	@GetMapping(
		value = "/keyString/setex", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO setex() {
		return redisKeyStringService.setex();
	}
	
	/**
	 * 赋值, key不存在时起效
	 */
	@GetMapping(
		value = "/keyString/setnx", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO setnx() {
		return redisKeyStringService.setnx();
	}
	
	/**
	 * 追加内容
	 */
	@GetMapping(
		value = "/keyString/apped", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO apped() {
		return redisKeyStringService.apped();
	}
	
	/**
	 * 查看value的长度
	 */
	@GetMapping(
		value = "/keyString/strlen", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO strlen() {
		return redisKeyStringService.strlen();
	}
	
}