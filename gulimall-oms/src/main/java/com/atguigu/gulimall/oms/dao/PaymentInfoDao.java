package com.atguigu.gulimall.oms.dao;

import com.atguigu.gulimall.oms.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:29:42
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
