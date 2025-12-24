package com.huike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huike.mapper")
public class RbacApplication {

    //templates文件夹下的资源是受保护的，无法通过浏览器直接访问
    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class, args);
    }

}
