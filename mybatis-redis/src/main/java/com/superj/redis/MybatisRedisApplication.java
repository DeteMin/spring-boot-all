package com.superj.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.superj.redis.dao")
public class MybatisRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisRedisApplication.class, args);
    }

}
