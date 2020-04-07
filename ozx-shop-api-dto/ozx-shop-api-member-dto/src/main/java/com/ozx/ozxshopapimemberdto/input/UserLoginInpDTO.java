package com.ozx.ozxshopapimemberdto.input;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserLoginInpDTO
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/11 10:09
 * @Version： 1.0
 **/
@Data
@ApiModel("用户登录输入类")
public class UserLoginInpDTO {

    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 登录类型：PC、IOS、Android
     */
    @ApiModelProperty(value = "登录类型")
    private String loginType;
    @ApiModelProperty(value = "登录设备")
    private String deviceInfor;
}
