package com.huang.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *
 * @author Yidan Huang
 * @date 2023/03/17
 */
@MapperScan("com.huang.product.dao")
@SpringBootApplication
public class HuangProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangProductApplication.class, args);
    }

}
