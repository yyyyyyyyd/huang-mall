package com.huang.coupon.service.impl;

import com.huang.coupon.entity.MemberPriceEntity;
import com.huang.coupon.entity.SkuLadderEntity;
import com.huang.coupon.entity.vo.MemberPrice;
import com.huang.coupon.entity.vo.SkuReductionTo;
import com.huang.coupon.service.MemberPriceService;
import com.huang.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.Query;

import com.huang.coupon.dao.SkuFullReductionDao;
import com.huang.coupon.entity.SkuFullReductionEntity;
import com.huang.coupon.service.SkuFullReductionService;

import javax.annotation.Resource;

@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity>
    implements SkuFullReductionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page =
            this.page(new Query<SkuFullReductionEntity>().getPage(params), new QueryWrapper<SkuFullReductionEntity>());

        return new PageUtils(page);
    }

    @Resource
    private SkuLadderService skuLadderService;
    @Resource
    private MemberPriceService memberPriceService;

    @Override
    public void saveSkuReduction(SkuReductionTo reductionTo) {
        // 1、// //5.4）、sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
        // sms_sku_ladder
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(reductionTo.getSkuId());
        skuLadderEntity.setFullCount(reductionTo.getFullCount());
        skuLadderEntity.setDiscount(reductionTo.getDiscount());
        skuLadderEntity.setAddOther(reductionTo.getCountStatus());
        if (reductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }

        // 2、sms_sku_full_reduction
        SkuFullReductionEntity reductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(reductionTo, reductionEntity);
        if (reductionEntity.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
            this.save(reductionEntity);
        }

        // 3、sms_member_price
        List<MemberPrice> memberPrice = reductionTo.getMemberPrice();

        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
            MemberPriceEntity priceEntity = new MemberPriceEntity();
            priceEntity.setSkuId(reductionTo.getSkuId());
            priceEntity.setMemberLevelId(item.getId());
            priceEntity.setMemberLevelName(item.getName());
            priceEntity.setMemberPrice(item.getPrice());
            priceEntity.setAddOther(1);
            return priceEntity;
        }).filter(item -> {
            return item.getMemberPrice().compareTo(new BigDecimal("0")) == 1;
        }).collect(Collectors.toList());

        memberPriceService.saveBatch(collect);
    }

}
