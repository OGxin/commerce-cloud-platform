package com.ozx.ozxshopapimemberdto.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserOutpDTO
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/4/7 17:31
 * @Version： 1.0
 **/
@Data
@ApiModel(value = "用户输出信息")
public class UserOutpDTO {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
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
