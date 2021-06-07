package com.ozx.ozxshopservicemember.mapper;

import com.ozx.ozxshopservicemember.entity.UserDO;
import org.apache.ibatis.annotations.*;

/**
 * @ClassName: UserMapper
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/6 16:43
 * @Version： 1.0
 **/
public interface UserMapper {

    @Insert("insert into mall_user(mobile,password,email,is_avalible) values(#{mobile},#{password},#{email},'1')")
    int register(UserDO userDO);

    @Select(" select user_id as userId" +
            " from mall_user where mobile = #{mobile} and password = #{md5Password}")
    UserDO login(@Param("mobile") String mobile, @Param("md5Password") String md5Password);

    @Update("update mall_user_token set is_availability = '1',update_time =now() where token =#{token}")
    int updateLoginStatus(@Param("token") String  token);

    @Results(id="userDo",value = {
            @Result(column = "user_id",property = "userId"),
            @Result(column = "mobile",property = "mobile")
    })
    @Select("select * from mall_user where mobile = #{mobile} and password =#{password}")
    UserDO ssoLogin(@Param("mobile") String mobile,@Param("password") String password);
}
