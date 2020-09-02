package com.yan.handler;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yan.entity.BasicResponseVO;

/**
 * handler - 异常处理器
 * @author master-yan
 *
 */
@ControllerAdvice
public class YanExceptionHandler {

	/**
	 * 默认异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public BasicResponseVO exceptionHandler(Exception e){
       	return new BasicResponseVO(500, "未知异常: " + e, null);
    }

	/**
	 * 非法请求参数处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public BasicResponseVO methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
		FieldError fieldError = e.getBindingResult().getFieldError();
		return new BasicResponseVO(403, fieldError.getDefaultMessage(), null);
    }

}