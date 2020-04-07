package com.ozx.ozxshopportalweb.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName: RegisterVO
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/24 14:59
 * @Version： 1.0
 **/
@Data
public class RegisterVO {

    @NotBlank(message = "手机号码不能为空")
    @Size(min = 11,max = 11,message = "手机号码长度不正确")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$",message = "手机号码格式不正确")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

//    @NotBlank(message = "验证码不能为空")
    private String registCode;

    @NotBlank(message = "图形验证码不能为空")
    private String graphicCode;

}
