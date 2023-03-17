package com.huang.order.dao;

import com.huang.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:51:50
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

}
