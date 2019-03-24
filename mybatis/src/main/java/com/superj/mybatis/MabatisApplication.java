package com.superj.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.superj.mybatis.dao")
public class MabatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MabatisApplication.class, args);
    }

}
