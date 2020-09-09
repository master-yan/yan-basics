package com.yan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * po - 杯子
 * @author master-yan
 *
 */
@Data
@Entity
@Table(name = "test_cup")
public class CupPO implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -202009091027L;
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * 杯子名称
	 */
	@Column(name = "cup_name")
	private String cupName;
	
	/**
	 * 总数量
	 */
	@Column(name = "total")
	private Integer total;
	
	/**
	 * 售价
	 */
	@Column(name = "price")
	private BigDecimal price;
	
	/**
	 * 介绍
	 */
	@Column(name = "introduction")
	private String introduction;
	
	/**
	 * 创建时间
	 */
	@Column(name = "creation_time")
	private Timestamp creationTime;
	
	/**
	 * 更新时间
	 */
	@Column(name = "updated_time")
	private Timestamp updatedTime;
	
}