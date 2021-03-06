package com.yan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yan.config.ValidationGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vo - 书本
 * @author master-yan
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookVO implements Serializable {
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -202009091024L;

	/**
	 * id
	 */
	@NotNull(message = "修改操作时, id不能为空", groups = ValidationGroup.Update.class)
	private Integer id;
	
	/**
	 * 书名
	 */
	private String bookName;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 总数量(册)
	 */
	@Min(value = 1, message = "销售状态最小值为1")
	@Max(value = 1000, message = "销售状态最大值为1000")
	private Integer total;
	
	/**
	 * 售价
	 */
	private BigDecimal price;
	
	/**
	 * 介绍
	 */
	private String introduction;
	
	/**
	 * 创建时间
	 * @JsonFormat fastjson的时间格式设置，application的配置不起作用时可用该方式解决
	 */
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
	private Timestamp creationTime;
	
	/**
	 * 更新时间
	 * @JsonFormat fastjson的时间格式设置，application的配置不起作用时可用该方式解决
	 */
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
	private Timestamp updatedTime;
	
}