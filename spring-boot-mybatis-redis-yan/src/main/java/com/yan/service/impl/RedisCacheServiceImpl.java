package com.yan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yan.dao.RedisCacheDao;
import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.SweetsPO;
import com.yan.entity.SweetsVO;
import com.yan.service.RedisCacheService;
import com.yan.util.Utils;

/**
 * service - 糖果管理
 * @author master-yan
 *
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {
	
	@Autowired
	private RedisCacheDao redisCacheDao;

	/**
	 * @Cacheable 查询数据加入缓存
	 */
	@Cacheable
	@Override
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<SweetsVO>> queryAll(
		Integer pageNumber, Integer pageSize
	) {
		// 查询总条数
		long total = redisCacheDao.queryAllTotal();
		
		// 构造分页返回值
		BasicListResponseGenericityVO<SweetsVO> listResponse = Utils.<SweetsVO>getBasicListResponseGenericityVO(pageNumber, pageSize, total);
		
		// 查询数据, 使用处理过的分页参数
		List<SweetsPO> pos = redisCacheDao.queryAll(listResponse.getPageIndex(), listResponse.getPageSize());
		
		// 对象转换
		List<SweetsVO> vos = Utils.copyListProperties(SweetsVO.class, pos);
		
		// 设置列表
		listResponse.setContent(vos);
		
		return new BasicResponseGenericityVO<BasicListResponseGenericityVO<SweetsVO>>(200, "OK", listResponse);
	}

	/**
	 * @CachePut 修改了数据库的数据，同时更新缓存
	 */
	@Transactional
	@Override
	public BasicResponseVO add(SweetsVO vo) {
		SweetsPO po = new SweetsPO();
		
		// 转换模型
		BeanUtils.copyProperties(vo, po);
		
		// 保存数据
		int row = redisCacheDao.add(po);
		
		return new BasicResponseVO(200, "OK", row);
	}

	/**
	 * @CachePut 修改了数据库的数据，同时更新缓存
	 */
	@Transactional
	@Override
	public BasicResponseVO update(SweetsVO vo) {
		SweetsPO po = new SweetsPO();
		
		// 转换模型
		BeanUtils.copyProperties(vo, po);
		
		// 修改数据
		int row = redisCacheDao.update(po);
		
		return new BasicResponseVO(200, "OK", row);
	}

	/**
	 * @CacheEvict 删除数据，删除缓存
	 */
	@Transactional
	@Override
	public BasicResponseVO deleteById(Integer id) {
		// 删除数据
		int row = redisCacheDao.deleteById(id);
		
		return new BasicResponseVO(200, "OK", row);
	}

}