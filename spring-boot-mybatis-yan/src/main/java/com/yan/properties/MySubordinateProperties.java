package com.yan.properties;

import lombok.Data;

/**
 * @ConfigurationProperties子属性测试
 * @author master-yan
 *
 */
@Data
public class MySubordinateProperties {

	private int code = 404;
	
	private String msg = "找不到页面";
	
}