package com.yan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * api拦截-拦截器： 自定义拦截器
 * 拦截器既可以拿到原始的http请求和响应也可以拿到真正处理这个请求的方法的信息，但是拿不到调用真正处理的方法的参数表
 * 拦截的优先级：Filter > Interceptor > ControllerAdvice > Aspect > Controller
 * @Component spring配置，把该class设置为spring的组件（不是bean，所以不会放到bean容器）
 * @author master-yan
 *
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

	/**
	 * 前置处理-controller被访问前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("自定义拦截器-执行preHandle");
		
		System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
		
		System.out.println(((HandlerMethod) handler).getMethod().getName());
		
		// 拦截器可以传值，过滤器不可以
		request.setAttribute("开始时间", System.currentTimeMillis());
		
		return true;
	}
	
	/**
	 * 后置处理-controller调用完成后执行（异常时不执行）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("自定义拦截器-开始执行postHandle");
		
		long endTime = System.currentTimeMillis() - Long.valueOf(request.getAttribute("开始时间").toString());
		
		System.out.println("自定义拦截器-postHandle执行完毕:" + endTime);
	}
	
	/**
	 * 后置处理-controller调用完成后执行（不管是否发生异常）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("自定义拦截器-开始执行afterCompletion");
		
		long endTime = System.currentTimeMillis() - Long.valueOf(request.getAttribute("开始时间").toString());
		
		System.out.println("自定义拦截器-afterCompletion执行完毕:" + endTime);
	}
	
}