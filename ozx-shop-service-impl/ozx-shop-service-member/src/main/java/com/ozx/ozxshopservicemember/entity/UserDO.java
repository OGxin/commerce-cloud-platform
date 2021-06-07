package com.ozx.ozxshopservicemember.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @ClassName: UserDO
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/6 16:43
 * @Version： 1.0
 **/
@Data
public class UserDO {
    private Long userId;
    private String mobile;
    private String email;
    private String password;
    private String userName;
    private Integer sex;
    private Integer age;
    private Date createTime;
    private Integer isAvalible;
    private String picImg;
    private String qqOpenid;
    private String wxOpenid;
}
