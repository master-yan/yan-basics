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
 * po - 书本
 * @author fenrir
 *
 */
@Data
@Entity
@Table(name = "test_book")
public class BookPO {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * 书名
	 */
	@Column(name = "book_name")
	private String bookName;
	
	/**
	 * 作者
	 */
	@Column(name = "author")
	private String author;
	
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