package com.yan.entity;

import lombok.Data;

/**
 * vo - 通用返回对象(泛型)
 * @author master-yan
 */
@Data
public class BasicResponseGenericityVO <T> {

    /**
     * 响应编号
     */
    private int code;

    /**
     * 响应说明
     */
    private String message;

    /**
     * 业务返回值
     */
    private T data;
    
    public BasicResponseGenericityVO() {
		super();
	}
    
	public BasicResponseGenericityVO(T data) {
		super();
		this.code = 200;
		this.message = "OK";
		this.data = data;
	}

	public BasicResponseGenericityVO(int code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
    
}