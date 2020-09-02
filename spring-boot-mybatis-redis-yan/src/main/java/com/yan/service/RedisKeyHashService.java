package com.yan.service;

import org.springframework.stereotype.Service;

import com.yan.entity.BasicResponseVO;

/**
 * service - 调用spring 封装的 redis key hash api
 * @author master-yan
 *
 */
@Service
public interface RedisKeyHashService {

	/**
	 * 赋值
	 */
	BasicResponseVO hset();
	
	/**
	 * 批量赋值
	 */
	BasicResponseVO hmset();
	
	/**
	 * 取值
	 */
	BasicResponseVO hget();
	
	/**
	 * 批量取值
	 */
	BasicResponseVO hmget();
	
	/**
	 * 自增指定的值(仅value为数字有效)
	 */
	BasicResponseVO hincrby();
	
	/**
	 * 检查field是否存在
	 */
	BasicResponseVO hexists();
	
}