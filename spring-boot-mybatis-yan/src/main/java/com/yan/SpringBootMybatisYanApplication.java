package com.yan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * application - spring boot启动类
 * @SpringBootApplication - spring boot启动配置
 * @EnableCaching - spring boot cache配置，开启缓存
 * @ComponentScan - spring boot配置，管理路径下带spring注解的class
 * @Filter - spring boot配置，@ComponentScan的子注解，用来设定过滤规则让@ComponentScan来决定如何排除和只包含
 * @ComponentScans - spring boot配置，管理多个@ComponentScan
 * @MapperScan - mybatis扫描配置
 * @author master-yan
 *
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.yan.dao")
//@ComponentScan("com.yan")
//@ComponentScans(value = {@ComponentScan("com.yan.dao"), @ComponentScan("com.yan.service")})
public class SpringBootMybatisYanApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisYanApplication.class, args);
    }

}