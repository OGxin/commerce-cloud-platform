package com.ozx.ozxshopbasicsxxlssoserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.ozx.ozxshopapimemberdto.input.UserLoginInpDTO;
import com.ozx.ozxshopapimemberdto.output.UserOutpDTO;
import com.ozx.ozxshopbasicsxxlssoserver.feign.UserSSOLoginServiceFeign;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopcommombasecore.constants.Constants;
import com.ozx.ozxshopcommonweb.base.BaseWebController;
import com.xxl.sso.core.conf.Conf;
import com.xxl.sso.core.entity.ReturnT;
import com.xxl.sso.core.login.SsoWebLoginHelper;
import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.store.SsoSessionIdHelper;
import com.xxl.sso.core.user.XxlSsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName: WebController
 * @Description:xxl-sso 认证中心
 * @Author ou.zhenxing
 * @Date 2020/4/7 16:17
 * @Version： 1.0
 **/
@RestController
public class WebController extends BaseWebController {

    @Autowired
    private UserSSOLoginServiceFeign userSSOLoginServiceFeign;

    @RequestMapping("/")
    public BasicResponse<XxlSsoUser> index(HttpServletRequest request, HttpServletResponse response) {
        // login check
        XxlSsoUser xxlUser = SsoWebLoginHelper.loginCheck(request, response);
        return super.success(xxlUser);
    }


    @PostMapping("/login")
    public BasicResponse<String> doLogin(HttpServletRequest request,
                                             HttpServletResponse response,
                                             String username, String password,
                                             String ifRemember){
        boolean ifRem = (ifRemember != null & "on".equals(ifRemember))?true:false;
        UserLoginInpDTO loginInpDTO = new UserLoginInpDTO();
        loginInpDTO.setMobile(username);
        loginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
        loginInpDTO.setPassword(password);
//        String browserInfo = getwebBrowserInfo(request);
//        loginInpDTO.setDeviceInfor(browserInfo);
        BasicResponse<UserOutpDTO> ssoLoginResult = userSSOLoginServiceFeign.ssoLogin(loginInpDTO);
        if(ssoLoginResult.getCode()!= ReturnT.SUCCESS_CODE){
            return super.failure(ssoLoginResult.getMsg());
        }

        XxlSsoUser xxlSsoUser = new XxlSsoUser();
        xxlSsoUser.setUserid(ssoLoginResult.getData().getUserId().toString());
        xxlSsoUser.setUsername(ssoLoginResult.getData().getMobile());
        xxlSsoUser.setVersion(UUID.randomUUID().toString().replaceAll("-",""));
        xxlSsoUser.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        xxlSsoUser.setExpireFreshTime(System.currentTimeMillis());

        String sessionId = SsoSessionIdHelper.makeSessionId(xxlSsoUser);

        SsoWebLoginHelper.login(response,sessionId,xxlSsoUser,ifRem);
        return super.success(sessionId);
    }

    @RequestMapping(Conf.SSO_LOGOUT)
    public BasicResponse<String> logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        BasicResponse<String> result = new BasicResponse<>();
        // logout
        SsoWebLoginHelper.logout(request, response);
        result.setCode(200);
        result.setMsg("注销成功！");
//        redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        return result;
    }

    @PostMapping("user")
    public BasicResponse<UserOutpDTO> getUserInfo(@RequestBody UserLoginInpDTO userLoginInpDTO){
         return userSSOLoginServiceFeign.ssoLogin(userLoginInpDTO);
    }



}
