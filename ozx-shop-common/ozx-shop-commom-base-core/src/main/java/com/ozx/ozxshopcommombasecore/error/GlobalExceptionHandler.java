package com.ozx.ozxshopcommombasecore.error;

import com.alibaba.fastjson.JSONObject;
import com.ozx.ozxshopcommombasecore.basic.BasicApiService;
import com.ozx.ozxshopcommombasecore.basic.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/6 16:34
 * @Version： 1.0
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BasicApiService<JSONObject> {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BasicResponse<JSONObject> exceptionHandler(Exception e){
        log.info("全局捕获异常：异常原因：[{}]",e.getMessage());
         return setResultError("系统错误！");
    }
}
