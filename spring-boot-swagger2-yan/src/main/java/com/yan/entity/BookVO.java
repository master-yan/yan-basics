package com.yan.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vo - 书本
 * @author fenrir
 *
 */
@ApiModel(value = "书本vo", description = "书本vo-后端用来跟前端交互的json模型")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookVO {

	@ApiModelProperty(value = "id", required = true, example = "65465465")
	private Integer id;
	
	@ApiModelProperty(value = "书名", required = true, example = "程序员的自我修养")
	private String bookName;
	
	@ApiModelProperty(value = "作者", required = true, example = "陈逸鹤")
	private String author;
	
	@ApiModelProperty(value = "总数量(册)", required = true, example = "100000")
	private Integer total;
	
	@ApiModelProperty(value = "售价", required = true, example = "99.99")
	private BigDecimal price;
	
	@ApiModelProperty(value = "介绍", required = true, example = "《程序员的自我修养》是2017年清华大学出版社出版的图书，作者是陈逸鹤。")
	private String introduction;
	
	@ApiModelProperty(value = "创建时间", required = false, example = "2017-05-10 14:44:10")
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
	private Timestamp creationTime;
	
	@ApiModelProperty(value = "更新时间", required = false, example = "2017-05-10 14:44:10")
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
	private Timestamp updatedTime;
	
}