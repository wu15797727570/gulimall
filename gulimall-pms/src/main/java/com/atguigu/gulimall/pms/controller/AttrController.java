package com.atguigu.gulimall.pms.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;
import com.atguigu.gulimall.commons.bean.Resp;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import com.atguigu.gulimall.pms.service.AttrAttrgroupRelationService;
import com.atguigu.gulimall.pms.service.AttrGroupService;
import com.atguigu.gulimall.pms.vo.AttrGroupSaveVo;
import com.atguigu.gulimall.pms.vo.AttrWithGroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.service.AttrService;




/**
 * 商品属性
 *
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:34:08
 */
@Api(tags = "商品属性 管理")
@RestController
@RequestMapping("pms/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @GetMapping("/sale/{catId}")
    @ApiOperation("查询某个三级分类下的所有销售属性")
    public Resp<PageVo> getSaleAttrByCatId(QueryCondition queryCondition ,
                                           @PathVariable("catId") Long catId){
        PageVo page = attrService.getSaleAttrByCatId(queryCondition , catId);
        return Resp.ok(page);
    }


    @GetMapping("/base/{catId}")
    @ApiOperation("查询某个三级分类下的所有基本属性")
    public Resp<PageVo> getBaseAttrByCatId(QueryCondition queryCondition ,
            @PathVariable("catId") Long catId){
        PageVo page = attrService.getBaseAttrByCatId(queryCondition , catId);
        return Resp.ok(page);
    }
    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:attr:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = attrService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{attrId}")
    @PreAuthorize("hasAuthority('pms:attr:info')")
    public Resp<AttrWithGroupVo> info(@PathVariable("attrId") Long attrId){
        AttrWithGroupVo attrWithGroupVo = new AttrWithGroupVo();
        AttrEntity attr = attrService.getById(attrId);
        BeanUtils.copyProperties(attr , attrWithGroupVo);

        //查询分组信息
        AttrGroupEntity attrGroup = attrAttrgroupRelationService.getGroupByAttrId(attrId);
        attrWithGroupVo.setGroup(attrGroup);

        return Resp.ok(attrWithGroupVo);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:attr:save')")
    public Resp<Object> save(@RequestBody AttrGroupSaveVo groupSaveVo){
		attrService.saveAttrAndGroup(groupSaveVo);

        return Resp.ok("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:attr:update')")
    public Resp<Object> update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:attr:delete')")
    public Resp<Object> delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return Resp.ok(null);
    }

}
