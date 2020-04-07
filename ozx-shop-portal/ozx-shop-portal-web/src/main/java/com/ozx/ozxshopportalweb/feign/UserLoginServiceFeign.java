package com.ozx.ozxshopportalweb.feign;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.input.UserLoginInpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopserviceapimember.service.UserLoginService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ozx-shop-service-member")
public interface UserLoginServiceFeign extends UserLoginService{
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    BasicResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);
}
