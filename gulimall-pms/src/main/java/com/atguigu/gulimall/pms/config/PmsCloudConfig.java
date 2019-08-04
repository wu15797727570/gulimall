package com.atguigu.gulimall.pms.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

import javax.validation.groups.ConvertGroup;

@Configuration
@EnableDiscoveryClient//开启服务注册发现功能
@Slf4j
public class PmsCloudConfig {
}
