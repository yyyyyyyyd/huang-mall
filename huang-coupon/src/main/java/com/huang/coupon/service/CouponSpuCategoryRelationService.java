package com.huang.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.coupon.entity.CouponSpuCategoryRelationEntity;

import java.util.Map;

/**
 * 优惠券分类关联
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:58:11
 */
public interface CouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

