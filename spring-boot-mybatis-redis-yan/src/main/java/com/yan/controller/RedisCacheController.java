package com.yan.controller;

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
import com.yan.entity.SweetsVO;
import com.yan.service.RedisCacheService;

/**
 * controller - spring boot使用redis作为spring boot缓存demo
 * @author master-yan
 *
 */
@RestController
public class RedisCacheController {

	@Autowired
	private RedisCacheService redisCacheService;
	
	/**
	 * 查询糖果
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping(
		value = "/cache/queryAll/{pageNumber:[0-9]\\\\d*}/{pageSize:[0-9]\\\\d*}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<SweetsVO>> selectAll(
		@PathVariable(value = "pageNumber") Integer pageNumber, 
		@PathVariable(value = "pageSize") Integer pageSize
	) {
		return redisCacheService.queryAll(pageNumber, pageSize);
	}
	
	/**
	 * 新增糖果
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/cache/add", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO add(@Validated(ValidationGroup.Add.class)@Valid @RequestBody SweetsVO vo) {
		return redisCacheService.add(vo);
	}
	
	/**
	 * 修改糖果
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/cache/update", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO update(@Validated(ValidationGroup.Update.class)@Valid @RequestBody SweetsVO vo) {
		return redisCacheService.update(vo);
	}
	
	/**
	 * 根据id删除糖果
	 * @param vo
	 * @return
	 */
	@GetMapping(
		value = "/cache/deleteById/{id:[0-9a-z]{32}}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO deleteById(@PathVariable(value = "id") Integer id) {
		return redisCacheService.deleteById(id);
	}
	
}