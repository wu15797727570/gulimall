package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimall.pms.dao.AttrDao;
import com.atguigu.gulimall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.AttrGroupDao;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import com.atguigu.gulimall.pms.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrDao attrDao;
    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageVo(page);
    }

    /**
     * 查询某个分组下对应的所有属性
     * @param queryCondition
     * @param groupId
     * @return
     */
    @Override
    public PageVo getAttrList(QueryCondition queryCondition, Long groupId) {

        //获取分页信息
        IPage<AttrGroupEntity> page = new Query<AttrGroupEntity>().getPage(queryCondition);

        //条件查询
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id " , groupId);

        IPage<AttrGroupEntity> groupEntityIPage = this.page(page, queryWrapper);

        PageVo pageVo = new PageVo(groupEntityIPage);
        return pageVo;
    }

    /**
     * 查询某个分组以及分组下面的所有属性信息
     * @param attrGroupId
     * @return
     */
    @Override
    public AttrGroupWithAttrsVo getAttrByAttrGroupId(Long attrGroupId) {
        AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
        //查询出属性分组的信息
        AttrGroupEntity groupEntity = this.getById(attrGroupId);
        BeanUtils.copyProperties(groupEntity , attrsVo);

        //查询出级联关系的信息
        Long attrGroupId1 = groupEntity.getAttrGroupId();
        QueryWrapper<AttrAttrgroupRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_group_id" , attrGroupId1);
        List<AttrAttrgroupRelationEntity> relations = relationDao.selectList(queryWrapper);
        attrsVo.setRelations(relations);

        //查出当前分组的所有属性信息
        List<Long> attrIds = new ArrayList<>();
        relations.forEach(item -> {
            Long attrId = item.getAttrId();
            attrIds.add(attrId);
        });

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.in("attr_id " , attrIds);

        List<AttrEntity> attrEntities = attrDao.selectList(wrapper);
        attrsVo.setAttrEntities(attrEntities);
        return attrsVo;
    }

}