package com.ozx.ozxshopapimemberdto.input;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @ClassName: UserInpDTO
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/6 17:11
 * @Version： 1.0
 **/
@Data
@ApiModel(value = "用户信息实体类")
public class UserInpDTO {
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "QQ开放ID")
    private String qqOpenid;
    @ApiModelProperty(value = "微信开放ID")
    private String wxOpenid;
}
