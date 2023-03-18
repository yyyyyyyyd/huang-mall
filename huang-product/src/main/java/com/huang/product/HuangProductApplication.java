package com.huang.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 *
 * @author Yidan Huang
 * @date 2023/03/17
 */
@MapperScan("com.huang.product.dao")
@ComponentScan("com.huang")
@EnableFeignClients("com.huang.product.feigin")
@SpringBootApplication
public class HuangProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangProductApplication.class, args);
    }

}
