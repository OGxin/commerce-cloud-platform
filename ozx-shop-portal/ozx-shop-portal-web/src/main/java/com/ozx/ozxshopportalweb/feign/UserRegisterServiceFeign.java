package com.ozx.ozxshopportalweb.feign;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.input.UserInpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopserviceapimember.service.UserRegisterService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ozx-shop-service-member")
public interface UserRegisterServiceFeign extends UserRegisterService {

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    BasicResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO,
                                       @RequestParam(value = "regCode") String regCode);


}
