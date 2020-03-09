package com.ozx.ozxshopserviceapimember.service;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.input.UserInpDTO;
import com.ozx.ozxshopcommon.basic.BasicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "用户注册接口")
public interface UserRegisterService {

    @PostMapping("/register")
    @ApiOperation(value = "用户信息注册接口")
    BasicResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO,
                                       @RequestParam(value = "regCode") String regCode);
}
