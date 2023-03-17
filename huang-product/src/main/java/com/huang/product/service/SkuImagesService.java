package com.huang.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:07:07
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

