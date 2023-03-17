package com.huang.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.member.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 10:13:15
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

