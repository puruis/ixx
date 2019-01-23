package com.ixx.conf;

import org.crazycake.shiro.RedisManager;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * Description:
 * User: purui_zhang
 * Date: 2018-11-14
 * Time: 15:01
 */
public class CustomRedisManager extends RedisManager {


    private static JedisPool jedisPool = null;
    private String host;
    private int port;
    private int expire;
    private int timeout;
    private int database;
    private String password = null;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public CustomRedisManager() {
    }
    public void init(){
        this.password = StringUtils.isEmpty(this.password) ? null : this.password;
        if (jedisPool == null) {
            jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port, this.timeout, this.password, database);
        }
    }

}
