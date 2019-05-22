package com.deschen.myblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author deschen
 * @Create 2019/3/28
 * @Description
 * @Since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "idworker")
@Data
public class IdWorkerConfig {

    private Integer workerId;
    private Integer datacenterId;
}
