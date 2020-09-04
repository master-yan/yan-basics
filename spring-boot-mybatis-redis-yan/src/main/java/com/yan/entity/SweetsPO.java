package com.yan.entity;

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
 * po - 糖果
 * @author master-yan
 *
 */
@Data
@Entity
@Table(name = "test_sweet")
public class SweetsPO {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * 糖果名称
	 */
	@Column(name = "sweet_name")
	private String sweetName;
	
	/**
	 * 品牌
	 */
	@Column(name = "brand")
	private String brand;
	
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