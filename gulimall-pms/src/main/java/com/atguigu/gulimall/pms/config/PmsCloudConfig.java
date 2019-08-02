package com.atguigu.gulimall.pms.config;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

import javax.validation.groups.ConvertGroup;

@Configuration
@EnableDiscoveryClient//开启服务注册发现功能
public class PmsCloudConfig {
}
