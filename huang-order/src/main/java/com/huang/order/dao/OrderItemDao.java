package com.huang.order.dao;

import com.huang.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:51:50
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {

}
