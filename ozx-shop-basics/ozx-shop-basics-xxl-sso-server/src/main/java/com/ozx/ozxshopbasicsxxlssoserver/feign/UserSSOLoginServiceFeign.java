package com.ozx.ozxshopbasicsxxlssoserver.feign;

import com.ozx.ozxshopserviceapimember.service.UserSSOLoginService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ozx-shop-service-member")
public interface UserSSOLoginServiceFeign extends UserSSOLoginService {
}
