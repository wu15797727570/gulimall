package com.atguigu.gulimall.pms.service;

import com.atguigu.gulimall.pms.vo.AttrGroupWithAttrsVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 属性分组
 *
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:34:08
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageVo queryPage(QueryCondition params);

    /**
     * 查询某个分组下对应的所有属性
     * @param queryCondition
     * @param groupId
     * @return
     */
    PageVo getAttrList(QueryCondition queryCondition, Long groupId);

    /**
     * 查询某个分组以及分组下面的所有属性信息
     * @param attrGroupId
     * @return
     */
    AttrGroupWithAttrsVo getAttrByAttrGroupId(Long attrGroupId);
}

