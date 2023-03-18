package com.huang.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.huang.product.entity.BrandEntity;
import com.huang.product.entity.CategoryEntity;
import com.huang.product.service.BrandService;
import com.huang.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.huang.product.entity.CategoryBrandRelationEntity;
import com.huang.product.service.CategoryBrandRelationService;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.R;

import javax.annotation.Resource;


/**
 * 品牌分类关联
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:07:07
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/catelog/list")
    public R get(String brandId){
        LambdaQueryWrapper<CategoryBrandRelationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryBrandRelationEntity::getBrandId,brandId);
        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.list(queryWrapper);
        return R.ok().put("data", list);
    }


    @GetMapping("/brands/list")
    public R getBrands(String catId){
        LambdaQueryWrapper<CategoryBrandRelationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryBrandRelationEntity::getCatelogId, catId);
        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.list(queryWrapper);
        return R.ok().put("data", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        Long catelogId = categoryBrandRelation.getCatelogId();
        LambdaQueryWrapper<CategoryEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryEntity::getCatId,catelogId);
        CategoryEntity category = categoryService.getOne(queryWrapper);
        LambdaQueryWrapper<BrandEntity> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(BrandEntity::getBrandId,categoryBrandRelation.getBrandId());
        BrandEntity brand = brandService.getOne(queryWrapper1);
        categoryBrandRelation.setCatelogName(category.getName());
        categoryBrandRelation.setBrandName(brand.getName());
        categoryBrandRelationService.save(categoryBrandRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
