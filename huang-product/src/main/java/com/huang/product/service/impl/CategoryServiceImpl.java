package com.huang.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.Query;

import com.huang.product.dao.CategoryDao;
import com.huang.product.entity.CategoryEntity;
import com.huang.product.service.CategoryService;

import javax.annotation.Resource;


/**
 *
 *
 * @author Yidan Huang
 * @date 2023/03/17
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }
    /**
     * @return {@code R }
     * @description: 查出树形列表
     * @author 黄熠丹 221231005
     * @date 2023/03/17
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntities = list();
        return categoryEntities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, categoryEntities)))
                .sorted(Comparator.comparing(CategoryEntity::getSort,Comparator.nullsFirst(Integer::compareTo)))
                .collect(Collectors.toList());
    }
    /**
     * @param categoryEntity   类别实体
     * @param categoryEntities 类别实体
     * @return {@code List<CategoryEntity> }
     * @description: 获取子分类
     * @author 黄熠丹 221231005
     * @date 2023/03/17
     */
    private List<CategoryEntity> getChildren(CategoryEntity categoryEntity, List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .filter(subCategoryEntity -> subCategoryEntity.getParentCid().equals(categoryEntity.getCatId()))
                .peek(subCategoryEntity -> subCategoryEntity.setChildren(getChildren(subCategoryEntity, categoryEntities)))
                .sorted(Comparator.comparing(CategoryEntity::getSort,Comparator.nullsFirst(Integer::compareTo)))
                .collect(Collectors.toList());
    }


}
