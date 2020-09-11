package com.yan.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	/**
	 * 查询书本
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping(
		value = "/test/getTest", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public Object getTest() {
		return "{\"code\":200, \"msg\":\"OK\"}";
	}
	
}