package com.huang.product.dao;

import com.huang.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:07:07
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {

}
