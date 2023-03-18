package com.huang.three.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件上传至阿里云
 *
 * @author Yidan Huang
 * @date 2023/02/21
 */
public interface OssService {
    /**
     * @param file 文件
     * @return {@code String }
     * @description: 文件上传至阿里云
     * @author 黄熠丹 221231005
     * @date 2023/02/21
     */
    String uploadFileAvatar(MultipartFile file);

    Map policy();

}

