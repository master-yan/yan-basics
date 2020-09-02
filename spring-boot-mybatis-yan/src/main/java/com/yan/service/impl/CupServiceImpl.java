package com.yan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.dao.CupDao;
import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.CupPO;
import com.yan.entity.CupVO;
import com.yan.service.CupService;
import com.yan.util.Utils;

/**
 * service - 杯子管理
 * @author master-yan
 *
 */
@Service
public class CupServiceImpl implements CupService {

	@Autowired
	private CupDao cupDao;
	
	@Override
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<CupVO>> queryAll(Integer pageNumber,
			Integer pageSize) {
		// 查询总条数
		long total = cupDao.queryAllTotal();
		
		// 构造分页返回值
		BasicListResponseGenericityVO<CupVO> listResponse = Utils.<CupVO>getBasicListResponseGenericityVO(pageNumber, pageSize, total);
		
		// 查询数据, 使用处理过的分页参数
		List<CupPO> pos = cupDao.queryAll(listResponse.getPageIndex(), listResponse.getPageSize());
		
		// 对象转换
		List<CupVO> vos = Utils.copyListProperties(CupVO.class, pos);
		
		// 设置列表
		listResponse.setContent(vos);
		
		return new BasicResponseGenericityVO<BasicListResponseGenericityVO<CupVO>>(200, "OK", listResponse);
	}

	@Transactional
	@Override
	public BasicResponseVO add(CupVO vo) {
		CupPO po = new CupPO();
		
		// 转换模型
		BeanUtils.copyProperties(vo, po);
		
		// 保存数据
		int row = cupDao.add(po);
		
		return new BasicResponseVO(200, "OK", row);
	}
	
	@Transactional
	@Override
	public BasicResponseVO batchAdd(List<CupVO> vos) {
		// 对象转换
		List<CupPO> pos = Utils.copyListProperties(CupPO.class, vos);
		
		// 保存数据
		int row = cupDao.batchAdd(pos);
		
		return new BasicResponseVO(200, "OK", row);
	}

	@Transactional
	@Override
	public BasicResponseVO update(CupVO vo) {
		CupPO po = new CupPO();
		
		// 转换模型
		BeanUtils.copyProperties(vo, po);
		
		// 修改数据
		int row = cupDao.update(po);
		
		return new BasicResponseVO(200, "OK", row);
	}
	
	@Transactional
	@Override
	public BasicResponseVO batchUpdate(List<CupVO> vos) {
		// 对象转换
		List<CupPO> pos = Utils.copyListProperties(CupPO.class, vos);
		
		// 保存数据
		int row = cupDao.batchUpdate(pos);
		
		return new BasicResponseVO(200, "OK", row);
	}

	@Transactional
	@Override
	public BasicResponseVO deleteById(Integer id) {
		// 删除数据
		int row = cupDao.deleteById(id);
		
		return new BasicResponseVO(200, "OK", row);
	}

}