package com.ozx.ozxshopcommon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisUtil
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/4 10:55
 * @Version： 1.0
 **/
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Description: redis set值
     * @Author ou.zhenxing
     * @Date   2020/3/4 11:09
     * @Param
     * @return
     **/
    public void setString(String key,String data,Long timeout){
        stringRedisTemplate.opsForValue().set(key,data);
        if (timeout != null) {
            stringRedisTemplate.expire(key,timeout, TimeUnit.SECONDS);
        }
    }

    public void setString(String key,String data){
        setString(key,data,null);
    }

    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key){
        return stringRedisTemplate.delete(key);
    }
}
