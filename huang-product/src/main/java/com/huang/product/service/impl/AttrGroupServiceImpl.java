package com.huang.product.service.impl;

import com.huang.common.utils.Query;
import com.huang.common.utils.PageUtils;
import com.huang.product.entity.AttrEntity;
import com.huang.product.entity.vo.AttrGroupWithAttrsVo;
import com.huang.product.service.AttrService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huang.product.dao.AttrGroupDao;
import com.huang.product.entity.AttrGroupEntity;
import com.huang.product.service.AttrGroupService;

import javax.annotation.Resource;

/**
 * impl attr集团服务
 *
 * @author Yidan Huang
 * @date 2023/03/18
 */
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page =
            this.page(new Query<AttrGroupEntity>().getPage(params), new QueryWrapper<AttrGroupEntity>());

        return new PageUtils(page);
    }

    /**
     * 根据分类id查出所有的分组以及这些组里面的属性
     *
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        // com.atguigu.gulimall.product.vo
        // 1、查询分组信息
        List<AttrGroupEntity> attrGroupEntities =
            this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        // 2、查询所有属性
        return attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group, attrsVo);
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getAttrGroupId());
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());
    }
}
