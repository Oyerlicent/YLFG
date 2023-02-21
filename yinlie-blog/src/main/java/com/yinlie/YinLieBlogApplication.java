package com.yinlie;

import org.mapstruct.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Oyerlicent
 * @create 2022-12-04 23:15
 **/
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class YinLieBlogApplication {
    public static void main(String[] args){
        SpringApplication.run(YinLieBlogApplication.class,args);
    }
}
