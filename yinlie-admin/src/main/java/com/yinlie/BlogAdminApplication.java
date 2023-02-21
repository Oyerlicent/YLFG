package com.yinlie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Oyerlicent
 * @create 2023-02-09 21:50
 **/
@SpringBootApplication
@MapperScan("com.yinlie.mapper")
public class BlogAdminApplication {
    public static void main(String[] args){
        SpringApplication.run(BlogAdminApplication.class,args);
    }
}
