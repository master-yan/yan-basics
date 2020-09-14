package com.yan.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * properties配置
 * 从properties文件里扫描值跟prefix属性前缀一样的配置，名字匹配的属性时使用文件里的，没有则用class里面的默认值
 * 注：properties文件默认用的ISO_8859_1编码，中文会乱码，唯一的解决方法是把中文转ASCII码，其他方式都无法起效
 * @Component spring配置，把该class设置为spring的组件（不是bean，所以不会放到bean容器）
 * @Data lombok注解，自动生成getset方法
 * @author master-yan
 *
 */
@ConfigurationProperties(prefix = "yan.test")
@Component
@Data
public class MyProperties {

	private String testString = "字符串测试";
	
	private int testInt = 666;
	
	private PropertiesTestEnum propertiesTestEnum = PropertiesTestEnum.ONE;
	
	private MySubordinateProperties mySubordinateProperties;
	
}