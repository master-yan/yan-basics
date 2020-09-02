package com.yan.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PO - redis测试对象, 羔羊
 * @author fenrir
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LambPO implements java.io.Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -1L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 羔羊名字
	 */
	private String name;
	
	/**
	 * 羔羊年龄
	 */
	private Integer age;
	
	/**
	 * 羔羊创建时间
	 */
	private Timestamp createdTime;
	
}