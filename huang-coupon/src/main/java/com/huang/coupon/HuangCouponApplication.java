package com.huang.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HuangCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangCouponApplication.class, args);
    }

}
