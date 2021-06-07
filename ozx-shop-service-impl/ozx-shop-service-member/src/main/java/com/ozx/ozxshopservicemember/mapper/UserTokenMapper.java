package com.ozx.ozxshopservicemember.mapper;

import com.ozx.ozxshopservicemember.entity.UserTokenDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserTokenMapper {

    @Select("select id as id,token as token,login_type as loginType,is_availability as isAvailability," +
            "user_id as userId from mall_user_token where user_id =#{userId} and login_type =#{loginType} and is_availability = '0'")
    UserTokenDO getUserLoginStatus(@Param("userId") Long userId,@Param("loginType") String loginType);

    @Insert("insert into mall_user_token(token,login_type,device_infor,is_availability,user_id,create_time) values(#{token},#{loginType},#{deviceInfor},'0',#{userId},now())")
    int insertUserToken(UserTokenDO userToken);
}
