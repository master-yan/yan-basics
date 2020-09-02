package com.yan.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yan.entity.BasicResponseVO;
import com.yan.service.RedisKeyStringService;

/**
 * service - 调用spring 封装的 redis key string api
 * @author master-yan
 *
 */
@Service
public class RedisKeyStringServiceImpl implements RedisKeyStringService {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Override
	public BasicResponseVO set(String key, String value) {
		// 监听key
		stringRedisTemplate.watch(key);

		// 开启事务
		stringRedisTemplate.multi();

		try {
			// 赋值(key, value)
			stringRedisTemplate.opsForValue().set(key, value);
			
			if (value.equals("100")) {
				throw new Exception("造个异常");
			}
			
			// 执行事务
			stringRedisTemplate.exec();
		} catch (Exception e) {
			// 取消事务
			stringRedisTemplate.discard();
			e.printStackTrace();
			return new BasicResponseVO(500, "来了来了", null);
		}
		
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO mset() {
		// 准备数据
		Map<String, String> map = new HashMap<>();
		map.put("redisTest1", "随随便便给的值1");
		map.put("redisTest2", "随随便便给的值2");
		map.put("redisTest3", "随随便便给的值3");
		
		// 批量赋值(key, value, ...)
		stringRedisTemplate.opsForValue().multiSet(map);
		
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO get() {
		// 取值(key)
		String data = stringRedisTemplate.opsForValue().get("redisTest1");
		
		return new BasicResponseVO(200, "OK", data);
	}

	@Override
	public BasicResponseVO mget() {
		// 准备数据
		Set<String> keys = new HashSet<>();
		keys.add("redisTest1");
		keys.add("redisTest2");
		keys.add("redisTest3");
		
		// 取值(key...)
		List<String> list = stringRedisTemplate.opsForValue().multiGet(keys);
		
		return new BasicResponseVO(200, "OK", list);
	}

	@Override
	public BasicResponseVO incr() {
		// 自增1(值为数字的情况)
		stringRedisTemplate.opsForValue().increment("redisTest");
		
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO incrBy() {
		// 自增指定的值(key, increment)
		stringRedisTemplate.opsForValue().increment("redisTest", 20);
		
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO decr() {
		// 自减1(值为数字的情况)
		stringRedisTemplate.opsForValue().decrement("redisTest");
		
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO decrBy() {
		// 自减指定的值(key, increment)
		stringRedisTemplate.opsForValue().decrement("redisTest", 10);
		
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO setex() {
		// 设置值的同时,设置生命周期[时间的单位是秒, 有效期一分钟就写60]
		// key, seconds, value
		stringRedisTemplate.opsForValue().set("redisTest", "50", 10);
		stringRedisTemplate.expire("redisTest", 10, TimeUnit.SECONDS);
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO setnx() {
		// 赋值,key不存在时起效(key, value)
		stringRedisTemplate.opsForValue().getAndSet("redisTest", "90");
			
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO apped() {
		// 在key对应的值后追加内容(key, value)
		stringRedisTemplate.opsForValue().append("redisTest", "90");
		
		return new BasicResponseVO(200, "OK", null);
	}

	@Override
	public BasicResponseVO strlen() {
		// 在key对应的值后追加内容(key, value)
		Long length = stringRedisTemplate.opsForValue().size("redisTest");
				
		return new BasicResponseVO(200, "OK", length);
	}

}
