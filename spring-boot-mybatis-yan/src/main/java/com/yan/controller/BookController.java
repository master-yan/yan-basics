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
import com.yan.entity.BookVO;
import com.yan.service.BookService;

/**
 * controller - 书本管理
 * @author master-yan
 *
 */
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	/**
	 * 查询书本
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping(
		value = "/book/queryAll/{pageNumber:[0-9]\\\\d*}/{pageSize:[0-9]\\\\d*}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseGenericityVO<BasicListResponseGenericityVO<BookVO>> selectAll(
		@PathVariable(value = "pageNumber") Integer pageNumber, 
		@PathVariable(value = "pageSize") Integer pageSize
	) {
		return bookService.queryAll(pageNumber, pageSize);
	}
	
	/**
	 * 新增书本
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/book/add", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO add(@Validated(ValidationGroup.Add.class)@Valid @RequestBody BookVO vo) {
		return bookService.add(vo);
	}
	
	/**
	 * 批量新增书本
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/book/batchAdd", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO batchAdd(@Validated(ValidationGroup.Add.class)@Valid @RequestBody List<BookVO> vos) {
		return bookService.batchAdd(vos);
	}
	
	/**
	 * 修改书本
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/book/update", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO update(@Validated(ValidationGroup.Update.class)@Valid @RequestBody BookVO vo) {
		return bookService.update(vo);
	}
	
	/**
	 * 批量修改书本
	 * @param vo
	 * @return
	 */
	@PostMapping(
		value = "/book/batchUpdate", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO batchUpdate(@Validated(ValidationGroup.Update.class)@Valid @RequestBody List<BookVO> vos) {
		return bookService.batchUpdate(vos);
	}
	
	/**
	 * 根据id删除书本
	 * @param vo
	 * @return
	 */
	@GetMapping(
		value = "/book/deleteById/{id:[0-9a-z]{32}}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO deleteById(@PathVariable(value = "id") Integer id) {
		return bookService.deleteById(id);
	}
	
}