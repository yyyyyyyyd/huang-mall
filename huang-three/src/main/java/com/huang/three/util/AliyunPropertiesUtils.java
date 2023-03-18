package com.huang.three.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@Component
// 常量类，读取配置文件application.properties中的配置
public class AliyunPropertiesUtils implements InitializingBean {
    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;
    @Value("${alibaba.cloud.access-key}")
    private String keyId;
    @Value("${alibaba.cloud.secret-key}")
    private String keySecret;
    @Value("${alibaba.cloud.bucketname}")
    private String bucketName;
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;


    @Override

    public void afterPropertiesSet() throws Exception {

        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;


    }
}
