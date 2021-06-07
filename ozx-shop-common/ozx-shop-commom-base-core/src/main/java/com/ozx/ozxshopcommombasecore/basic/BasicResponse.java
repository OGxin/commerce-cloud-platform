package com.ozx.ozxshopcommombasecore.basic;

import lombok.Data;

/**
 * @ClassName: BasicResponse
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/4 15:53
 * @Version： 1.0
 **/
@Data
public class BasicResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public BasicResponse(){}

    public BasicResponse(Integer code,String msg,T data){
        super();
        this.code=code;
        this.msg=msg;
        this.data=data;
    }


}
