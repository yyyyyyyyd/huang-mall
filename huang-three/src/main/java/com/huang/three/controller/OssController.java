package com.huang.three.controller;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.huang.common.utils.R;

import com.huang.three.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * oss控制器
 *
 * @author Yidan Huang
 * @date 2023/02/21
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    private OssService ossService;

    /**
     * @param file 文件
     * @return {@code ResultVo }
     * @description: 上传头像的方法
     * @author 黄熠丹 221231005
     * @date 2023/02/21
     */
    @PostMapping("/upload")
    public R uploadOssFile( MultipartFile file) {
        //获取上传文件 MultipartFile
        //返回上传路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok("文件上传成功").put("url", url);
    }

    @GetMapping("/policy")
    public R policy(String id) {
        Map respMap = ossService.policy();
        return R.ok().put("data",respMap);
    }
}
