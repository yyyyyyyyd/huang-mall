package com.huang.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:51:50
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

