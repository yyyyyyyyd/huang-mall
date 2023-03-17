package com.huang.order.dao;

import com.huang.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:51:49
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {

}
