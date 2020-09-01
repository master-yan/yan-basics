package com.yan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * vo - 通用返回对象
 * @author fenrir
 */
@ApiModel(value = "基础返回值", description = "这是api的基础返回值, 业务的返回值都会放到data中")
@Data
public class BasicResponseVO {

    @ApiModelProperty(value = "响应编号", required = true, example = "200")
    private int code;

    @ApiModelProperty(value = "响应说明", required = true, example = "OK")
    private String message;

    @ApiModelProperty(value = "业务返回值", required = false)
    private Object data;

    public BasicResponseVO() {
		super();
	}
    
	public BasicResponseVO(Object data) {
		super();
		this.code = 200;
		this.message = "OK";
		this.data = data;
	}

	public BasicResponseVO(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
    
}