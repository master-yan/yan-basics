package com.yan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.dao.BookDao;
import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.BookPO;
import com.yan.entity.BookVO;
import com.yan.service.BookService;
import com.yan.util.Utils;

/**
 * service - 书本管理
 * @author fenrir
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<BookVO>> queryAll(
		Integer pageNumber, Integer pageSize
	) {
		// 查询总条数
		long total = bookDao.queryAllTotal();
		
		// 构造分页返回值
		BasicListResponseGenericityVO<BookVO> listResponse = Utils.<BookVO>getBasicListResponseGenericityVO(pageNumber, pageSize, total);
		
		// 查询数据, 使用处理过的分页参数
		List<BookPO> pos = bookDao.queryAll(listResponse.getPageIndex(), listResponse.getPageSize());
		
		// 对象转换
		List<BookVO> vos = Utils.copyListProperties(BookVO.class, pos);
		
		// 设置列表
		listResponse.setContent(vos);
		
		return new BasicResponseGenericityVO<BasicListResponseGenericityVO<BookVO>>(200, "OK", listResponse);
	}

	@Transactional
	@Override
	public BasicResponseVO add(BookVO vo) {
		BookPO po = new BookPO();
		
		// 转换模型
		BeanUtils.copyProperties(vo, po);
		
		// 保存数据
		int row = bookDao.add(po);
		
		return new BasicResponseVO(200, "OK", row);
	}
	
	@Transactional
	@Override
	public BasicResponseVO batchAdd(List<BookVO> vos) {
		// 对象转换
		List<BookPO> pos = Utils.copyListProperties(BookPO.class, vos);
		
		// 保存数据
		int row = bookDao.batchAdd(pos);
		
		return new BasicResponseVO(200, "OK", row);
	}

	@Transactional
	@Override
	public BasicResponseVO update(BookVO vo) {
		BookPO po = new BookPO();
		
		// 转换模型
		BeanUtils.copyProperties(vo, po);
		
		// 修改数据
		int row = bookDao.update(po);
		
		return new BasicResponseVO(200, "OK", row);
	}
	
	@Transactional
	@Override
	public BasicResponseVO batchUpdate(List<BookVO> vos) {
		// 对象转换
		List<BookPO> pos = Utils.copyListProperties(BookPO.class, vos);
		
		// 保存数据
		int row = bookDao.batchUpdate(pos);
		
		return new BasicResponseVO(200, "OK", row);
	}

	@Transactional
	@Override
	public BasicResponseVO deleteById(Integer id) {
		// 删除数据
		int row = bookDao.deleteById(id);
		
		return new BasicResponseVO(200, "OK", row);
	}

}