package com.yan.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vo - 基础的列表响应(泛型)
 * @author master-yan
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicListResponseGenericityVO <T> implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -202009091019L;

	/**
	 * 列表内容
	 */
	private List<T> content;

	/**
	 * 当前页(从1开始)
	 */
	private int pageNumber;

	/**
	 * 页行数
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private int totalPage;
    
	/**
	 * 当前页下标
	 */
	private int pageIndex;

	/**
	 * 总行数
	 */
	private long total;

}