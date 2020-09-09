package com.yan.service;

import org.springframework.stereotype.Service;

import com.yan.entity.BasicListVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BookVO;

/**
 * service - 书本管理（spring boot cache使用demo）
 * @author master-yan
 *
 */
@Service
public interface CacheService {

	/**
	 * 查询书本
	 * @param bookName
	 * @param condition
	 * @param ndition
	 * @return
	 */
	public BasicResponseGenericityVO<BasicListVO<BookVO>> cacheable(String bookName);
	
}