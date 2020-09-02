package com.yan.service;

import org.springframework.stereotype.Service;

import com.yan.entity.BasicResponseVO;

/**
 * service - 调用spring 封装的 redis key string api
 * @author master-yan
 *
 */
@Service
public interface RedisKeyStringService {

	/**
	 * 赋值
	 * @param key
	 * @param value
	 */
	BasicResponseVO set(String key, String value);
	
	/**
	 * 批量赋值
	 */
	BasicResponseVO mset();
	
	/**
	 * 取值
	 */
	BasicResponseVO get();
	
	/**
	 * 批量取值
	 */
	BasicResponseVO mget();
	
	/**
	 * 自增1
	 */
	BasicResponseVO incr();
	
	/**
	 * 自增指定的值
	 */
	BasicResponseVO incrBy();
	
	/**
	 * 自减1
	 */
	BasicResponseVO decr();
	
	/**
	 * 自减指定的值
	 */
	BasicResponseVO decrBy();
	
	/**
	 * 设置值的同时,设置生命周期
	 */
	BasicResponseVO setex();
	
	/**
	 * 赋值, key不存在时起效
	 */
	BasicResponseVO setnx();
	
	/**
	 * 追加内容
	 */
	BasicResponseVO apped();
	
	/**
	 * 查看value的长度
	 */
	BasicResponseVO strlen();
	
}