package com.ozx.ozxshopservicemember.entity;

import com.ozx.ozxshopcommombasecore.basic.BasicDO;
import lombok.Data;

/**
 * @ClassName: UserTokenDO
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/11 16:57
 * @Version： 1.0
 **/
@Data
public class UserTokenDO extends BasicDO {
    private Long id;
    private String token;
    private String loginType;
    private String deviceInfor;
    private Long userId;
}
