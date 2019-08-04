package com.atguigu.gulimall.pms.service;

import com.atguigu.gulimall.pms.vo.AttrGroupSaveVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 商品属性
 *
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:34:08
 */
public interface AttrService extends IService<AttrEntity> {

    PageVo queryPage(QueryCondition params);


    /**
     * 查询某个三级分类下的所有基本属性
     * @param queryCondition
     * @param catId
     * @return
     */
    PageVo getBaseAttrByCatId(QueryCondition queryCondition, Long catId);

    PageVo getSaleAttrByCatId(QueryCondition queryCondition, Long catId);

    void saveAttrAndGroup(AttrGroupSaveVo groupSaveVo);
}

