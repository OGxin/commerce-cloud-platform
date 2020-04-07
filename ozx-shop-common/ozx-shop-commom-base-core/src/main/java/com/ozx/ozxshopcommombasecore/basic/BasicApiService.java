package com.ozx.ozxshopcommombasecore.basic;

import com.ozx.ozxshopcommombasecore.constants.Constants;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BasicApiService
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/4 16:09
 * @Version： 1.0
 **/
@Data
@Component
public class BasicApiService<T> {

    /**
     * @Description: 通用封装
     * @Author ou.zhenxing
     * @Date   2020/3/4 16:14
     * @Param
     * @return
     **/
    public BasicResponse<T> setResult(Integer code,String msg,T data){
        return new BasicResponse(code,msg,data);
    }

    public BasicResponse<T> setResultError(Integer code,String msg){
        return setResult(code,msg,null);
    }

    public BasicResponse<T> setResultError(String msg){
            return setResult(Constants.HTTP_RES_CODE_500,msg,null);
    }

    /**
     * @Description: 请求返回成功，有数据返回
     * @Author ou.zhenxing
     * @Date   2020/3/4 17:53
     * @Param
     * @return
     **/
    public BasicResponse<T> setResultSuccess(T data){
        return setResult(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,data);
    }

    public BasicResponse<T> setResultSuccess(){
        return  setResult(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,null);
    }
    /**
     * @Description: 请求返回成功，无数据返回
     * @Author ou.zhenxing
     * @Date   2020/3/4 17:52
     * @Param
     * @return
     **/
    public BasicResponse<T> setResultSuccess(String msg){
       return setResult(Constants.HTTP_RES_CODE_200,msg,null);
    }

    /**
     * 判断数据库执行是否成功
     * @param result
     * @return
     */
    public Boolean toDaoResult(int result){
        return result > 0 ? true:false;
    }

}
