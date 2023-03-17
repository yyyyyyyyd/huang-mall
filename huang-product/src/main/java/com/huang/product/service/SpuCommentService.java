package com.huang.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:07:06
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

