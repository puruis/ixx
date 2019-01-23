package com.ixx.conf.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-14
 * Time: 15:14
 */
@Component
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
public class RedisProperties {
    private Integer database;
    private String host;
    private Integer port;
    private String password;
    private Duration timeout;
    /**
     * 默认30天 = 2592000s
     */
    private Integer expire = 2592000;
}
