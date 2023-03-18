package com.huang.three.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.huang.three.service.OssService;
import com.huang.three.util.AliyunPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * oss服务impl
 *
 * @author Yidan Huang
 * @date 2023/02/21
 */
@Service
public class OssServiceImpl implements OssService {

    @Resource
    OSS ossClient;

    @Resource
    AliyunPropertiesUtils aliyunPropertiesUtils;





    /**
     * @param file 文件
     * @return {@code String }
     * @description: 上传头像到oss
     * @author 黄熠丹 221231005
     * @date 2023/02/21
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String bucketName = aliyunPropertiesUtils.getBucketName();
        String endpoint = aliyunPropertiesUtils.getEndpoint();
        String accessKeyId = aliyunPropertiesUtils.getKeyId();
        String accessKeySecret = aliyunPropertiesUtils.getKeySecret();
        //工具类获取值
//        String endpoint = AliyunPropertiesUtils.END_POINT;
//        String accessKeyId = AliyunPropertiesUtils.ACCESS_KEY_ID;
//        String accessKeySecret = AliyunPropertiesUtils.ACCESS_KEY_SECRET;
//        String backetName = AliyunPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名
            String filename = file.getOriginalFilename();
            //在文件名称里添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid + filename;

            //把文件按日期分类
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //第一个参数 Backet名称
            //第二个参数 上传到oss文件路径和文件名称
            //第三个参数 上传文件输入流

            //拼接路径
            filename = datePath + "/" + filename;
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //需要把上传文件到阿里云的路径手动拼接出来
            // https://gulischool-dyk.oss-cn-beijing.aliyuncs.com/1.png

            return "https://" + bucketName + "." + endpoint + "/" + filename;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    @Override
    public Map policy() {
        String bucketName = aliyunPropertiesUtils.getBucketName();
        String endpoint = aliyunPropertiesUtils.getEndpoint();
        String accessKeyId = aliyunPropertiesUtils.getKeyId();
        String accessKeySecret = aliyunPropertiesUtils.getKeySecret();
        //https://gulimall-hello.oss-cn-beijing.aliyuncs.com/hahaha.jpg

        String host = "https://" + bucketName + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://88.88.88.88:8888";
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/"; // 用户上传文件时指定的前缀。

        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }
        return respMap;
    }
}
