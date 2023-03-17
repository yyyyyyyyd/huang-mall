package com.huang.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:58:10
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

