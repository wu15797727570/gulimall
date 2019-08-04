package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.pms.dao.AttrGroupDao;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.pms.service.AttrAttrgroupRelationService;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Autowired
    private AttrGroupDao attrGroupDao;
    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public AttrGroupEntity getGroupByAttrId(Long attrId) {

        QueryWrapper<AttrAttrgroupRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id" , attrId);
        AttrAttrgroupRelationEntity one = baseMapper.selectOne(queryWrapper);

        //
        AttrGroupEntity attrGroupEntity = null;
        if(one != null){
            Long groupId = one.getAttrGroupId();
            attrGroupEntity = attrGroupDao.selectById(groupId);
        }

        return attrGroupEntity;
    }

}