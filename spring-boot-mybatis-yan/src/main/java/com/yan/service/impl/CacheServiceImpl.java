package com.yan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.dao.CacheDao;
import com.yan.entity.BasicListVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BookPO;
import com.yan.entity.BookVO;
import com.yan.service.CacheService;
import com.yan.util.Utils;

/**
 * service - 书本管理（spring boot cache使用demo）
 * @author master-yan
 *
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private CacheDao cacheDao;

	@Override
	public BasicResponseGenericityVO<BasicListVO<BookVO>> cacheable(String bookName) {
		// 创建返回值模型
		BasicListVO<BookVO> listResponse = new BasicListVO<>();
		
		// 查询数据
		List<BookPO> pos = cacheDao.cacheable(bookName);
		
		// 对象转换
		List<BookVO> vos = Utils.copyListProperties(BookVO.class, pos);
		
		// 设置列表
		listResponse.setContent(vos);
		
		return new BasicResponseGenericityVO<BasicListVO<BookVO>>(200, "OK", listResponse);
	}

}