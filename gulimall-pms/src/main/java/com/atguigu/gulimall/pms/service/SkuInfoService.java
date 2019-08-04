package com.atguigu.gulimall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.pms.entity.SkuInfoEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import java.util.List;


/**
 * sku信息
 *
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:34:08
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageVo queryPage(QueryCondition params);

    /**
     * 获取某个spu下的所有sku信息
     * @param spuId
     * @return
     */
    List<SkuInfoEntity> getSkuInfoBySpuId(Long spuId);
}

