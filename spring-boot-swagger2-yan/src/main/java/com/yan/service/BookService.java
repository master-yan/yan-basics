package com.yan.service;

import org.springframework.stereotype.Service;

import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.BookVO;

/**
 * service - 书本管理
 * @author fenrir
 *
 */
@Service
public interface BookService {

	/**
	 * 查询书本
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<BookVO>> queryAll(Integer pageNumber, Integer pageSize);
	
	/**
	 * 新增书本
	 * @param vo
	 * @return
	 */
	public BasicResponseVO add(BookVO vo);
	
	/**
	 * 修改书本
	 * @param vo
	 * @return
	 */
	public BasicResponseVO update(BookVO vo);
	
	/**
	 * 根据id删除书本
	 * @param vo
	 * @return
	 */
	public BasicResponseVO deleteById(Integer id);
	
}