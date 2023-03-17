package com.huang.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 10:13:14
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

