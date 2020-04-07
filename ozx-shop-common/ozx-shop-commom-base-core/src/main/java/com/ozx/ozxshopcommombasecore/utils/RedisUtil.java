package com.ozx.ozxshopcommombasecore.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

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

    /**
     * 根据key删除redis储存的key-value
     * @param key
     * @return
     */
    public boolean delete(String key){
        return stringRedisTemplate.delete(key);
    }

    /**
     * 开启redis事务
     */
    public void begin(){
        /**
         * 开启redis事务权限
         */
        stringRedisTemplate.setEnableTransactionSupport(true);
        /**
         * 开启事务
         */
        stringRedisTemplate.multi();
    }

    /**
     * 回滚redis事务
     */
    public void rollback(){
        stringRedisTemplate.discard();
    }

    /**
     * 成功提交事务
     */
    public void exec(){
        stringRedisTemplate.exec();
    }
}
