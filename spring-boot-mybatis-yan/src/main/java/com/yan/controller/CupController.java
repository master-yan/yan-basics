package com.yan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yan.config.ValidationGroup;
import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.CupVO;
import com.yan.service.CupService;

/**
 * controller - 杯子管理
 * @author master-yan
 *
 */
@RestController
public class CupController {

	@Autowired
	private CupService cupService;
	
	/**
	 * 查询杯子
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping(
		value = "/cup/queryAll/{pageNumber:[0-9]\\\\\\\\d*}/{pageSize:[0-9]\\\\\\\\d*}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<CupVO>> selectAll(
		@PathVariable(value = "pageNumber") Integer pageNumber, 
		@PathVariable(value = "pageSize") Integer pageSize
	) {
		return cupService.queryAll(pageNumber, pageSize);
	}
	
	/**
	 * 新增杯子
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/cup/add", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO add(@Validated(ValidationGroup.Add.class)@Valid @RequestBody CupVO vo) {
		return cupService.add(vo);
	}
	
	/**
	 * 批量新增杯子
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/cup/batchAdd", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO batchAdd(@Validated(ValidationGroup.Add.class)@Valid @RequestBody List<CupVO> vos) {
		return cupService.batchAdd(vos);
	}
	
	/**
	 * 修改杯子
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/cup/update", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO update(@Validated(ValidationGroup.Update.class)@Valid @RequestBody CupVO vo) {
		return cupService.update(vo);
	}
	
	/**
	 * 批量修改杯子
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/cup/batchUpdate", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO batchUpdate(@Validated(ValidationGroup.Update.class)@Valid @RequestBody List<CupVO> vos) {
		return cupService.batchUpdate(vos);
	}
	
	/**
	 * 根据id删除杯子
	 * @param vo
	 * @return
	 */
	@GetMapping(
		value = "/cup/deleteById/{id:[0-9a-z]{32}}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO deleteById(@PathVariable(value = "id") Integer id) {
		return cupService.deleteById(id);
	}
	
}