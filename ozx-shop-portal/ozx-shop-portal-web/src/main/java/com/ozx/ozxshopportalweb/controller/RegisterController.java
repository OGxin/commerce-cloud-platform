package com.ozx.ozxshopportalweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.input.UserInpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopcommonweb.base.BaseWebController;
import com.ozx.ozxshopcommonweb.bean.VOTransformDTOUtils;
import com.ozx.ozxshopcommonweb.utils.RandomValidateCodeUtil;
import com.ozx.ozxshopportalweb.feign.UserRegisterServiceFeign;
import com.ozx.ozxshopportalweb.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: RegisterController
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/21 2:05
 * @Version： 1.0
 **/
@Controller
public class RegisterController extends BaseWebController {

    @Autowired
    private UserRegisterServiceFeign userRegisterServiceFeign;

    private static final String USER_REGISTER_PAGE ="member/register";
    private static final String USER_LOGIN_PAGE ="member/login";



    @GetMapping("/register")
    public String getReg(){
        return USER_REGISTER_PAGE;
    }

    @PostMapping("/register")
    public String postReg(@ModelAttribute(name = "registerVo") @Validated RegisterVO registerVo, BindingResult bindingResult,
                          Model model, HttpSession httpSession){
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            setErrorMsg(model,errMsg);
            return USER_REGISTER_PAGE;
        }
        String graphicCode = registerVo.getGraphicCode();
        Boolean checkVerify = RandomValidateCodeUtil.checkVerify(graphicCode, httpSession);
        if (!checkVerify) {
            setErrorMsg(model,"图形验证码不正确");
            return USER_REGISTER_PAGE;
        }
        UserInpDTO userInpDTO= VOTransformDTOUtils.VoTransformDto(registerVo,UserInpDTO.class);
        BasicResponse<JSONObject> result = userRegisterServiceFeign.register(userInpDTO, "12345");
        if(!isSuccess(result)){
            setErrorMsg(model,result.getMsg());
            return USER_REGISTER_PAGE;
        }
        return USER_LOGIN_PAGE;
    }
}
