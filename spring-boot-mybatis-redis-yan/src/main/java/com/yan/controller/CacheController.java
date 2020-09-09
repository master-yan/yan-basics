package com.yan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicListVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.BookVO;
import com.yan.service.CacheService;

/**
 * controller - 书本管理（spring boot cache使用demo）
 * @CacheConfig spring boot cache配置
 * CacheConfig 主要针对class配置，当一个class里面用到缓存的方法很多时，可以用该注解配置通用的属性
 * @author master-yan
 *
 */
@RestController
@CacheConfig(cacheManager = "redisCacheManager", cacheNames = "cacheTest")
public class CacheController {

	@Autowired
	private CacheService cacheService;
	
	/**
	 * 查询和添加缓存
	 * @Cacheable spring boot cache配置
	 * Cacheable 主要针对方法配置，通过命名空间+key来使用和添加缓存
	 * 注: 就算缓存被清空了，命名空间跟key存在的话，依然会根据命名空间和key去取值
	 * cacheNames/value 必填，二选一，缓存的命名空间，用来找缓存数据
	 * condition 可选，默认true，条件符合时查询结果才缓存
	 * unless 可选，默认false，条件符合时不缓存
	 * key 可选，spel表达式，这里与参数名称对应，bookName没变的情况返回缓存的值，变了则查询数据库
	 * keyGenerator 可选，key的生成器，key/keyGenerator二选一使用
	 * cacheManager 可选，指定缓存管理器
	 * cacheResolver 可选，指定获取解析器
	 * sync 可选，是否使用异步模式
	 * @param bookName
	 * @param condition
	 * @return
	 */
	@Cacheable(
		cacheManager = "redisCacheManager", 
		cacheNames = "cacheTest", 
		condition = "#condition == true", 
		key = "#bookName"
	)
	@GetMapping(
		value = "/cache/cacheable/{bookName}/{condition}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseGenericityVO<BasicListVO<BookVO>> cacheable(
		@PathVariable(value = "bookName") String bookName, 
		@PathVariable(value = "condition") boolean condition
	) {
		return cacheService.cacheable(bookName);
	}
	
	/**
	 * 根据key查询缓存
	 * @param bookName
	 * @return
	 */
	@Cacheable(
		cacheManager = "redisCacheManager", 
		cacheNames = "cacheTest", 
		key = "#bookName", 
		unless = "true"
	)
	@GetMapping(
		value = "/cache/getCacheByBookName/{bookName}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseGenericityVO<BasicListVO<BookVO>> getCacheByBookName(
		@PathVariable(value = "bookName") String bookName
	) {
		return new BasicResponseGenericityVO<BasicListVO<BookVO>>(200, "OK", null);
	}
	
	/**
	 * 清空缓存
	 * @CacheEvict spring boot cache配置
	 * CacheEvict 主要针对方法配置，根据命名空间和key来清空缓存
	 * allEntries 可选，默认false，false时根据命名空间+key来清空缓存，true时则情况该命名空间的所有缓存
	 * beforeInvocation 可选，默认false，为true则在方法执行前清空缓存
	 * @param bookName
	 * @return
	 */
	@CacheEvict(
		cacheManager = "redisCacheManager", 
		cacheNames = "cacheTest",
		key = "#bookName", 
		allEntries = false, 
		beforeInvocation = false
	)
	@GetMapping(
		value = "/cache/clear/{bookName}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO clearCache(
		@PathVariable(value = "bookName") String bookName
	) {
		return new BasicResponseVO(200, "OK", null);
	}
	
	/**
	 * 查询并缓存(查询不会调用缓存，每次都查真实数据并添加到缓存)
	 * @param bookName
	 * @return
	 */
	@CachePut(
		cacheManager = "redisCacheManager", 
		cacheNames = "cacheTest", 
		key = "#bookName"
	)
	@GetMapping(
		value = "/cache/cachePut/{bookName}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseGenericityVO<BasicListVO<BookVO>> cachePut(
		@PathVariable(value = "bookName") String bookName
	) {
		return cacheService.cacheable(bookName);
	}
	
	/**
	 * 组合注解
	 * @param bookName
	 * @return
	 */
	@Caching(
		cacheable = {
			@Cacheable(cacheManager = "redisCacheManager", cacheNames = "cacheTest", key = "#bookName")
		}, 
		put = {
			@CachePut(cacheManager = "redisCacheManager", cacheNames = "cacheTest", key = "#bookName")
		}, 
		evict = {
			@CacheEvict(cacheManager = "redisCacheManager", cacheNames = "cacheTest", key = "#bookName", allEntries = false, beforeInvocation = true)
		}
	)
	@GetMapping(
		value = "/cache/caching/{bookName}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseGenericityVO<BasicListVO<BookVO>> caching(
		@PathVariable(value = "bookName") String bookName
	) {
		return cacheService.cacheable(bookName);
	}
	
}