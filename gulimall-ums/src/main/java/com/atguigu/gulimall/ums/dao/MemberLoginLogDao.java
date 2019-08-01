package com.atguigu.gulimall.ums.dao;

import com.atguigu.gulimall.ums.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author wuxiaoqiang
 * @email 1575794449@qq.com
 * @date 2019-08-01 18:38:02
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
