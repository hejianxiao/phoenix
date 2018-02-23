package com.phoenix.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hjx
 * 2018/2/1 0001.
 */
@Data
@Component
@ConfigurationProperties(prefix = "sysConfig")
public class SystemConfig {
    
    /** 系统路径. */
    private String basePath;

}
