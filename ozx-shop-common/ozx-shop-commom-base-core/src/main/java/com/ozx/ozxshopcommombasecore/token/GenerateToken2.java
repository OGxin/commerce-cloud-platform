package com.ozx.ozxshopcommombasecore.token;

import com.ozx.ozxshopcommombasecore.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName: TokenUtils
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/11 17:27
 * @Version： 1.0
 **/
@Component
public class GenerateToken2 {

    @Autowired
    private RedisUtil redisUtil;

    public String createToken(String prefixKey,String redisValue){
        return createToken(prefixKey,redisValue,null);
    }

    /**
     * 创建token
     * @param prefixKey
     * @param redisValue
     * @param time
     * @return [token]
     */
    public String createToken(String prefixKey,String redisValue,Long time) {
        if (redisValue == null) {
            new Exception("redis值不能为空");
        }
        String token=prefixKey+UUID.randomUUID().toString().replace("-","");
        redisUtil.setString(token,redisValue,time);
        return token;
    }

    /**
     * 根据token查询redis中的值
     * @param token
     * @return
     */
    public String getToken(String token)
    {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return redisUtil.getString(token);
    }

    /**
     * 移除redis中的token
     * @param token
     * @return boolean
     */
    public Boolean delToken(String token){
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return redisUtil.delete(token);
    }

}
