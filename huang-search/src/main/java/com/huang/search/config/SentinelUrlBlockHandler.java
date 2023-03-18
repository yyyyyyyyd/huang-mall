package com.huang.search.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.huang.common.exception.BizCodeEnum;
import com.huang.common.utils.R;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义阻塞返回方法
 * @Created: with IntelliJ IDEA.
 * @author: 夏沫止水
 * @createTime: 2020-07-13 11:30
 **/

@Component
public class SentinelUrlBlockHandler implements BlockExceptionHandler {

    /**
     * 自定义限流返回信息
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
        // 降级业务处理
        R error = R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(error));
    }
}

//@Configuration
//public class HuangSearchSentinelConfig {
//    public class PigxUrlBlockHandler implements BlockExceptionHandler {
//
//        @Override
//        public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
//
//
//        }
//    }
//
//    public HuangSearchSentinelConfig() {
//        WebCallbackManager.setUrlBlockHandler(new PigxUrlBlockHandler() {
//            @Override
//            public void handle(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
//                R error = R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMessage());
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("application/json");
//                response.getWriter().write(JSON.toJSONString(error));
//
//            }
//        });
//
//    }
//
//}
