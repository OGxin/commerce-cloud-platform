package com.ozx.ozxshopserviceapimember.service;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.input.UserLoginInpDTO;
import com.ozx.ozxshopapimemberdto.output.UserOutpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "用户登录服务接口")
public interface UserLoginService {

    @ApiOperation(value="用户登录信息接口")
    BasicResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);
    
}
