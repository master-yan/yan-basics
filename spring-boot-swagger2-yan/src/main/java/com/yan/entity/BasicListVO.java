package com.yan.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * vo - 列表内容
 * @author fenrir
 */
@ApiModel(value = "列表内容", description = "这是查询列表的固定返回值的列表内容")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicListVO<T> {

    @ApiModelProperty(value = "列表内容", required = false)
    private List<T> content;

}