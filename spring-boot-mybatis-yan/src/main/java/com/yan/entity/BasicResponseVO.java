package com.yan.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * vo - 通用返回对象
 * @author master-yan
 */
@Data
public class BasicResponseVO implements Serializable {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -202009091023L;
	
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