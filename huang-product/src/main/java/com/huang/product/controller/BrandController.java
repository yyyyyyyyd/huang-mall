package com.huang.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.huang.product.entity.BrandEntity;
import com.huang.product.service.BrandService;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.R;



/**
 * 品牌
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 09:07:07
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     * t=1679108810799&page=1&limit=10&key=
     */
    @RequestMapping("/list")
    public R list(String page, String limit, String key) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("limit", limit);
        params.put("key", key);
        PageUtils page1 = brandService.queryPage(params);
        return R.ok().put("page", page1);
    }

    @PostMapping("/update/status")
    public R updateStatus(@RequestBody BrandEntity brand){
        brandService.updateById(brand);
        return R.ok("更新成功");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
