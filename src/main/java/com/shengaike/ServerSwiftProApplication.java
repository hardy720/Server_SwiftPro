package com.shengaike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shengaike.mapper")
public class ServerSwiftProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSwiftProApplication.class, args);
    }

}
