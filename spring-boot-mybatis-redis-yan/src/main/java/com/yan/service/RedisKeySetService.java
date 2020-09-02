package com.yan.service;

import org.springframework.stereotype.Service;

import com.yan.entity.BasicResponseVO;

/**
 * service - 调用spring 封装的 redis key set api
 * @author master-yan
 *
 */
@Service
public interface RedisKeySetService {

	/**
	 * 赋值
	 */
	BasicResponseVO sadd();
	
	/**
	 * 取值
	 */
	BasicResponseVO smembers();
	
	/**
	 * 取值,随机获取一个数据并从set中移除
	 */
	BasicResponseVO spop();
	
	/**
	 * 取多个集合的交集
	 */
	BasicResponseVO sinter();
	
	/**
	 * 取多个集合的并集
	 */
	BasicResponseVO sunion();
	
	/**
	 * 取多个集合的差集
	 */
	BasicResponseVO sdiff();
	
	/**
	 * 删除数据
	 */
	BasicResponseVO srem();
	
	/**
	 * 查看当前set是否包含该值
	 */
	BasicResponseVO sismember();
	
}