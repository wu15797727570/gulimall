package com.atguigu.gulimall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.pms.entity.CategoryEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import java.util.List;


/**
 * 商品三级分类
 *
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:34:08
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageVo queryPage(QueryCondition params);

    /**
     * 获取某个等级所有分类数据
     * @param level
     * @return
     */
    List<CategoryEntity> listTree(Integer level);

    /**
     * 获取某个分类的所有子分类
     * @param catId
     * @return
     */
    List<CategoryEntity> listChildrenTree(Integer catId);
}

