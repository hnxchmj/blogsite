package com.xiaofei.blogsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaofei.blogsite.dao")
public class BlogsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogsiteApplication.class, args);
    }
}
