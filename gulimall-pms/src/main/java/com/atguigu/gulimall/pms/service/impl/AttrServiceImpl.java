package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.pms.vo.AttrGroupSaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.dao.AttrDao;
import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrDao attrDao;
    @Autowired
    private AttrAttrgroupRelationDao relationDao;
    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageVo(page);
    }

    /**
     * 查询某个三级分类下的所有基本属性
     * @param queryCondition
     * @param catId
     * @return
     */
    @Override
    public PageVo getBaseAttrByCatId(QueryCondition queryCondition, Long catId) {

        //创建分页信息
        IPage<AttrEntity> page = new Query<AttrEntity>().getPage(queryCondition);

        //
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id" , catId);
        queryWrapper.eq("attr_type" , 1);

        IPage<AttrEntity> attrEntityIPage = this.page(page, queryWrapper);

        PageVo pageVo = new PageVo(attrEntityIPage);
        return pageVo;
    }

    @Override
    public PageVo getSaleAttrByCatId(QueryCondition queryCondition, Long catId) {
        //创建分页信息
        IPage<AttrEntity> page = new Query<AttrEntity>().getPage(queryCondition);

        //
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id" , catId);
        queryWrapper.eq("attr_type" , 0);

        IPage<AttrEntity> attrEntityIPage = this.page(page, queryWrapper);

        PageVo pageVo = new PageVo(attrEntityIPage);
        return pageVo;
    }

    /**
     * 保存
     * @param groupSaveVo
     */
    @Transactional
    @Override
    public void saveAttrAndGroup(AttrGroupSaveVo groupSaveVo) {
        //先将数据保存到属性表中
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(groupSaveVo , attrEntity);

        baseMapper.insert(attrEntity);

        //创建关联关系
        Long attrId = attrEntity.getAttrId();
        System.out.println("hahahahhaha ++++" + attrEntity.getAttrId());
        Long attrGroupId = groupSaveVo.getAttrGroupId();
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrId(attrId);
        relationEntity.setAttrGroupId(attrGroupId);
        relationDao.insert(relationEntity);

    }

}