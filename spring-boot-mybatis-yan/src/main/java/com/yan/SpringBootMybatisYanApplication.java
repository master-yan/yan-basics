package com.yan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application - spring boot启动类
 * @author master-yan
 *
 */
@SpringBootApplication
@MapperScan("com.yan.dao")
public class SpringBootMybatisYanApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisYanApplication.class, args);
    }
	
}