package com.atguigu.gulimall.sms.dao;

import com.atguigu.gulimall.sms.entity.CouponSpuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联
 * 
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:35:57
 */
@Mapper
public interface CouponSpuRelationDao extends BaseMapper<CouponSpuRelationEntity> {
	
}
