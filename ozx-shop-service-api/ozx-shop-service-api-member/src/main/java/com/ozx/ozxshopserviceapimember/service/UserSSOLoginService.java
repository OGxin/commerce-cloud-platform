package com.ozx.ozxshopserviceapimember.service;

import com.ozx.ozxshopapimemberdto.input.UserLoginInpDTO;
import com.ozx.ozxshopapimemberdto.output.UserOutpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "用户实现SSO登录接口")
public interface UserSSOLoginService {

    @ApiOperation(value="根据手机号、密码登录")
    BasicResponse<UserOutpDTO> ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO);
}
