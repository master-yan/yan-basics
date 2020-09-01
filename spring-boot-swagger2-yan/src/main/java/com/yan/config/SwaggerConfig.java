package com.yan.config;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 api文档配置
 * @Configuration 设置为spring配置
 * @ConditionalOnProperty spring条件配置，条件成立时，spring配置才会起作用
 * 	name用来从spring容器中找bean
 * 	havingValue比较获取到的属性值与havingValue给定的值是否相同,相同才加载配置
 * @EnableSwagger2 设置为swagger2配置
 * @EnableKnife4j 设置为knife4j配置
 */
@Configuration
@ConditionalOnProperty(name = "swagger.show", havingValue = "true")
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
	
	/**
	 * 创建api
	 * @return 
	 */
	@Bean
	public Docket fenrirApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 构建api信息
				.apiInfo(
					new ApiInfoBuilder()
					// api版本
					.version("1.0.0")
					// api标题
	                .title("spring boot swagger2 knife4j demo")
	                // api描述
	                .description(
                		"这是master-yan基于spring boot集成swagger2，knife4j的demo。" + 
        				"用于生成后端项目的接口文档。"
            		)
	                // 服务条款地址
	                .termsOfServiceUrl("https://www.yan.com")
	                // 联系方式
	                .contact(new Contact("master-yan", "master-yan的博客", "1091678409@qq.com"))
	                // 执照,许可证
	                .license("这里是许可证的地址")
	                .build()
				)
				// api地址
				.host("localhost:6001")
				// 标签,可变参方法,目前不明白用途
//				.tags(
//					new Tag("这是第1个标签", "这是第1个标签的描述"), 
//					new Tag("这是第2个标签", "这是第2个标签的描述"), 
//					new Tag("这是第3个标签", "这是第3个标签的描述")
//				)
				// 选择哪些路径和api会生成document
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors.any())
				// 错误路径不监控
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.paths(Predicates.not(PathSelectors.regex("/actuator.*")))
				// 监控跟目录下所有路径
				.paths(PathSelectors.regex("/.*"))
				.build()
				.directModelSubstitute(Timestamp.class, Date.class);
				
	}
	
}