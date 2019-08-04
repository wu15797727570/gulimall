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

import com.atguigu.gulimall.pms.dao.SpuInfoDao;
import com.atguigu.gulimall.pms.entity.SpuInfoEntity;
import com.atguigu.gulimall.pms.service.SpuInfoService;
import org.springframework.util.StringUtils;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageVo(page);
    }

    /**
     * 按照spuid,spuname,分类id检索商品
     * @param queryCondition
     * @param catId
     * @return
     */
    @Override
    public PageVo queryPageSearch(QueryCondition queryCondition, Long catId) {

        //获取分页信息

        IPage<SpuInfoEntity> page = new Query<SpuInfoEntity>().getPage(queryCondition);
        //查询条件
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();
        if(catId != 0){

            queryWrapper.eq("catalog_id" , catId);

            if(!StringUtils.isEmpty(queryCondition.getKey())){
                queryWrapper .and(obj -> {
                    obj.like("spu_name" , queryCondition.getKey())
                            .or().like("id" , queryCondition.getKey());
                    return obj;
                });
            }
        }else {
            if(!StringUtils.isEmpty(queryCondition.getKey())){
                queryWrapper .and(obj -> {
                    obj.like("spu_name" , queryCondition.getKey())
                            .or().like("id" , queryCondition.getKey());
                    return obj;
                });
            }
        }


        IPage<SpuInfoEntity> infoEntityIPage = this.page(page, queryWrapper);

        PageVo pageVo = new PageVo(infoEntityIPage);
        return pageVo;
    }

}