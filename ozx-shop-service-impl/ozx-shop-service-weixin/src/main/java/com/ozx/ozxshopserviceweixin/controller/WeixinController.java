package com.ozx.ozxshopserviceweixin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: WeixinController
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/1/8 14:26
 * @Version： 1.0
 **/
@RestController
@RequestMapping("/wx")
@ApiModel("微信模块")
public class WeixinController {

    @GetMapping("/send")
    @ApiOperation(value="发送微信消息",notes = "微信消息")
    public String sendMessage(){
        return "发送微信消息";
    }
}
