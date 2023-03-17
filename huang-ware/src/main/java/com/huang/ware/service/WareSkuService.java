package com.huang.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 10:15:41
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

