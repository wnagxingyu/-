package com.example.wxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.wxy.Dao")
public class WxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxyApplication.class, args);
    }

}
