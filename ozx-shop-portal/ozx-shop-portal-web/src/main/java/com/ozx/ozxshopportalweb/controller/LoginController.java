package com.ozx.ozxshopportalweb.controller;

import com.alibaba.fastjson.JSONObject;
//import com.netflix.discovery.converters.Auto;
import com.ozx.ozxshopapimemberdto.input.UserLoginInpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopcommombasecore.constants.Constants;
import com.ozx.ozxshopcommonweb.base.BaseWebController;
import com.ozx.ozxshopcommonweb.bean.VOTransformDTOUtils;
import com.ozx.ozxshopcommonweb.constants.WebConstant;
import com.ozx.ozxshopcommonweb.utils.CookieUtils;
import com.ozx.ozxshopcommonweb.utils.RandomValidateCodeUtil;
import com.ozx.ozxshopportalweb.feign.UserLoginServiceFeign;
import com.ozx.ozxshopportalweb.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: LoginController
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/21 1:58
 * @Version： 1.0
 **/
@Controller
public class LoginController extends BaseWebController{

    @Autowired
    private UserLoginServiceFeign userLoginServiceFeign;
//    private UserRegisterServiceFeign userRegisterServiceFeign;

    /**
     *指向前端页面-存在前端文件夹的名称/前端文件名
     */
    private static final String USER_LOGIN_PAGE ="member/login";

    private static final String REDIRECT_INDEX ="redirect:/";

    @GetMapping("/login")
    public String getLogin(){
        return USER_LOGIN_PAGE;
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute(value = "loginVo") @Validated LoginVO loginVo, BindingResult bindingResult,
                            Model model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession){
        String graphicCode = loginVo.getGraphicCode();
        if (!RandomValidateCodeUtil.checkVerify(graphicCode,httpSession)) {
            setErrorMsg(model,"图形验证码不正确");
            return USER_LOGIN_PAGE;
        }
        UserLoginInpDTO userLoginInpDTO = VOTransformDTOUtils.VoTransformDto(loginVo, UserLoginInpDTO.class);
        String browserInfo = getwebBrowserInfo(request);
        userLoginInpDTO.setDeviceInfor(browserInfo);
        userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
        BasicResponse<JSONObject> loginResult = userLoginServiceFeign.login(userLoginInpDTO);
//        BasicResponse<JSONObject> loginResult = null;
        if (!isSuccess(loginResult)) {
            setErrorMsg(model,loginResult.getMsg());
            return USER_LOGIN_PAGE;
        }
        String token = loginResult.getData().getString("token");
        CookieUtils.setCookie(request,response, WebConstant.USER_LOGIN_TOKEN,token);
        return REDIRECT_INDEX;
    }
}
