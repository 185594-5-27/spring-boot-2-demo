package com.sb2.demo.common.config.mybatis;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/*
* 类描述：开启mybatis的支持
* @auther linzf
* @create 2017/9/25 0025 
*/
@Configuration
@MapperScan("com.sb2.demo.*.dao")
public class MyBatisConfig {

}