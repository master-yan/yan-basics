package com.yan.config;

import javax.validation.groups.Default;

/**
 * 用来做参数验证的分组配置
 * @author master-yan
 *
 */
public interface ValidationGroup {

	/**
	 * 新增分组
	 * @author fenrir
	 *
	 */
	interface Add extends Default {}
	
	/**
	 * 修改分组
	 * @author fenrir
	 *
	 */
    interface Update extends Default {}
	
}