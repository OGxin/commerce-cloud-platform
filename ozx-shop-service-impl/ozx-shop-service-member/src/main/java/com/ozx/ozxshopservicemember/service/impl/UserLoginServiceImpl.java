package com.ozx.ozxshopservicemember.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopapimemberdto.output.UserOutpDTO;
import com.ozx.ozxshopcommombasecore.basic.BasicApiService;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import com.ozx.ozxshopcommombasecore.bean.TransformBeanUtils;
import com.ozx.ozxshopcommombasecore.constants.Constants;
import com.ozx.ozxshopcommombasecore.token.GenerateToken;
import com.ozx.ozxshopcommombasecore.transaction.RedisDataResourceTransaction;
import com.ozx.ozxshopcommombasecore.utils.MD5Util;
import com.ozx.ozxshopservicemember.entity.UserDO;
import com.ozx.ozxshopservicemember.entity.UserTokenDO;
import com.ozx.ozxshopservicemember.mapper.UserMapper;
import com.ozx.ozxshopservicemember.mapper.UserTokenMapper;
import com.ozx.ozxshopapimemberdto.input.UserLoginInpDTO;
import com.ozx.ozxshopserviceapimember.service.UserLoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserLoginServiceImpl
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/11 10:02
 * @Version： 1.0
 **/
//@Service
@RestController
public class UserLoginServiceImpl extends BasicApiService<JSONObject> implements UserLoginService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GenerateToken generateToken;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private RedisDataResourceTransaction redisDataResourceTransaction;

    @Override
    @PostMapping("/login")
    public BasicResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登录类型不能为空");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC) ||
                loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)||
                    loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID))) {
            return setResultError("登录类型出错");
        }
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空");
        }
        String md5Password = MD5Util.MD5(password);
        UserDO userDO = userMapper.login(mobile, md5Password);
        if (userDO == null) {
            return setResultError("用户账号或密码错误");
        }
        TransactionStatus transactionStatus = null;
        try {
        Long userId = userDO.getUserId();
        /**
         * 通过userId、登录类型、登录状态为0（已登录）
         */
        UserTokenDO userTokenDO = userTokenMapper.getUserLoginStatus(userId, loginType);
            transactionStatus = redisDataResourceTransaction.begin();
            if (userTokenDO != null) {
                String token = userTokenDO.getToken();
                Boolean removeToken = generateToken.removeToken(userTokenDO.getToken());
                int updateLoginStatus = userMapper.updateLoginStatus(token);
                    if(!toDaoResult(updateLoginStatus)){
                        redisDataResourceTransaction.rollback(transactionStatus);
                    }

            }
            UserTokenDO userToken = new UserTokenDO();
            userToken.setLoginType(loginType);
            userToken.setDeviceInfor(deviceInfor);
            String prefixKey = Constants.MEMBER_TOKEN_KEYPREFIX + loginType;
            String token = generateToken.createToken(prefixKey, userId+"");
            userToken.setToken(token);
            userToken.setUserId(userId);
            int insertUserToken = userTokenMapper.insertUserToken(userToken);
            if(!toDaoResult(insertUserToken)){
                redisDataResourceTransaction.rollback(transactionStatus);
                return setResultError("系统错误");
            }
            JSONObject data = new JSONObject();
            data.put("token",token);
            redisDataResourceTransaction.commit(transactionStatus);
            return setResultSuccess(data);


        } catch (Exception e) {
            try {
                redisDataResourceTransaction.rollback(transactionStatus);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return setResultError("系统错误");
        }

    }


}
