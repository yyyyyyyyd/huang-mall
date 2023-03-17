package com.huang.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:58:11
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

