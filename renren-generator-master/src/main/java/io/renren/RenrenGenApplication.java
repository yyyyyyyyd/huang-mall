package io.renren;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 *
 *
 * @author Yidan Huang
 * @date 2023/03/17
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@MapperScan("io.renren.dao")
public class RenrenGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenrenGenApplication.class, args);
	}
}
