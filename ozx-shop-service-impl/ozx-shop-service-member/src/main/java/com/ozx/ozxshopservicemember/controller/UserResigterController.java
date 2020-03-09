package com.ozx.ozxshopservicemember.controller;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.input.UserInpDTO;
import com.ozx.ozxshopcommon.basic.BasicResponse;
import com.ozx.ozxshopserviceapimember.service.UserRegisterService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserResigterController
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/9 15:15
 * @Version： 1.0
 **/
@RestController
@RequestMapping("/mem")
@ApiModel("用户注册模块")
public class UserResigterController {
    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("/reg")
    @ApiOperation(value = "注册",notes = "注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "regCode",paramType = "query",value = "验证码",dataType = "String",example = "12345")
    }
    )
    public BasicResponse<JSONObject> register(UserInpDTO userInpDTO,String regCode){
        return userRegisterService.register(userInpDTO, regCode);
    }
}
