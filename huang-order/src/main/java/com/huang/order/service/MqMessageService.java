package com.huang.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.order.entity.MqMessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:51:49
 */
public interface MqMessageService extends IService<MqMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

