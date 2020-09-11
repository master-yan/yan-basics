package com.yan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * api拦截-切片： 自定义切片
 * 过滤器可以拿到真正处理请求的方法的参数表，但是拿不到原始的http请求和响应对象
 * 拦截的优先级：Filter > Interceptor > ControllerAdvice > Aspect > Controller
 * @Aspect spring配置，设置该class为一个切片
 * @Component spring配置，把该class设置为spring的组件（不是bean，所以不会放到bean容器）
 * @author master-yan
 *
 */
@Aspect
@Component
public class MyAspect {

	/**
	 * @Arounds spring配置，设置切入点（包围，在切入点之前之后以及发生异常时执行）
	 * 第一个*指任何返回值，第二个星指任何方法，第一个..指任何参数
	 * 注解称为切入点，而方法被称为增强
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable 
	 */
	@Around("execution(* com.yan.controller.BookController.*(..))")
	public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint) {
		System.out.println("around切片执行开始");
		
		// 获取参数表
		Object[] args = proceedingJoinPoint.getArgs();
		
		for (Object object : args) {
			System.out.println("arg is " + object);
		}
		
		// 获取返回值
		Object proceed;
		try {
			proceed = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			proceed = e;

			System.out.println("around切片捕获到了异常");
			
			e.printStackTrace();
		}
		
		System.out.println("around切片执行结束");
		
		return proceed;
	}

	/**
	 * @Before spring配置，设置切入点（在切入点之前执行）
	 * 第一个*指任何返回值，第二个星指任何方法，第一个..指任何参数
	 * 注解称为切入点，而方法被称为增强
	 * @param joinPoint
	 * @return
	 * @throws Throwable 
	 */
	@Before("execution(* com.yan.controller.CacheController.*(..))")
	public void beforeTest(JoinPoint joinPoint) throws Throwable {
		System.out.println("before切片执行开始");
		
		// 获取参数表
		Object[] args = joinPoint.getArgs();
		
		for (Object object : args) {
			System.out.println("arg is " + object);
		}

		System.out.println("before切片执行结束");
	}
	
	/**
	 * @After spring配置，设置切入点（在切入点之后执行）
	 * 第一个*指任何返回值，第二个星指任何方法，第一个..指任何参数
	 * 注解称为切入点，而方法被称为增强
	 * @param joinPoint
	 * @return
	 * @throws Throwable 
	 */
	@After("execution(* com.yan.controller.CacheController.*(..))")
	public void afterTest(JoinPoint joinPoint) throws Throwable {
		System.out.println("after切片执行开始");
		
		// 获取参数表
		Object[] args = joinPoint.getArgs();
		
		for (Object object : args) {
			System.out.println("arg is " + object);
		}
		
		// 获取返回值
		System.out.println("after切片执行结束");
	}
	
	/**
	 * @AfterThrowing spring配置，设置切入点（出现异常时执行）
	 * 第一个*指任何返回值，第二个星指任何方法，第一个..指任何参数
	 * 注解称为切入点，而方法被称为增强
	 * @param joinPoint
	 * @return
	 * @throws Throwable 
	 */
	@AfterThrowing("execution(* com.yan.controller.CacheController.*(..))")
	public void afterThrowingTest(JoinPoint joinPoint) {
		System.out.println("afterThrowing切片执行开始");
		
		// 获取参数表
		Object[] args = joinPoint.getArgs();
		
		for (Object object : args) {
			System.out.println("arg is " + object);
		}
		
		System.out.println("afterThrowing切片执行结束");
	}
	
}