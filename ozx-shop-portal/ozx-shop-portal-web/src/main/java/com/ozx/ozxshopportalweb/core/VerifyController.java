package com.ozx.ozxshopportalweb.core;

import com.ozx.ozxshopcommonweb.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: VerifyController
 * @Description:TODO
 * @Author ou.zhenxing
 * @Date 2020/3/23 21:42
 * @Version： 1.0
 **/
@Controller
public class VerifyController {

    @Autowired
    private RandomValidateCodeUtil randomValidateCodeUtil;

    @GetMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        /**
         * 设置响应头信息，不需缓存此内容
         */
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expire",0);
        /**
         * 设置响应类型，输出的内容为图片
         */
        response.setContentType("image/jpeg");
        randomValidateCodeUtil.getRandcode(request,response);
    }
}
