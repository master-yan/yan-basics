package com.yan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.CupVO;

/**
 * service - 杯子管理
 * @author fenrir
 *
 */
@Service
public interface CupService {

	/**
	 * 查询书本
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<CupVO>> queryAll(Integer pageNumber, Integer pageSize);
	
	/**
	 * 新增书本
	 * @param vo
	 * @return
	 */
	public BasicResponseVO add(CupVO vo);
	
	/**
	 * 批量新增书本
	 * @param vos
	 * @return
	 */
	public BasicResponseVO batchAdd(List<CupVO> vos);
	
	/**
	 * 修改书本
	 * @param vo
	 * @return
	 */
	public BasicResponseVO update(CupVO vo);
	
	/**
	 * 批量修改书本
	 * @param vos
	 * @return
	 */
	public BasicResponseVO batchUpdate(List<CupVO> vos);
	
	/**
	 * 根据id删除书本
	 * @param vo
	 * @return
	 */
	public BasicResponseVO deleteById(Integer id);
	
}