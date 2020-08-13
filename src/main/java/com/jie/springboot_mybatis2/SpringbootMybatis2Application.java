package com.jie.springboot_mybatis2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootMybatis2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatis2Application.class, args);
    }

}
