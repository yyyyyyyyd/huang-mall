package com.huang.product.dao;

import com.huang.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:07:06
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
