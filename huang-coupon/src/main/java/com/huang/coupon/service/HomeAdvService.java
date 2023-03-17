package com.huang.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.coupon.entity.HomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:58:12
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

