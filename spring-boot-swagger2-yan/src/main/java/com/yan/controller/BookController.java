package com.yan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yan.entity.BasicListResponseGenericityVO;
import com.yan.entity.BasicResponseGenericityVO;
import com.yan.entity.BasicResponseVO;
import com.yan.entity.BookVO;
import com.yan.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * controller - 书本管理
 * @author master-yan
 *
 */
@Api(tags = {"书本管理模块"})
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@ApiOperation(value = "查询书本列表", notes = "查询书本列表，基于rest风格的url通过get请求，使用分页的方式查询书本列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageNumber", dataType = "int", value = "分页下标"),
        @ApiImplicitParam(name = "pageSize", dataType = "int", value = "显示行数")
    })
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
	
	@ApiOperation(value = "新增书本", notes = "新增书本，用post请求传json的方式新增书本")
	@PostMapping(
		value = "/book/add", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO add(@RequestBody BookVO vo) {
		return bookService.add(vo);
	}
	
	@ApiOperation(value = "修改书本", notes = "修改书本，用post请求传json的方式新增书本")
	@PostMapping(
		value = "/book/update", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO update(@RequestBody BookVO vo) {
		return bookService.update(vo);
	}
	
	@ApiOperation(value = "删除书本", notes = "删除书本，基于rest风格的url，使用get方式传书本id删除书本")
	@ApiImplicitParam(name = "id", dataType = "int", value = "6545645")
	@GetMapping(
		value = "/book/deleteById/{id:[0-9a-z]{32}}", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public BasicResponseVO deleteById(@PathVariable(value = "id") Integer id) {
		return bookService.deleteById(id);
	}
	
}