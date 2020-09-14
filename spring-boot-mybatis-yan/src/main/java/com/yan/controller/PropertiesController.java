package com.yan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yan.properties.MyProperties;

@RestController
public class PropertiesController {

	@Autowired
	private MyProperties myProperties;
	
	@Value(value = "${yan.test.testString}")
	private String testString;
	
	/**
	 * 获取自定义properties的值
	 * @return
	 */
	@GetMapping(
		value = "/properties/getMyProperties", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public Object getMyProperties() {
		return myProperties;
	}
	
	/**
	 * 获取自定义properties的其中一个值
	 * @return
	 */
	@GetMapping(
		value = "/properties/getPropertiesValue", 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public Object getPropertiesValue() {
		return testString;
	}
	
}