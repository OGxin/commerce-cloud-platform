package com.ozx.ozxshopcommombasecore.basic;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: BasicDO
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/11 17:08
 * @Version： 1.0
 **/
@Data
public class BasicDO {
    private Date createTime;
    private Date updateTime;
    private Long isAvailability;
}
