package com.huang.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.huang.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class HuangMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangMemberApplication.class, args);
    }

}
