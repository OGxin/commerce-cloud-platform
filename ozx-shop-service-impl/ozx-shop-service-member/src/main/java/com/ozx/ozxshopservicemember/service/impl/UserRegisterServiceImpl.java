package com.ozx.ozxshopservicemember.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.input.UserInpDTO;
import com.ozx.ozxshopcommon.basic.BasicApiService;
import com.ozx.ozxshopcommon.basic.BasicResponse;
import com.ozx.ozxshopcommon.bean.TransformBeanUtils;
import com.ozx.ozxshopserviceapimember.service.UserRegisterService;
import com.ozx.ozxshopservicemember.entity.UserDO;
import com.ozx.ozxshopservicemember.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: UserRegisterServiceImpl
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/7 0:01
 * @Version： 1.0
 **/
@Service
public class UserRegisterServiceImpl extends BasicApiService<JSONObject> implements UserRegisterService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public BasicResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO, String regCode) {
        String userName = userInpDTO.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名不能为空");
        }
        String mobile = userInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空");
        }
        String password = userInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空");
        }
        UserDO userDO = TransformBeanUtils.dtoTransformDO(userInpDTO, UserDO.class);
        return userMapper.register(userDO)>0?setResultSuccess("注册成功"):setResultError("注册失败");
    }
}
