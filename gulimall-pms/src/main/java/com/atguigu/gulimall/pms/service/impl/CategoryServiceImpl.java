package com.atguigu.gulimall.pms.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.CategoryDao;
import com.atguigu.gulimall.pms.entity.CategoryEntity;
import com.atguigu.gulimall.pms.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageVo(page);
    }

    /**
     * 获取某个等级所有分类数据
     * @param level
     * @return
     */
    @Override
    public List<CategoryEntity> listTree(Integer level) {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();

        if(level != 0){
            queryWrapper.eq("cat_level" , level);
        }
        List<CategoryEntity> list = baseMapper.selectList(queryWrapper);
        return list;
    }


    /**
     * 获取某个分类的所有子分类
     * @param catId
     * @return
     */
    @Override
    public List<CategoryEntity> listChildrenTree(Integer catId) {

        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_cid " , catId);

        List<CategoryEntity> list = baseMapper.selectList(queryWrapper);
        return list;
    }

}