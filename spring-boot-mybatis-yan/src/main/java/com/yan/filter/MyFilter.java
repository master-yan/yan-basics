package com.yan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * api拦截-过滤器： 自定义过滤器
 * 过滤器可以拿到原始的http请求和响应，但是拿不到真正处理这个请求的方法的信息
 * 拦截的优先级：Filter > Interceptor > ControllerAdvice > Aspect > Controller
 * @Component spring配置，把该class设置为spring的组件（不是bean，所以不会放到bean容器）
 * @author master-yan
 *
 */
@Component
public class MyFilter implements Filter {
	
	/**
	 * 初始化过滤器-项目启动时执行
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("自定义过滤器-初始化");
	}

	/**
	 * 执行过滤-方法被调用时
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("自定义过滤器-启动");
		
		// 进入下一个过滤器
		chain.doFilter(request, response);
		
		System.out.println("自定义过滤器-退出");
	}
	
	/**
	 * 销毁过滤器-项目关闭时执行
	 */
	@Override
	public void destroy() {
		System.out.println("自定义过滤器-已销毁");
	}

}