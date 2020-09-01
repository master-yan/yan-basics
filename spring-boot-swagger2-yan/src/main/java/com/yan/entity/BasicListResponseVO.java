package com.yan.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vo - 基础的列表响应
 * @author fenrir
 */
@ApiModel(value = "基础列表", description = "这是查询列表的固定返回值")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicListResponseVO {

    @ApiModelProperty(value = "列表内容", required = false)
    private List<?> content;

    @ApiModelProperty(value = "当前页(从0开始)", required = true, example = "0")
    private int pageNumber;

    @ApiModelProperty(value = "页行数", required = true, example = "10")
    private int pageSize;

    @ApiModelProperty(value = "总页数", required = true, example = "11")
    private int totalPage;
    
    @ApiModelProperty(value = "当前页下标", required = true, example = "0")
    private int pageIndex;

    @ApiModelProperty(value = "总行数", required = true, example = "100")
    private long total;

}