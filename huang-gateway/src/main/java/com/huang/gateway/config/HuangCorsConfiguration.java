package com.huang.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.netty.http.server.HttpServerResponse;

import javax.annotation.Resource;

/**
 * 跨域配置
 *
 * @author Yidan Huang
 * @date 2023/03/17
 */
@Configuration
public class HuangCorsConfiguration {



    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 配置跨域
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许哪个请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许哪个方法进行跨域
        corsConfiguration.addAllowedMethod("*");
        // 允许哪个请求来源进行跨域
        // corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOriginPattern("*");
        // 是否允许携带cookie进行跨域
        corsConfiguration.setAllowCredentials(true);
        // 设置跨域时长
        corsConfiguration.setMaxAge(3600L);

        source.registerCorsConfiguration("/**",corsConfiguration);

        return new CorsWebFilter(source);
    }

}
