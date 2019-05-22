package com.deschen.myblog.core.utils;


import com.deschen.myblog.config.IdWorkerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author deschen
 * @Create 2019/3/28
 * @Description
 * @Since 1.0.0
 */
@Component
public class IdWorkerUtil {

    @Autowired
    private IdWorkerConfig config;

    public Long getId() {
        long id = new IdWorker(config.getWorkerId(),
                config.getDatacenterId()).nextId();
        return id;
    }

}
