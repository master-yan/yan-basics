package com.yan.entity;

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
public class BasicListVO<T> {

	/**
	 * 列表内容
	 */
	private List<T> content;

}