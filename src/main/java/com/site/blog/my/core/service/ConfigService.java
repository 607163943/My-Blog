package com.site.blog.my.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.site.blog.my.core.pojo.po.BlogConfig;

import java.util.Map;

public interface ConfigService extends IService<BlogConfig> {
    /**
     * 修改配置项
     *
     * @param configName 配置项名称
     * @param configValue 配置项值
     * @return 0-失败  1-成功
     */
    int updateConfig(String configName, String configValue);

    /**
     * 获取所有的配置项
     *
     * @return 所有的配置项
     */
    Map<String,String> getAllConfigs();
}
