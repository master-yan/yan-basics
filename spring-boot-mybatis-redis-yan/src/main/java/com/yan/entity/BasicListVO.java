package com.yan.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vo - 列表内容
 * @author master-yan
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicListVO<T> implements Serializable {
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -202009091021L;

	/**
	 * 列表内容
	 */
	private List<T> content;

}