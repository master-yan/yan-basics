package com.yan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * application - spring boot启动类
 * @SpringBootApplication - spring boot启动配置
 * @EnableCaching - spring boot开启缓存
 * @MapperScan - mybatis扫描配置
 * @author master-yan
 *
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.yan.dao")
public class SpringBootMybatisYanApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisYanApplication.class, args);
    }
	
}