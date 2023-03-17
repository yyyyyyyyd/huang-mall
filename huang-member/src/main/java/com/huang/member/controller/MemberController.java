package com.huang.member.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import com.huang.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huang.member.entity.MemberEntity;
import com.huang.member.service.MemberService;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.R;

import javax.annotation.Resource;


/**
 * 会员
 *
 * @author Huangyidan
 * @email 1360532612@qq.com
 * @date 2023-03-17 10:13:15
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Resource
    private CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public R test(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("会员昵称张三");
        R membercoupons = couponFeignService.membercoupons();//假设张三去数据库查了后返回了张三的优惠券信息

        //打印会员和优惠券信息
        return Objects.requireNonNull(R.ok().put("member", memberEntity)).put("coupons",membercoupons.get("coupons"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
