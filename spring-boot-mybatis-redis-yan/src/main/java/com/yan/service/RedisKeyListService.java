package com.yan.service;

import org.springframework.stereotype.Service;

import com.yan.entity.BasicResponseVO;

/**
 * service - 调用spring 封装的 redis key list api
 * @author master-yan
 *
 */
@Service
public interface RedisKeyListService {

	/**
	 * 赋值, 从左侧开始插入
	 */
	BasicResponseVO lpush();
	
	/**
	 * 赋值, 从右侧开始插入
	 */
	BasicResponseVO rpush();
	
	/**
	 * 赋值, 指定下标
	 */
	BasicResponseVO lset();
	
	/**
	 * 取值,从左开始取,取出后删除
	 */
	BasicResponseVO lpop();
	
	/**
	 * 取值,从右开始取,取出后删除
	 */
	BasicResponseVO rpop();
	
	/**
	 * 取值,指定范围获取
	 */
	BasicResponseVO lrange();
	
	/**
	 * 取值,指定下标获取
	 */
	BasicResponseVO lindex();
	
	/**
	 * 获取列表长度
	 */
	BasicResponseVO llen();
	
	/**
	 * 删除列表中的数据
	 */
	BasicResponseVO lrem();
	
	/**
	 * 	删除，保留指定范围的数据
	 */
	BasicResponseVO ltrim();
	
	/**
	 * 把源列表的最后一个数据, 剪切到目标列表的头部
	 */
	BasicResponseVO rpoplpush();
	
}