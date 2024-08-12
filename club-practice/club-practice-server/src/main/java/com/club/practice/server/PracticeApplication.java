package com.club.practice.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 练习微服务启动类
 * @author Tellsea
 * @date 2024−08−07
 */
@SpringBootApplication
@ComponentScan("com.club")
@MapperScan("com.club.**.dao")
@EnableFeignClients(basePackages = "com.jingdianjichi")
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class);
    }
}
