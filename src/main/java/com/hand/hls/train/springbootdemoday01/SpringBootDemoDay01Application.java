package com.hand.hls.train.springbootdemoday01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hand.hls.train.springbootdemoday01.mapper")
public class SpringBootDemoDay01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoDay01Application.class, args);
    }

}
