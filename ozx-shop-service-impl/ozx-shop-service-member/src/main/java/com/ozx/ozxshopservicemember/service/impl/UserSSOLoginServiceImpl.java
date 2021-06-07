package com.ozx.ozxshopservicemember.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ozx.ozxshopapimemberdto.input.UserLoginInpDTO;
import com.ozx.ozxshopapimemberdto.output.UserOutpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicApiService;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopcommombasecore.bean.TransformBeanUtils;
import com.ozx.ozxshopcommombasecore.constants.Constants;
import com.ozx.ozxshopcommombasecore.utils.MD5Util;
import com.ozx.ozxshopserviceapimember.service.UserSSOLoginService;
import com.ozx.ozxshopservicemember.entity.UserDO;
import com.ozx.ozxshopservicemember.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserSSOLoginServiceImpl
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/4/10 14:50
 * @Version： 1.0
 **/
@RestController
public class UserSSOLoginServiceImpl extends BasicApiService<UserOutpDTO> implements UserSSOLoginService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @RequestMapping(name = "ssoLogin")
    @HystrixCommand(fallbackMethod = "getUserInfoFallback")
    public BasicResponse<UserOutpDTO> ssoLogin(UserLoginInpDTO userLoginInpDTO) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空");
        }
//        String loginType = userLoginInpDTO.getLoginType();
//        if (StringUtils.isEmpty(loginType)) {
//            return setResultError("登录类型不能为空");
//        }
//        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC) ||
//                loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)||
//                loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID))) {
//            return setResultError("登录类型出错");
//        }
//        String deviceInfor = userLoginInpDTO.getDeviceInfor();
//        if (StringUtils.isEmpty(deviceInfor)) {
//            return setResultError("设备信息不能为空");
//        }
        String md5Password = MD5Util.MD5(password);
        UserDO userDO = userMapper.ssoLogin(mobile, md5Password);
        if (userDO == null) {
            setResultError("手机号或密码不正确！");
        }
        UserOutpDTO userOutpDTO = TransformBeanUtils.doTransformDto(userDO, UserOutpDTO.class);
        return setResultSuccess(userOutpDTO);
    }

    public BasicResponse<UserOutpDTO> getUserInfoFallback(@RequestBody UserLoginInpDTO userLoginInpDTO){
        UserOutpDTO userOutpDTO = new UserOutpDTO();
        userOutpDTO.setUserId(-1L);
        userOutpDTO.setMobile("88888888888");
        return setResultSuccess(userOutpDTO);
    }
}
