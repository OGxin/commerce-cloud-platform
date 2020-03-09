package com.ozx.ozxshopservicemember.mapper;

import com.ozx.ozxshopservicemember.entity.UserDO;
import org.apache.ibatis.annotations.Insert;

/**
 * @ClassName: UserMapper
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/6 16:43
 * @Version： 1.0
 **/
public interface UserMapper {

    @Insert("insert into shop_user(mobile,email,password,user_name,is_avalible) values(#{mobile},#{email},#{password}," +
            "#{userName},'1')")
    int register(UserDO userDO);
}
