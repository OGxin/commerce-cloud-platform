package com.ozx.ozxshopbasicsxxlssoserver.config;

import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: XxlSsoConfig
 * @Description: xxl-sso 的config配置文件
 * @Author ou.zhenxing
 * @Date 2020/4/7 15:56
 * @Version： 1.0
 **/
@Configuration
public class XxlSsoConfig implements InitializingBean,DisposableBean{

    @Value("${xxl.sso.redis.address}")
    private String redisAddress;

    @Value("${xxl.sso.redis.expire.minute}")
    private int redisExpireMinute;

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinute(redisExpireMinute);
        JedisUtil.init(redisAddress);
    }
}
